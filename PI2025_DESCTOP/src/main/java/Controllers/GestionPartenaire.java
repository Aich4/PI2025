package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Partenaire;
import services.CategorieService;
import services.PartenaireService;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GestionPartenaire {
    private PartenaireService ps = new PartenaireService();
    private CategorieService cs = new CategorieService();

    @FXML
    private TextField nompartenaire;
    @FXML
    private TextField emailpartenaire;
    @FXML
    private TextField adresse;
    @FXML
    private DatePicker date;
    @FXML
    private TextField description;
    @FXML
    private ComboBox<String> idcategorie;
    @FXML
    private TextField num_tel;

    @FXML
    private Button categorie;

    @FXML
    void navigateToAddCategorie(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddCategorie.fxml")); // Path to your AddCategorie.fxml
        try {
            Parent root = loader.load();  // Load the AddCategorie FXML
            // Get the current scene and set the root to the new scene
            ((Node) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            // Handle exception if loading fails
            showErrorAlert("Erreur", "Impossible de charger la page Catégorie.");
        }
    }




    @FXML
    void save(ActionEvent event) {
        try {
            String nom = nompartenaire.getText();
            if (nom == null || nom.trim().isEmpty()) {
                throw new IllegalArgumentException("Le nom du partenaire est obligatoire.");
            }

            String nomRegex = "^(?!\\d+$)[A-Za-z0-9]+$";
            if (!nom.matches(nomRegex)) {
                throw new IllegalArgumentException("Le nom du partenaire doit être composé de lettres et/ou chiffres, mais pas uniquement de chiffres.");
            }

            String email = emailpartenaire.getText();
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("L'email du partenaire est obligatoire.");
            }

            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("L'email du partenaire est invalide.");
            }

            if (adresse.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("L'adresse du partenaire est obligatoire.");
            }

            if (description.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("La description du partenaire est obligatoire.");
            }

            String nomCategorie = idcategorie.getValue();
            if (nomCategorie == null || nomCategorie.trim().isEmpty()) {
                throw new IllegalArgumentException("La catégorie est obligatoire.");
            }

            if (date.getValue() == null) {
                throw new IllegalArgumentException("La date d'ajout est obligatoire.");
            }

            LocalDate selectedDate = date.getValue();
            if (selectedDate.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La date d'ajout ne peut pas être dans le passé.");
            }

            Date sqlDate = Date.valueOf(selectedDate);
            int idCategorie = cs.getIdByNom(nomCategorie);

            String numTelStr = num_tel.getText();
            if (numTelStr == null || numTelStr.trim().isEmpty()) {
                throw new IllegalArgumentException("Le numéro de téléphone est obligatoire.");
            }
            if (!numTelStr.matches("\\d{8}")) {
                throw new IllegalArgumentException("Le numéro de téléphone doit être composé de 8 chiffres.");
            }

            int numTel = Integer.parseInt(numTelStr);
            Partenaire p = new Partenaire(nom, email, adresse.getText(), description.getText(), sqlDate, idCategorie, numTel);

            ps.create(p);
            cs.incrementNbrCategorie(idCategorie);
            reset();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Partenaire créé avec succès.");
            alert.showAndWait();

        } catch (IllegalArgumentException e) {
            showErrorAlert("Erreur de saisie", e.getMessage());
        } catch (Exception e) {
            showErrorAlert("Erreur", "Erreur lors de la création du partenaire : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void reset() {
        nompartenaire.clear();
        emailpartenaire.clear();
        adresse.clear();
        description.clear();
        num_tel.clear();
        idcategorie.getSelectionModel().clearSelection();
        idcategorie.setPromptText("Catégorie");
        date.setValue(null);
    }

    @FXML
    void show(ActionEvent event) {
        try {
            // Get the current stage (old interface)
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Load the new scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichagePartenaire.fxml"));
            Parent root = loader.load();

            // Create a new stage (new window)
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Liste des Partenaires");
            newStage.show();

            // Close the old window (current stage)
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception, maybe show an alert or log it
        }
    }


    @FXML
    public void initialize() {
        try {
            idcategorie.getItems().addAll(cs.getAllNames());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void categorie(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichageCategorie.fxml"));
        try {
            Parent root = loader.load();
            adresse.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showDash(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
        try {
            Parent root = loader.load();
            adresse.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void logOut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        try {
            Parent root = loader.load();
            adresse.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
