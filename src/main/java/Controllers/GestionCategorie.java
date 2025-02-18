package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import models.Categorie;
import services.CategorieService;

import java.io.File;
import java.io.IOException;

public class GestionCategorie {

        CategorieService ss;

        public GestionCategorie() {
            this.ss = new CategorieService();
        }

    @FXML
    private TextField descriptioncategorie;


    @FXML
    private TextField nomcategorie;

    @FXML
    void save(ActionEvent event) {
        // Récupération des données entrées
        String nom = nomcategorie.getText().trim();
        String description = descriptioncategorie.getText().trim();

        // Vérification du nom
        if (nom.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom ne peut pas être vide.");
            return;
        } else if (isOnlyDigits(nom)) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom ne peut pas contenir uniquement des chiffres.");
            return;
        }

        // Vérification de la description
        if (description.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "La description ne peut pas être vide.");
            return;
        }

        // Vérification de l'image
        if (imagePath == null || imagePath.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une image doit être sélectionnée.");
            return;
        }

        // Création de la catégorie
        Categorie categorie = new Categorie(nom, description, imagePath);

        try {
            // Tentative de sauvegarde
            this.ss.create(categorie);
            reset();  // Réinitialisation des champs
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Catégorie créée avec succès.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", e.getMessage());
        }
    }

    // Méthode pour vérifier si le texte contient uniquement des chiffres
    private boolean isOnlyDigits(String text) {
        return text.matches("\\d+"); // Vérifie si le texte contient uniquement des chiffres
    }

    // Méthode pour afficher les alertes
    private void showAlert(Alert.AlertType alertType, String title, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    void show(ActionEvent event) {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichageCategorie.fxml"));
        try {
            Parent root = loader.load();
            nomcategorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String imagePath;
        void reset (){
            this.nomcategorie.clear();
            this.descriptioncategorie.clear();
            this.imagePath = "";

        }
    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            this.imagePath = selectedFile.toURI().toString();
        }
    }



}




