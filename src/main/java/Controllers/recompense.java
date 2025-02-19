package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import models.Mission;
import models.Recompense;
import services.RecompenseService;

import java.io.IOException;

public class recompense {

     RecompenseService rs;

    public recompense() {
        this.rs = new RecompenseService();
    }

    @FXML
    private TextField cout_en_points;

    @FXML
    private TextArea descriptionRec;


    @FXML
    private ComboBox<String> disponibilite;


    @FXML
    void createRec(ActionEvent event) {
        try {
            // Récupération et validation des champs
            String descriptionText = descriptionRec.getText().trim();
            String coutText = cout_en_points.getText().trim();
            String disponibiliteValue = disponibilite.getValue();

            // Vérification des champs obligatoires
            if (descriptionText.isEmpty()) {
                showAlert("Erreur", "La description ne peut pas être vide.");
                return;
            }

            if (coutText.isEmpty()) {
                showAlert("Erreur", "Le coût en points est requis.");
                return;
            }

            int cout;
            try {
                cout = Integer.parseInt(coutText);
                if (cout < 0) {
                    showAlert("Erreur", "Le coût en points doit être un nombre positif.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Erreur", "Veuillez entrer un nombre valide pour le coût en points.");
                return;
            }

            if (disponibiliteValue == null || disponibiliteValue.isEmpty()) {
                showAlert("Erreur", "Veuillez sélectionner une disponibilité.");
                return;
            }

            // Création de la récompense
            Recompense r = new Recompense(descriptionText, cout, disponibiliteValue);

            // Enregistrement dans la base de données
            rs.create(r);
            reset();

            // Affichage d'un message de succès
            showAlert("Succès", "Récompense créée avec succès !", Alert.AlertType.INFORMATION);

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


    void reset() {
        this.descriptionRec.clear();
        this.cout_en_points.clear();
        this.disponibilite.getItems().clear();;
    }

    @FXML
    public void initialize() {

        remplirComboBoxDisponibilite();

    }

}