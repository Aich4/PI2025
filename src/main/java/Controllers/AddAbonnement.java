package Controllers;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import models.Abonnement;
import services.ServiceAbonnement;
import services.ServicePack;

public class AddAbonnement {




        @FXML
        private ComboBox<String> statuta;

        @FXML
        private DatePicker dateE;

        @FXML
        private DatePicker dateS;

        @FXML
        private ComboBox< String > nompack;

            private ServiceAbonnement serviceAbonnement = new ServiceAbonnement();

    @FXML
    void initialize() {
        loadPackID();
        loadStatutValues();
    }

    private void loadStatutValues() {
        statuta.getItems().addAll("Actif", "Inactif", "Expiré");
        statuta.setPromptText("Sélectionner un statut");
    }

    public void loadPackID() {

        ServicePack servicePack = new ServicePack();
        List<String> packNames = servicePack.getAllPackID();

        System.out.println("Pack names fetched: " + packNames);

        nompack.getItems().clear();
        nompack.getItems().addAll(packNames);

        nompack.setPromptText("Sélectionner un pack");

        if (!packNames.isEmpty()) {
            nompack.setValue(packNames.get(0));
        }
    }

    @FXML
    void save(ActionEvent event) {
        try {
            // Récupération des valeurs saisies
            String selectedPack = nompack.getValue();
            LocalDate newDateS = dateS.getValue();
            LocalDate newDateE = dateE.getValue();
            String statutA = statuta.getValue(); // Récupération correcte de la valeur sélectionnée

            // Vérification si les champs sont vides
            if (selectedPack == null || newDateS == null || newDateE == null || statutA == null) {
                showAlert("Erreur", "⚠ Tous les champs doivent être remplis !");
                return;
            }

            // Vérification que la date de souscription n'est pas avant aujourd'hui
            if (newDateS.isBefore(LocalDate.now())) {
                showAlert("Erreur", "⚠ La date de souscription ne peut pas être antérieure à aujourd'hui !");
                return;
            }

            // Vérification que la date de souscription est avant la date d'expiration
            if (newDateS.isAfter(newDateE)) {
                showAlert("Erreur", "⚠ La date de souscription doit être avant la date d'expiration !");
                return;
            }

            // Debugging: Affichage des valeurs avant enregistrement
            System.out.println("Statut: " + statutA);
            System.out.println("Date Souscription: " + newDateS);
            System.out.println("Date Expiration: " + newDateE);
            System.out.println("ID du Pack: " + selectedPack);

            // Conversion du pack ID en entier
            int idPack = Integer.parseInt(selectedPack);
            // Conversion des LocalDate en SQL Date
            Date sqlDateS = Date.valueOf(newDateS);
            Date sqlDateE = Date.valueOf(newDateE);

            // Création de l'objet Abonnement
            Abonnement abonnement = new Abonnement(0, idPack, sqlDateS, sqlDateE, statutA);

            // Enregistrement dans la base de données
            serviceAbonnement.create(abonnement);

            // Affichage du message de succès
            showAlert("Succès", "✅ Abonnement créé avec succès !");
        } catch (NumberFormatException e) {
            showAlert("Erreur", "⚠ ID du pack invalide !");
        } catch (SQLException e) {
            showAlert("Erreur", "⚠ Erreur SQL : " + e.getMessage());
        } catch (Exception e) {
            showAlert("Erreur", "⚠ Une erreur est survenue : " + e.getMessage());
            e.printStackTrace();
        }
    }





    private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setContentText(message);
            alert.showAndWait();
        }


    @FXML
    void showAbonnement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowAbonnement.fxml"));
            Parent root = loader.load();

            // Remplacer la scène actuelle
            Scene currentScene = ((Node) event.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    }


