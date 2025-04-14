package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.MyDb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    protected void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs");
            return;
        }

        try {
            Connection conn = MyDb.getInstance().getConnection();
            String query = "SELECT * FROM user WHERE email = ? AND mot_de_passe = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int userId = rs.getInt("id");
                    String userType = rs.getString("type_user");
                    
                    FXMLLoader loader;
                    if ("admin".equalsIgnoreCase(userType)) {
                        loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
                    } else {
                        loader = new FXMLLoader(getClass().getResource("/Frontoffice.fxml"));
                    }
                    
                    Parent root = loader.load();
                    
                    if ("admin".equalsIgnoreCase(userType)) {
                        Dashboard controller = loader.getController();
                        controller.setUserId(userId);
                    } else {
                        FrontOffice controller = loader.getController();
                        controller.setUserId(userId);
                    }
                    
                    Stage stage = (Stage) emailField.getScene().getWindow();
                    stage.setScene(new Scene(root));
                } else {
                    messageLabel.setText("Email ou mot de passe incorrect");
                }
            }
        } catch (SQLException | IOException e) {
            messageLabel.setText("Erreur lors de la connexion");
            e.printStackTrace();
        }
    }
} 