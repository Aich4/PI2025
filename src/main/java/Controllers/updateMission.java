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
public class updateMission {

    private Mission currentMission;
    MissionService ms = new MissionService();
    RecompenseService rs;

    public updateMission() {
        this.rs = new RecompenseService();
    }

    @FXML
    private TextArea description;

    @FXML
    private ComboBox<String> idRec;

    @FXML
    private TextField points_recompense;

    @FXML
    private ComboBox<String> statut;

    @FXML
    void updateMission(ActionEvent event) {
        try {
            String descriptionText = description.getText().trim();
            String pointsText = points_recompense.getText().trim();
            String statutValue = statut.getValue();
            String selectedRec = idRec.getValue();

            if (descriptionText.isEmpty()) {
                showAlert("Erreur", "La description ne peut pas être vide.");
                return;
            }

            int points;
            try {
                points = Integer.parseInt(pointsText);
                if (points < 0) {
                    showAlert("Erreur", "Les points de récompense doivent être positifs.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer un nombre valide pour les points de récompense.");
                return;
            }

            if (statutValue == null || selectedRec == null) {
                showAlert("Erreur", "Veuillez remplir tous les champs.");
                return;
            }

            // Mettre à jour les informations de la mission
            currentMission.setDescription(descriptionText);
            currentMission.setPoints_recompense(points);
            currentMission.setStatut(statutValue);

            // Trouver l'ID de la récompense à partir de sa description
            int idRec = rs.getIdByDescrption(selectedRec);
            currentMission.setIdRec(idRec);

            // Mettre à jour la mission dans la base de données
            ms.update(currentMission);

            // Retour à la liste des missions
            listMission(event); // Retour à la liste après la mise à jour
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Une erreur s'est produite lors de la mise à jour de la mission.");
        }
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    // Méthode pour initialiser la mission
    public void setMission(Mission mission) {
        this.currentMission = mission;

        // Remplir les champs de l'interface avec les données de la mission
        description.setText(mission.getDescription());
        points_recompense.setText(String.valueOf(mission.getPoints_recompense()));
        statut.setValue(mission.getStatut());

        // Charger la récompense associée à la mission (idRec)
        try {
            idRec.setValue(rs.getDescriptionById(mission.getIdRec())); // Assurez-vous que getDescriptionById fonctionne correctement
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listMission(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/ShowMission.fxml"));
            Parent root = loader.load();

            // Obtenir la scène depuis l'événement
            ((Button) event.getSource()).getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de ShowMission.fxml", e);
        }
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
    public void initialize() {
        try {
            idRec.getItems().addAll(rs.getAllDescription()); // Ajouter directement les catégories
        } catch (Exception e) {
            e.printStackTrace(); // Afficher l'exception si elle se produit
        }
        remplirComboBoxStatut();

    }

    private void remplirComboBoxStatut() {
        statut.getItems().addAll("En cours", "Validé");
    }


}
