package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import models.Categorie;
import services.CategorieService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class GestionCategorie {

        CategorieService ss;

        public GestionCategorie() {
            this.ss = new CategorieService();
        }

    @FXML
    private ImageView imageView;  // ImageView pour afficher l’image sélectionnée

    private String imagePath;

    @FXML
    private Button partenaire;

    @FXML
    void navigateToAddPartenaire(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddPartenaire.fxml")); // Path to your AddPartenaire.fxml
        try {
            Parent root = loader.load();  // Load the AddPartenaire FXML
            // Get the current scene and set the root to the new scene
            imageView.getScene().setRoot(root);
        } catch (IOException e) {
            // Handle exception if loading fails
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger la page Partenaire.");
        }
    }



    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers image", "*.jpg", "*.png", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath(); // ✅ fixed here (no URI!)
            imageView.setImage(new Image(selectedFile.toURI().toString())); // show preview
        }
    }

    @FXML
    private TextField descriptioncategorie;


    @FXML
    private TextField nomcategorie;

    @FXML
    void save(ActionEvent event) {
        String nom = nomcategorie.getText().trim();
        String description = descriptioncategorie.getText().trim();

        if (nom.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom ne peut pas être vide.");
            return;
        } else if (isOnlyDigits(nom)) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom ne peut pas contenir uniquement des chiffres.");
            return;
        }

        if (description.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "La description ne peut pas être vide.");
            return;
        } else if (isOnlyDigits(description)) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "La description ne peut pas contenir uniquement des chiffres.");
            return;
        }

        if (imagePath == null || imagePath.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une image doit être sélectionnée.");
            return;
        }

        if (ss.categoryExists(nom)) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une catégorie avec ce nom existe déjà.");
            return;
        }

        try {
            // Get original image file
            File originalFile = new File(imagePath);
            if (!originalFile.exists()) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Fichier image introuvable.");
                return;
            }

            // Generate unique filename
            String extension = imagePath.substring(imagePath.lastIndexOf('.') + 1);
            String uniqueFileName = UUID.randomUUID().toString() + "." + extension;

            // Dynamically resolve Symfony logo directory
            String baseDir = System.getProperty("user.dir"); // e.g. PI2025_DESCTOP
            Path destinationPath = Paths.get(baseDir, "../ProjetWebTrekSwap/public/uploads/logos", uniqueFileName).normalize();

            // Ensure the parent directory exists
            Files.createDirectories(destinationPath.getParent());

            // Copy file to Symfony project
            Files.copy(originalFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            // Save only filename in database
            Categorie categorie = new Categorie(nom, description, uniqueFileName);
            ss.create(categorie);

            reset(); // Clear fields
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Catégorie créée avec succès.");

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la copie de l'image :\n" + e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur :\n" + e.getMessage());
        }
    }



    // Méthode pour vérifier si le texte contient uniquement des chiffres
    private boolean isOnlyDigits(String str) {
        return str.matches("\\d+");
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


        void reset (){
            this.nomcategorie.clear();
            this.descriptioncategorie.clear();
            this.imagePath = "";

        }
    @FXML
    void showDash(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
        try {
            Parent root = loader.load();
            imageView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void logOut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        try {
            Parent root = loader.load();
            imageView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void Part(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichagePartenaire.fxml"));
        try {
            Parent root = loader.load();
            imageView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}




