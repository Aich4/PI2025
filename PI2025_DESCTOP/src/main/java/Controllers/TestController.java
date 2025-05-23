package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import models.Reclamation;
import services.ReclamationService;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TestController {
    ReclamationService reclamationService;

    public TestController() {
        this.reclamationService = new ReclamationService();
    }

    @FXML
    private ComboBox<String> combo_rec;

    @FXML
    private TextField desc_rec;

    @FXML
    void initialize() {
        ObservableList<String> tList = FXCollections.observableArrayList(
                "Problème technique",
                "Problème de paiement",
                "Problème de réservation",
                "Autre"
        );

        combo_rec.setItems(tList);
    }

    @FXML
    void ajouterReclamation(ActionEvent event) {
        String type = combo_rec.getValue();
        String description = desc_rec.getText();

        // Créer un Timestamp pour la date actuelle
        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());

        // Créer une nouvelle réclamation avec l'état initial "0" (non traité)
        Reclamation reclamation = new Reclamation(description, type, currentTimestamp);
        reclamation.setEtat("En cours"); // Définir l'état initial comme En cours

        try {
            if(reclamationService.create(reclamation)) {
                System.out.println("Réclamation ajoutée avec succès");
                // Vider les champs après l'ajout
                combo_rec.setValue(null);
                desc_rec.clear();
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de la réclamation: " + e.getMessage());
        }
    }
    @FXML
    void listRec(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichageReclamation.fxml"));
        try {
            Parent root = loader.load();
            combo_rec.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void openGemini(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/apiGemini.fxml"));
            Parent root = loader.load();
            combo_rec.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goFront(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontoffice.fxml"));
            Parent root = loader.load();
            combo_rec.getScene().setRoot(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}