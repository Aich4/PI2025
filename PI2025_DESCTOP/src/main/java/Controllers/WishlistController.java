package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Destination;
import services.WishlistService;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static Controllers.planDestination.openPlanDestinationPage;

public class WishlistController {
    @FXML
    private TextArea textArea; // Declare your TextArea
    @FXML
    private VBox wishlistContainer;



    @FXML
    private Spinner<Integer> nmbrJours;

    @FXML
    private Button generateButton; // Declare the button

    @FXML
    private Button returnBtn;


    @FXML
    void goBack(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontOffice.fxml"));
        try {
            Parent root = loader.load();
            generateButton.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void initialize() {
        // Create a Label for the number of days
        Label daysLabel = new Label("Number of Days:");

        // Initialize Spinner with default values
        nmbrJours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 5));

        // Create an HBox to hold the label and spinner together
        HBox spinnerContainer = new HBox(10, daysLabel, nmbrJours);
        spinnerContainer.setStyle("-fx-padding: 10;");

        // Fetch the wishlist data
        List<Destination> wishlist = WishlistService.getInstance().getWishlist();

        // Clear the previous content
        wishlistContainer.getChildren().clear();

        // Add the spinner container at the top
        wishlistContainer.getChildren().add(spinnerContainer);

        // Loop through each destination in the wishlist
        for (Destination destination : wishlist) {
            HBox wishlistItem = new HBox(10);
            wishlistItem.setStyle("-fx-background-color: #ffffff; -fx-padding: 10; -fx-border-radius: 8px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.5, 0, 2);");

            ImageView destinationImage = new ImageView();
            if (destination.getImage_destination() != null && !destination.getImage_destination().isEmpty()) {
                try {
                    String basePath = System.getProperty("user.dir");
                    File imageFile = new File("../ProjetWebTrekSwap/public" + destination.getImage_destination());
                    if (imageFile.exists()) {
                        Image image = new Image(imageFile.toURI().toString());
                        destinationImage.setImage(image);
                        destinationImage.setFitHeight(50);
                        destinationImage.setFitWidth(50);
                        destinationImage.setStyle("-fx-background-radius: 25;");
                    }
                } catch (Exception e) {
                    System.out.println("Failed to load image for wishlist item: " + e.getMessage());
                    destinationImage.setImage(null);
                }
            }


            VBox destinationInfo = new VBox(5);
            Text destinationName = new Text(destination.getNom_destination());
            destinationName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333333;");
            Text destinationDescription = new Text(destination.getDecription());
            destinationDescription.setStyle("-fx-font-size: 12px; -fx-text-fill: #777777;");
            destinationInfo.getChildren().addAll(destinationName, destinationDescription);

            ImageView removeIcon = new ImageView(new Image(getClass().getResourceAsStream("/heart_filled.png")));
            removeIcon.setFitHeight(20);
            removeIcon.setFitWidth(20);
            removeIcon.setOnMouseClicked(event -> {
                WishlistService.getInstance().remove(destination);
                initialize();
            });

            wishlistItem.getChildren().addAll(destinationImage, destinationInfo, removeIcon);
            wishlistContainer.getChildren().add(wishlistItem);
        }

        // Re-add the return button
        wishlistContainer.getChildren().add(returnBtn);

        // Re-add the generate button at the bottom
        wishlistContainer.getChildren().add(generateButton);
    }



    @FXML
    void generateButtonClicked() {
        // Get the number of days from the spinner
        int numberOfDays = nmbrJours.getValue();

        // List of destinations in the wishlist
        List<Destination> wishlist = WishlistService.getInstance().getWishlist();

        // Check if there are any destinations in the wishlist
        if (wishlist.isEmpty()) {
            // Alert if no destinations are in the wishlist
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Destinations");
            alert.setContentText("Your wishlist is empty. Please add some destinations first.");
            alert.showAndWait();
            return;
        }

        // Create a message to send to Gemini
        StringBuilder geminiInput = new StringBuilder();
        geminiInput.append("I have a wishlist of destinations in tunisia for a ")
                .append(numberOfDays)
                .append("-day trip. Please provide a well-structured itinerary for ")
                .append(numberOfDays)
                .append(" days. Example format: \n")
                .append("Day 1: ...\n")
                .append("Day 2: ...\n")
                .append("...\n")
                .append("Give me only the plan, no other text.\n");

        // Format the wishlist destinations into the message
        for (Destination destination : wishlist) {
            geminiInput.append("Destination: ").append(destination.getNom_destination()).append(" / ");
        }

        // Send the message to Gemini API
        String geminiResponse = sendToGemini(geminiInput.toString());

        Stage currentStage = (Stage) generateButton.getScene().getWindow();
        openPlanDestinationPage(geminiResponse, currentStage);
    }



    private String sendToGemini(String message) {
        try {
            // The URL for the Gemini API
            URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=AIzaSyBmclJH-djxlTjhP3tKeunWdNLcx6-g8KU");

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            // Prepare the message to send in the correct format based on documentation
            String payload = String.format(
                    "{\"contents\": [{\"parts\": [{\"text\": \"%s\"}]}]}",
                    message
            );

            // Enable input/output streams
            connection.setDoOutput(true);

            // Send the message
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Check for response code
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return "Error: " + responseCode;
            }

            // Read the response from Gemini
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }

                // Parse the JSON response
                String responseString = response.toString();

                // Use JSON parsing (with org.json library, for example)
                org.json.JSONObject jsonResponse = new org.json.JSONObject(responseString);
                String planText = jsonResponse.getJSONArray("candidates")
                        .getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text");

                // Return the cleaned up plan
                return planText;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating plan: " + e.getMessage();
        }
    }



}
