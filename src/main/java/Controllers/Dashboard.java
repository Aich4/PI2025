package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class Dashboard {

    @FXML
    private Button aa;

    @FXML
    void showAbon(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowAbonnement.fxml"));
        try {
            Parent root = loader.load();
            aa.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showDest(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListDestinationBack.fxml"));
        try {
            Parent root = loader.load();
            aa.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showMiss(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/mission.fxml"));
        try {
            Parent root = loader.load();
            aa.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showPart(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichagePartenaire.fxml"));
        try {
            Parent root = loader.load();
            aa.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showRec(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listReclamation.fxml"));
        try {
            Parent root = loader.load();
            aa.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
