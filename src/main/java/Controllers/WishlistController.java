package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import models.Destination;
import services.WishlistService;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.List;

import static Controllers.planDestination.openPlanDestinationPage;

public class WishlistController {
    @FXML
    private TextArea textArea; // Declare your TextArea
    @FXML
    private VBox wishlistContainer;

    @FXML
    private Button generateButton; // Declare the button

    public void initialize() {
        // Fetch the wishlist data
        List<Destination> wishlist = WishlistService.getInstance().getWishlist();

        // Clear the previous content
        wishlistContainer.getChildren().clear();

        // Loop through each destination in the wishlist
        for (Destination destination : wishlist) {
            // Create a new HBox for each item in the wishlist
            HBox wishlistItem = new HBox(10);
            wishlistItem.setStyle("-fx-background-color: #ffffff; -fx-padding: 10; -fx-border-radius: 8px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.5, 0, 2);");

            // Create an ImageView for the destination image (if available)
            ImageView destinationImage = new ImageView();
            if (destination.getImage_destination() != null && !destination.getImage_destination().isEmpty()) {
                Image image = new Image(destination.getImage_destination());
                destinationImage.setImage(image);
                destinationImage.setFitHeight(50);
                destinationImage.setFitWidth(50);
                destinationImage.setStyle("-fx-background-radius: 25;");
            }

            // Create a VBox for destination text
            VBox destinationInfo = new VBox(5);
            Text destinationName = new Text(destination.getNom_destination());
            destinationName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333333;");
            Text destinationDescription = new Text(destination.getDecription());
            destinationDescription.setStyle("-fx-font-size: 12px; -fx-text-fill: #777777;");
            destinationInfo.getChildren().addAll(destinationName, destinationDescription);

            // Create a remove button or an image (e.g., heart icon for wishlist)
            // You can add a button here if you want to allow the user to remove items from the wishlist.
            // Example: Button removeButton = new Button("Remove");
            // Alternatively, use an ImageView with a heart icon.
            ImageView removeIcon = new ImageView(new Image(getClass().getResourceAsStream("/heart_filled.png")));
            removeIcon.setFitHeight(20);
            removeIcon.setFitWidth(20);
            removeIcon.setOnMouseClicked(event -> {
                // Remove the destination from the wishlist
                WishlistService.getInstance().remove(destination);
                // Refresh the wishlist UI
                initialize();
            });

            // Add the components to the HBox
            wishlistItem.getChildren().addAll(destinationImage, destinationInfo, removeIcon);

            // Add the item to the VBox (the container)
            wishlistContainer.getChildren().add(wishlistItem);
        }
        wishlistContainer.getChildren().add(generateButton);  // Add the button to the VBox if it's not already in the FXML

    }
    @FXML
    void generateButtonClicked() {
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
        geminiInput.append("I have a wishlist of destinations for a 3-day trip: (pls give me well constructed paragraphe exemple day 1 : ..." +
                "day2 : ..." +
                "..." +
                "and pls give me directly tohe plan no ther texts )\n");


        // Format the wishlist destinations into the message
        for (Destination destination : wishlist) {
            geminiInput.append("Destination: " + destination.getNom_destination() + ", ");
            geminiInput.append("Latitude: " + destination.getLatitude() + ", ");
            geminiInput.append("Longitude: " + destination.getLongitude() + "\n");
        }

        // Send the message to Gemini (you may need to use an API call or direct communication)
        String geminiResponse = sendToGemini(geminiInput.toString());

        // Pass the Gemini response to the openPlanDestinationPage method
        openPlanDestinationPage(geminiResponse);
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
