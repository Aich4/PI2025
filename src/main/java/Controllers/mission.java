package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import models.Mission;
import services.MissionService;
import services.RecompenseService;

import java.io.IOException;


public class mission {
    MissionService ms;
    RecompenseService rs;

    public mission() {
        this.ms = new MissionService();
        this.rs = new RecompenseService();
    }

    @FXML
    private TextArea  description;

    @FXML
    private TextField points_recompense;

    @FXML
    private ComboBox<String> statut;

    @FXML
    private ComboBox<String> idRec;

    @FXML
    void createMission(ActionEvent event) throws Exception {
        try {
            // Récupération et validation des champs
            String descriptionText = description.getText().trim();
            String pointsText = points_recompense.getText().trim();
            String statutValue = statut.getValue();
            String descriptionRec = idRec.getValue();

            // Vérification des champs obligatoires
            if (descriptionText.isEmpty()) {
                showAlert("Erreur", "La description ne peut pas être vide.");
                return;
            }

            if (pointsText.isEmpty()) {
                showAlert("Erreur", "Les points de récompense sont requis.");
                return;
            }

            int points;
            try {
                points = Integer.parseInt(pointsText);
                if (points < 0) {
                    showAlert("Erreur", "Les points de récompense doivent être un nombre positif.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer un nombre valide pour les points de récompense.");
                return;
            }

            if (statutValue == null || statutValue.isEmpty()) {
                showAlert("Erreur", "Veuillez sélectionner un statut.");
                return;
            }

            if (descriptionRec == null || descriptionRec.isEmpty()) {
                showAlert("Erreur", "Veuillez sélectionner une récompense.");
                return;
            }

            // Récupération de l'ID de la récompense
            int idRec = rs.getIdByDescrption(descriptionRec);

            // Création de la mission
            Mission m = new Mission(descriptionText, points, statutValue, idRec);

            // Enregistrement dans la base de données
            ms.create(m);
            reset();

            // Affichage d'un message de succès
            showAlert("Succès", "Mission créée avec succès !", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            showAlert("Erreur", e.getMessage());
        }
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        showAlert(title, message, Alert.AlertType.ERROR);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void listMission(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/ShowMission.fxml"));
            Parent root = loader.load();

            // Obtenir la scène depuis l'événement (plus sûr que d'utiliser descriptionRec)
            ((Button) event.getSource()).getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de ShowMission.fxml", e);
        }
    }

    private void remplirComboBoxStatut() {
        statut.getItems().addAll("En cours", "Expiré");
    }


    void reset() {
        this.description.clear();
        this.points_recompense.clear();
    }

    @FXML
    public void initialize() {
        try {
            idRec.getItems().addAll(rs.getAllDescription()); // Ajouter directement les catégories
        } catch (Exception e) {
            e.printStackTrace(); // Afficher l'exception si elle se produit
        }
        remplirComboBoxStatut();

    }

    @FXML
    void mission(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/mission.fxml"));
            Parent root = loader.load();

            // Obtenir la scène depuis l'événement (plus sûr que d'utiliser descriptionRec)
            ((Button) event.getSource()).getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de ShowRecompense.fxml", e);
        }
    }

    @FXML
    void recompense(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/recompense.fxml"));
            Parent root = loader.load();

            // Obtenir la scène depuis l'événement (plus sûr que d'utiliser descriptionRec)
            ((Button) event.getSource()).getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de ShowRecompense.fxml", e);
        }
    }

    @FXML
    void showDash(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
        try {
            Parent root = loader.load();
            description.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

