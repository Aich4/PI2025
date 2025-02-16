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
        Destination d = new Destination(this.nomDestination.getText(),this.descriptionDestination.getText(),imagePath,Double.parseDouble(this.latitudeDestination.getText()),Double.parseDouble(this.longitudeDestination.getText()),Double.parseDouble(this.temperatureDestination.getText()),Double.parseDouble(this.ratingDestination.getText()));
        try{
            this.ds.create(d);
            reset();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Destination created");
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjoutActivite.fxml"));
        try {
            Parent root = loader.load();
            ratingDestination.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
