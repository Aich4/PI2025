package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import models.Activite;
import services.ActiviteService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.DestinationService;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class AjoutActivite {
    DestinationService destinationService = new DestinationService();
    ActiviteService activiteService = new ActiviteService();
    public AjoutActivite() {
        this.activiteService = new ActiviteService();
        this.destinationService = new DestinationService();
    }
    @FXML
    private DatePicker Date;

    @FXML
    private TextField Heure;

    @FXML
    private ComboBox<String> Statut;

    @FXML
    private ComboBox<String> idDest;

    @FXML
    private TextField nom;

    @FXML
    void ajoutActivite(ActionEvent event) {
        // Validate inputs
        if (idDest.getValue() == null || nom.getText().isEmpty() || Date.getValue() == null ||
                Heure.getText().isEmpty() || Statut.getValue() == null) {

            showAlert(Alert.AlertType.WARNING, "Warning", "All fields must be filled!");
            return;
        }

        // Validate destination
        int id;
        try {
            id = this.destinationService.getIdByName(idDest.getValue());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid destination selected!");
            return;
        }

        // Validate date (should not be in the past)
        LocalDate localDate = Date.getValue();
        if (localDate.isBefore(LocalDate.now())) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Date cannot be in the past!");
            return;
        }

        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        // Validate time format (HH:mm)
        if (!Heure.getText().matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Invalid time format! Use HH:mm.");
            return;
        }

        // Create and save activity
        Activite activite = new Activite(id, nom.getText(), sqlDate, Heure.getText(), Statut.getValue());

        try {
            this.activiteService.create(activite);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Activity created successfully!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    // Utility function to show alerts
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void showActivite(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListActiviteBack.fxml"));
        try {
            Parent root = loader.load();
            Heure.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void showDestination(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListDestinationBack.fxml"));
        try {
            Parent root = loader.load();
            Heure.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        // ✅ Restrict to only these values
        ObservableList<String> statutList = FXCollections.observableArrayList("active", "inactive", "completed");
        Statut.setItems(statutList);

        try {
            List<String> names = destinationService.getAllNames();
            ObservableList<String> nameList = FXCollections.observableArrayList(names);
            idDest.setItems(nameList);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chargement des destinations.", e);
        }
    }


}
