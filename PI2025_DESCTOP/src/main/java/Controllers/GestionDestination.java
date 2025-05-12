package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView imageView;

    @FXML
    void ajoutDestination(ActionEvent event) {
        String nom = nomDestination.getText().trim();
        String description = descriptionDestination.getText().trim();
        String image = (imagePath != null) ? imagePath : "";
        String latitudeStr = latitudeDestination.getText().trim();
        String longitudeStr = longitudeDestination.getText().trim();
        String temperatureStr = temperatureDestination.getText().trim();
        String ratingStr = ratingDestination.getText().trim();

        // Validate text fields
        if (!isValidText(nom, 3)) {
            showAlert("Error", "Name must be at least 3 characters long and contain only letters and spaces.", Alert.AlertType.ERROR);
            return;
        }
        if (!isValidText(description, 10)) {
            showAlert("Error", "Description must be at least 10 characters long and contain only valid text.", Alert.AlertType.ERROR);
            return;
        }

        // Validate image path
        if (!isValidImagePath(image)) {
            showAlert("Error", "Invalid image file! Must be a .jpg, .jpeg, or .png file.", Alert.AlertType.ERROR);
            return;
        }

        // Validate numeric fields
        Double latitude = validateDouble(latitudeStr, "Latitude", -90, 90);
        Double longitude = validateDouble(longitudeStr, "Longitude", -180, 180);
        Double temperature = validateDouble(temperatureStr, "Temperature", -50, 60);
        Double rating = validateDouble(ratingStr, "Rating", 0, 5);

        if (latitude == null || longitude == null || temperature == null || rating == null) {
            return; // showAlert is already called inside validateDouble
        }

        // Ensure rating has at most one decimal place
        if (!isValidDecimal(ratingStr, 1)) {
            showAlert("Error", "Rating must have at most one decimal place.", Alert.AlertType.ERROR);
            return;
        }

        // Create and save the destination
        Destination d = new Destination(nom, description, "/uploads/" + image, latitude, longitude, temperature, rating);

        try {
            ds.create(d);
            reset();
            showAlert("Success", "Destination created successfully!", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            showAlert("Error", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Helper method to validate text fields (name, description)
    private boolean isValidText(String text, int minLength) {
        return text.matches("^[A-Za-zÀ-ÿ'\\s]{" + minLength + ",}$");
    }

    private boolean isValidImagePath(String path) {
        return path.matches(".*\\.(jpg|jpeg|png)$");
    }


    // Helper method to validate numeric fields with a range
    private Double validateDouble(String value, String fieldName, double min, double max) {
        try {
            double num = Double.parseDouble(value);
            if (num < min || num > max) {
                showAlert("Error", fieldName + " must be between " + min + " and " + max + ".", Alert.AlertType.ERROR);
                return null;
            }
            return num;
        } catch (NumberFormatException e) {
            showAlert("Error", fieldName + " must be a valid number.", Alert.AlertType.ERROR);
            return null;
        }
    }

    // Helper method to validate decimal places
    private boolean isValidDecimal(String value, int decimalPlaces) {
        return value.matches("^\\d+(\\.\\d{1," + decimalPlaces + "})?$");
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
        fileChooser.setTitle("Sélectionner une image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Fichiers image", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                // Generate unique name
                String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf('.') + 1);
                String uniqueFileName = java.util.UUID.randomUUID().toString() + "." + extension;

                // Copy to Symfony project
                String baseDir = System.getProperty("user.dir");
                File destinationDir = new File(baseDir, "../ProjetWebTrekSwap/public/uploads");
                destinationDir.mkdirs(); // Ensure directory exists

                File destinationFile = new File(destinationDir, uniqueFileName);
                java.nio.file.Files.copy(
                        selectedFile.toPath(),
                        destinationFile.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );

                imagePath = uniqueFileName; // Save just the filename for DB
                imageView.setImage(new Image(destinationFile.toURI().toString())); // Preview
            } catch (IOException e) {
                showAlert("Erreur", "Échec lors de la copie de l'image : " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    void reset() {
        nomDestination.clear();
        descriptionDestination.clear();
        latitudeDestination.clear();
        longitudeDestination.clear();
        temperatureDestination.clear();
        ratingDestination.clear();
        imagePath = "";
        imageView.setImage(null);
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
