package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import models.Recompense;
import services.RecompenseService;

import java.io.IOException;

public class updateRec {

    private Recompense recompense;

    @FXML
    private TextField cout_en_points;

    @FXML
    private TextArea descriptionRec;

    @FXML
    private ComboBox<String> disponibilite;


    @FXML
    void updateRec(ActionEvent event) {
        try {
            // Récupérer les données modifiées
            String description = descriptionRec.getText().trim();
            int cout = Integer.parseInt(cout_en_points.getText().trim());
            String dispo = disponibilite.getValue();

            if (description.isEmpty() || dispo == null) {
                showAlert("Erreur", "Tous les champs doivent être remplis.");
                return;
            }

            // Mise à jour de la récompense
            recompense.setDescription(description);
            recompense.setCout_en_points(cout);
            recompense.setDisponibilite(dispo);

            // Mettre à jour dans la base de données via le service
            RecompenseService rs = new RecompenseService();
            rs.update(recompense); // Appeler la méthode update dans le service

            // Retour à la page des récompenses
            ShowRec(event);

        } catch (NumberFormatException e) {
            showAlert("Erreur", "Le coût en points doit être un nombre valide.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showAlert(String title, String message) {
        // Implémenter un mécanisme d'affichage des alertes d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setRecompense(Recompense recompense) {
        this.recompense = recompense;
        if (recompense != null) {
            descriptionRec.setText(recompense.getDescription());
            cout_en_points.setText(String.valueOf(recompense.getCout_en_points()));
            disponibilite.setValue(recompense.getDisponibilite());
        }
    }

    // Méthode d'initialisation des champs
    @FXML
    public void initialize() {

        remplirComboBoxDisponibilite();
    }

    private void remplirComboBoxDisponibilite() {
        disponibilite.getItems().addAll("Disponible", "Indisponible");
    }

    @FXML
    void ShowRec(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/ShowRecompense.fxml"));
            Parent root = loader.load();

            // Obtenir la scène depuis l'événement (plus sûr que d'utiliser descriptionRec)
            ((Button) event.getSource()).getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de ShowRecompense.fxml", e);
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



}
