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
        LocalDate localDate = Date.getValue();
        int id;
        try {
            id = this.destinationService.getIdByName(idDest.getValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        Activite activite = new Activite(id,nom.getText(),sqlDate,Heure.getText(),Statut.getValue());

        try {
            this.activiteService.create(activite);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Activite created");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
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
        ObservableList<String> statutList = FXCollections.observableArrayList("annulé", "complet", "disponible");
        Statut.setItems(statutList);
        List<String> names = null;
        try {
            names = destinationService.getAllNames();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Convert List to ObservableList
        ObservableList<String> nameList = FXCollections.observableArrayList(names);

        // Set items in ComboBox
        idDest.setItems(nameList);

    }

}
