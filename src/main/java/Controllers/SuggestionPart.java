package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import services.CategorieService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SuggestionPart {
    CategorieService categorieService = new CategorieService();

    private static final String GEMINI_API_KEY = "AIzaSyAonOZVggKfRuWsE5gjGrbXwuXBRfSFlWs"; // 🔹 Replace with your API key
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + GEMINI_API_KEY;

    @FXML
    private ComboBox<String> category;

    @FXML
    private TextArea displayText;

    @FXML
    private Spinner<Integer> numPartnersSpinner;

    @FXML
    private Button Retour;

    @FXML
    void navigateToAffichagePartenaire(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichagePartenaire.fxml")); // Path to your affichagePartenaire.fxml
        try {
            Parent root = loader.load();  // Load the affichagePartenaire.fxml
            // Get the current scene and set the root to the new scene
            Retour.getScene().setRoot(root);
        } catch (IOException e) {
            // Handle exception if loading fails
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger la page Affichage Partenaire.");
        }
    }
    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Show the alert and wait for the user to close it
        alert.showAndWait();
    }
    @FXML
    public void initialize() {
        loadCategories();

        // Set initial spinner value if required
        numPartnersSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 3));
    }

    private void loadCategories() {
        try {
            List<String> categories = categorieService.getAllNames();
            category.getItems().addAll(categories);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception properly (logging, UI alert, etc.)
        }
    }

    @FXML
    void suggestPart(ActionEvent event) {
        String selectedCategory = category.getValue();
        Integer numPartners = numPartnersSpinner.getValue();  // Get the number of partners from the spinner

        if (selectedCategory == null || selectedCategory.isEmpty()) {
            displayText.setText("Please select a category first.");
            return;
        }

        if (numPartners == null || numPartners <= 0) {
            displayText.setText("Please choose a valid number of partners.");
            return;
        }

        try {
            String partnersInfo = generatePartnerInfo(selectedCategory, numPartners); // Pass the number of partners to the method
            displayText.setText(partnersInfo);
        } catch (Exception e) {
            e.printStackTrace();
            displayText.setText("Error generating partner information.");
        }
    }

    private String generatePartnerInfo(String category, int numPartners) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        // Define the prompt with dynamic partner count
        String prompt = "Generate a list of " + numPartners + " partners in the category of " + category +
                " located in Tunisia. Format the output as follows without extra text:\n\n";

        for (int i = 1; i <= numPartners; i++) {
            prompt += "🔹 Partner " + i + "\n" +
                    "📌 Name: [Partner Name]\n" +
                    "📧 Email: [Partner Email]\n" +
                    "📞 Phone: [Partner Phone Number]\n" +
                    "🛎️ Services Offered: [Services Offered]\n\n";
        }

        // JSON request body for Gemini API
        JSONObject contentPart = new JSONObject();
        contentPart.put("text", prompt);

        JSONObject content = new JSONObject();
        content.put("parts", new JSONArray().put(contentPart));

        JSONObject requestBody = new JSONObject();
        requestBody.put("contents", new JSONArray().put(content));

        // Build the request
        Request request = new Request.Builder()
                .url(GEMINI_API_URL)
                .post(RequestBody.create(MediaType.parse("application/json"), requestBody.toString()))
                .build();

        // Execute the request
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Unexpected response code: " + response.code());
        }

        // Parse the response
        JSONObject responseBody = new JSONObject(response.body().string());
        JSONArray candidates = responseBody.optJSONArray("candidates");

        if (candidates == null || candidates.length() == 0) {
            return "No response from Gemini API.";
        }

        // Extract the generated content
        JSONObject firstCandidate = candidates.getJSONObject(0);
        JSONArray contentParts = firstCandidate.getJSONObject("content").optJSONArray("parts");

        if (contentParts == null || contentParts.length() == 0) {
            return "No content generated.";
        }

        // Get the text response
        String generatedText = contentParts.getJSONObject(0).getString("text");

        // Clean the response and remove duplicates
        String[] lines = generatedText.split("\n");
        StringBuilder formattedText = new StringBuilder();

        boolean categoryHeaderAdded = false;
        int partnerNumber = 1;

        for (String line : lines) {
            line = line.trim();

            // Avoid duplicate "Partners for Category"
            if (line.startsWith("🏨 Partners for Category") && categoryHeaderAdded) {
                continue;
            }

            if (line.startsWith("🏨 Partners for Category")) {
                categoryHeaderAdded = true;
                formattedText.append(line).append("\n\n");
                continue;
            }

            // Fix repeated partner numbers
            if (line.matches("🔹 Partner \\d+")) {
                formattedText.append("🔹 Partner ").append(partnerNumber).append("\n");
                partnerNumber++;
            } else {
                formattedText.append(line).append("\n");
            }
        }

        return formattedText.toString().trim();
    }
}
