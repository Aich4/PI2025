package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import models.Abonnement;
import services.ServiceAbonnement;
import services.ServicePack;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class UpdateAbonnement {
    @FXML
    private DatePicker dateE;

    @FXML
    private DatePicker dateS;

    @FXML
    private ComboBox<String> nompack;

    @FXML
    private ComboBox <String> statuta;

    private Abonnement selectedA;

    private final ServiceAbonnement serviceAbonnement;

    private void loadStatutValues() {
        statuta.getItems().addAll("Actif", "Inactif", "Expiré");
        statuta.setPromptText("Sélectionner un statut");
    }



    public UpdateAbonnement() {
        this.serviceAbonnement = new ServiceAbonnement();
    }

    @FXML
    void initialize() {
        loadPackID();
        loadStatutValues(); // Now the status dropdown is populated
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ShowAbonnement.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void setAbonnDetails(Abonnement A) {
        this.selectedA = A;

        // Debug: Print the values
        System.out.println("Selected Abonnement: " + A);

        int idA=A.getId_Abonnement();
        // Set the ComboBox with the pack ID
        String idPack = String.valueOf(A.getId_Pack());
        nompack.setValue(idPack);

        // Set other details
        statuta.setValue(A.getStatut());

        // Convert java.sql.Date to LocalDate correctly
        if (A.getDate_Souscription() != null) {
            dateS.setValue(A.getDate_Souscription().toLocalDate());
        } else {
            System.out.println("⚠ dateS is NULL!");
        }

        if (A.getDate_Expiration() != null) {
            dateE.setValue(A.getDate_Expiration().toLocalDate());
        } else {
            System.out.println("⚠ dateE is NULL!");
        }
    }


    public void loadPackID() {
        ServicePack servicePack = new ServicePack();
        List<String> packNames = servicePack.getAllPackID();

        nompack.getItems().clear();
        nompack.getItems().addAll(packNames);
        nompack.setPromptText("Sélectionner un pack");
    }

    @FXML
    void save(ActionEvent event) {
        String selectedPack = nompack.getValue();
        LocalDate newDateS = dateS.getValue();
        LocalDate newDateE = dateE.getValue();
        String statutA = statuta.getValue(); // ✅ Corrected to get the selected value from ComboBox

        // Vérification si les champs sont vides
        if (selectedPack == null || newDateS == null || newDateE == null || statutA == null) {
            showAlert("Erreur", "Tous les champs doivent être remplis !");
            return;
        }

        // Vérification que la date de souscription est avant la date d'expiration
        if (newDateS.isAfter(newDateE)) {
            showAlert("Erreur", "La date de souscription doit être avant la date d'expiration !");
            return;
        }

        // Vérification que la date de souscription n'est pas avant aujourd'hui
        if (newDateS.isBefore(LocalDate.now())) {
            showAlert("Erreur", "La date de souscription ne peut pas être avant aujourd'hui !");
            return;
        }

        // Liste des statuts valides
        List<String> statutsValides = List.of("Actif", "Inactif", "Expiré", "En attente", "Annulé");

        // Vérification que le statut est valide
        if (!statutsValides.contains(statutA)) {
            showAlert("Erreur", "Statut invalide ! Veuillez choisir parmi : " + statutsValides);
            return;
        }

        try {
            selectedA.setDate_Expiration(java.sql.Date.valueOf(newDateE));
            selectedA.setDate_Souscription(java.sql.Date.valueOf(newDateS));
            selectedA.setId_Pack(Integer.parseInt(selectedPack)); // Convertir en entier
            selectedA.setStatut(statutA);

            // Mise à jour de l'abonnement
            serviceAbonnement.update(selectedA);
            showInfo("Succès", "✅ Abonnement mis à jour avec succès !");
        } catch (NumberFormatException e) {
            showAlert("Erreur", "ID du pack invalide !");
        } catch (SQLException e) {
            showAlert("Erreur", "Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    // Fonction pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
