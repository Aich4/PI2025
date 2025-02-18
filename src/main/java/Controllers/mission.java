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
    private TextField statut;

    @FXML
    private ComboBox<String> idRec;

    @FXML
    void createMission(ActionEvent event) throws Exception {

        String descriptionRec = this.idRec.getValue();
        int idRec = rs.getIdByDescrption(descriptionRec);

        Mission m = new Mission(this.description.getText(),Integer.parseInt(this.points_recompense.getText()), this.statut.getText(),idRec);
        try {
            this.ms.create(m);
            reset();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Mission created");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

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


    void reset() {
        this.description.clear();
        this.points_recompense.clear();
        this.statut.clear();
    }

    @FXML
    public void initialize() {
        try {
            idRec.getItems().addAll(rs.getAllDescription()); // Ajouter directement les catégories
        } catch (Exception e) {
            e.printStackTrace(); // Afficher l'exception si elle se produit
        }
    }

}

