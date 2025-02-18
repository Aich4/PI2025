package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import models.Destination;
import services.DestinationService;

import java.io.File;
import java.io.IOException;

public class GestionDestination {
    DestinationService ds = new DestinationService();
    public GestionDestination() {
        this.ds = new DestinationService();
    }
    private String imagePath;

    @FXML
    private Button chooseImageButton;

    @FXML
    private TextArea descriptionDestination;

    @FXML
    private TextField latitudeDestination;

    @FXML
    private TextField longitudeDestination;

    @FXML
    private TextField nomDestination;

    @FXML
    private TextField ratingDestination;

    @FXML
    private TextField temperatureDestination;

    @FXML
    void ajoutDestination(ActionEvent event) {

        String nom = this.nomDestination.getText().trim();
        String description = this.descriptionDestination.getText().trim();
        String image = imagePath;
        String latitudeStr = this.latitudeDestination.getText().trim();
        String longitudeStr = this.longitudeDestination.getText().trim();
        String temperatureStr = this.temperatureDestination.getText().trim();
        String ratingStr = this.ratingDestination.getText().trim();

// Check if fields are empty
        if (nom.isEmpty() || description.isEmpty() || image.isEmpty() || image == null ||
                latitudeStr.isEmpty() || longitudeStr.isEmpty() || temperatureStr.isEmpty() || ratingStr.isEmpty()) {
            showAlert("Error", "All fields must be filled!", Alert.AlertType.ERROR);
            return;
        }

        try {
            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);
            double temperature = Double.parseDouble(temperatureStr);
            double rating = Double.parseDouble(ratingStr);

            // Validate rating range
            if (rating < 0 || rating > 5) {
                showAlert("Error", "Rating must be between 0 and 5.", Alert.AlertType.ERROR);
                return;
            }

            // Create and save the destination
            Destination d = new Destination(nom, description, image, latitude, longitude, temperature, rating);
            try {
                this.ds.create(d);
                reset();
                showAlert("Success", "Destination created successfully!", Alert.AlertType.INFORMATION);

            } catch (Exception e) {

                showAlert("erreur", e.getMessage(), Alert.AlertType.INFORMATION);
            }


        } catch (NumberFormatException e) {
            showAlert("Error", "Latitude, Longitude, Temperature, and Rating must be valid numbers.", Alert.AlertType.ERROR);
        }
    }

// Utility method to show alerts
        private void showAlert(String title, String content, Alert.AlertType type) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setContentText(content);
            alert.showAndWait();
        }


        @FXML
    void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");

        // Set file filter to show only images
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            imagePath = selectedFile.toURI().toString(); // Save path
        }
    }
    void reset() {
        this.nomDestination.setText("");
        this.descriptionDestination.setText("");
        this.latitudeDestination.setText("");
        this.longitudeDestination.setText("");
        this.temperatureDestination.setText("");
        this.ratingDestination.setText("");
        this.imagePath = "";
    }
    @FXML
    void showUser(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListDestinationBack.fxml"));
        try {
            Parent root = loader.load();
            ratingDestination.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showActivite(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListActiviteBack.fxml"));
        try {
            Parent root = loader.load();
            ratingDestination.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
