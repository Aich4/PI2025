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
import java.sql.SQLException;

public class UpdatePack {

    @FXML
    private ComboBox<String> Statut;

    @FXML
    private ComboBox<String> avantages;

    @FXML
    private TextField description;

    @FXML
    private TextField duree;

    @FXML
    private TextField nom_Pack;

    @FXML
    private TextField prix;



    private Pack selectedPack;
    ServicePack servicePack;

    private void loadStatutValues() {
        Statut.getItems().addAll("Actif", "Inactif", "Expiré");
        Statut.setPromptText("Sélectionner un statut");
    }
    private void loadAvantageValues() {
        avantages.getItems().addAll("Aventure", "Détente", "Exploration", "Luxe", "Culturel", "Sportif");
        avantages.setPromptText("Sélectionner un avantage");
    }

    @FXML
    void initialize() {
        loadStatutValues();
        loadAvantageValues();
    }

    @FXML
    void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/ShowPack.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public UpdatePack (){
        this.servicePack = new ServicePack();
    }


    public void setPackDetails(Pack pack) {
        this.selectedPack = pack;

        // Set the text fields with pack details, including ID if needed
        nom_Pack.setText(pack.getNom_Pack());
        description.setText(pack.getDescription());
        prix.setText(String.valueOf(pack.getPrix()));
        duree.setText(String.valueOf(pack.getDuree()));
        avantages.setValue(pack.getAvantages());
        Statut.setValue(pack.getStatut());
    }

    @FXML
    void saveChanges(ActionEvent event) {
        String nom = nom_Pack.getText().trim();
        String desc = description.getText().trim();
        String prixStr = prix.getText().trim();
        String dureeStr = duree.getText().trim();
        String avantagesStr = avantages.getValue(); // ✅ No need for `.trim()` (null-safe)
        String statutStr = Statut.getValue(); // ✅ No need for `.trim()` (null-safe)

        // Vérification que les champs ne sont pas vides
        if (nom.isEmpty() || desc.isEmpty() || prixStr.isEmpty() || dureeStr.isEmpty() || avantagesStr == null || statutStr == null) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Tous les champs doivent être remplis !");
            return;
        }

        // Vérification que le nom commence par une majuscule
        if (!Character.isUpperCase(nom.charAt(0))) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom du pack doit commencer par une majuscule !");
            return;
        }

        // Vérification que prix et durée sont bien des nombres valides et positifs
        float prixValue;
        int dureeValue;
        try {
            prixValue = Float.parseFloat(prixStr);
            dureeValue = Integer.parseInt(dureeStr);
            if (prixValue <= 0 || dureeValue <= 0) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le prix et la durée doivent être des nombres positifs !");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le prix et la durée doivent être des nombres valides !");
            return;
        }

        // Vérification du statut (doit être "Actif", "Inactif" ou "Expiré")
        statutStr = statutStr.toLowerCase();
        if (!statutStr.equals("actif") && !statutStr.equals("inactif") && !statutStr.equals("expiré")) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le statut doit être 'Actif', 'Inactif' ou 'Expiré' !");
            return;
        }

        // Vérification de l'avantage (doit être parmi les valeurs acceptées)
        String[] avantagesValides = {"Aventure", "Détente", "Exploration", "Luxe", "Culturel", "Sportif"};
        boolean avantageValide = false;
        for (String avantage : avantagesValides) {
            if (avantagesStr.equalsIgnoreCase(avantage)) {
                avantageValide = true;
                break;
            }
        }

        if (!avantageValide) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "L'avantage doit être 'Aventure', 'Détente', 'Exploration', 'Luxe', 'Culturel' ou 'Sportif' !");
            return;
        }

        // Mise à jour du pack sélectionné avec les nouvelles données
        selectedPack.setNom_Pack(nom);
        selectedPack.setDescription(desc);
        selectedPack.setPrix(prixValue);
        selectedPack.setDuree(dureeValue);
        selectedPack.setAvantages(avantagesStr);
        selectedPack.setStatut(statutStr);

        try {
            servicePack.update(selectedPack);
            showAlert(Alert.AlertType.INFORMATION, "Succès", "✅ Pack mis à jour avec succès !");
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
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























