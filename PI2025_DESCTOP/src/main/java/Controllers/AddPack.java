package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Pack;
import services.ServicePack;

import java.io.IOException;





public class AddPack {
    ServicePack servicePack;
    public AddPack() {
        this.servicePack = new ServicePack();
    }

    @FXML
    private ComboBox<String> Statut1;

    @FXML
    private ComboBox<String > avantages;

    @FXML
    private TextField description;

    @FXML
    private TextField duree;

    @FXML
    private TextField nom_Pack;

    @FXML
    private TextField prix;

    @FXML
    void initialize() {
        loadStatutValues();
        loadAvantagesValues();
    }

    private void loadStatutValues() {
        Statut1.getItems().addAll("Actif", "Inactif", "Expiré");
        Statut1.setPromptText("Sélectionner un statut");
    }

    private void loadAvantagesValues() {
        avantages.getItems().addAll("Aventure", "Détente", "Exploration", "Luxe", "Culturel", "Sportif");
        avantages.setPromptText("Sélectionner un avantage");
    }


    @FXML
    void save(ActionEvent event) {
        String nom = nom_Pack.getText().trim();
        String desc = description.getText().trim();
        String prixStr = prix.getText().trim();
        String dureeStr = duree.getText().trim();
        String avantagesStr = avantages.getValue(); // Récupération correcte depuis le ComboBox
        String statutStr = Statut1.getValue(); // Récupération correcte depuis le ComboBox



        // Vérification du format du nom (doit commencer par une majuscule)
        if (!Character.isUpperCase(nom.charAt(0))) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom du pack doit commencer par une majuscule !");
            return;
        }

        // Vérification que prix et durée sont des nombres valides et positifs
        int prixValue, dureeValue;
        try {
            prixValue = Integer.parseInt(prixStr);
            dureeValue = Integer.parseInt(dureeStr);
            if (prixValue <= 0 || dureeValue <= 0) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le prix et la durée doivent être des nombres positifs !");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le prix et la durée doivent être des nombres valides !");
            return;
        }

        // Création du pack si tout est valide
        Pack pack = new Pack(nom, 0, desc, prixValue, dureeValue, avantagesStr, statutStr);

        try {
            servicePack.create(pack);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Pack créé avec succès !");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur : " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }


    // Fonction pour afficher des alertes
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void showpack(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowPack.fxml"));
            Parent root = loader.load();

            // Afficher la nouvelle fenêtre
            Scene currentScene = ((Node) event.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showDash(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void showPack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ShowAbonnement.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void logOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}




























