package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import utils.MyDb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dashboard {

    @FXML private Button aa;
    @FXML private ListView<String> userListView;
    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML
    void showUserManagement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserManagement.fxml"));
            Parent root = loader.load();
            
            // Get the controller and set the admin ID
            UserManagementController controller = loader.getController();
            controller.setAdminId(userId);
            
            aa.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listreclamation.fxml"));
        try {
            Parent root = loader.load();
            aa.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showProfile(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileManagement.fxml"));
            Parent root = loader.load();
            
            ProfileManagementController controller = loader.getController();
            controller.setUserId(userId);
            
            aa.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        try {
            Parent root = loader.load();
            aa.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
