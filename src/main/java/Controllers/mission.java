package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import models.Mission;
import services.MissionService;


public class mission {
    MissionService ms;

    public mission() {
        this.ms = new MissionService();
    }

    @FXML
    private TextArea  description;

    @FXML
    private TextField points_recompense;

    @FXML
    private TextField statut;

    @FXML
    void createMission(ActionEvent event) {

        Mission m = new Mission(this.description.getText(),Integer.parseInt(this.points_recompense.getText()), this.statut.getText());
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

    void reset() {
        this.description.clear();
        this.points_recompense.clear();
        this.statut.clear();
    }

}

