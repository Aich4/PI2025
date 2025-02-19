package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private TextField disponibilite;

    @FXML
    void createRec(ActionEvent event) {

        Recompense r = new Recompense(this.descriptionRec.getText(),Integer.parseInt(this.cout_en_points.getText()), this.disponibilite.getText());
        try {
            this.rs.create(r);
            reset();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Recompense created");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

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
        this.disponibilite.clear();
    }

}