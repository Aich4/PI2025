package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Mission;
import models.Recompense;
import services.RecompenseService;

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

    void reset() {
        this.descriptionRec.clear();
        this.cout_en_points.clear();
        this.disponibilite.clear();
    }

}