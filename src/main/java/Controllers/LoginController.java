package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.MyDb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField emailField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private Hyperlink signupLink;
    
    @FXML
    private Label messageLabel;

    @FXML
    protected void handleLogin() throws IOException {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs");
            return;
        }

        try {
            Connection conn = MyDb.getInstance().getConnection();
            String query = "SELECT id, type_user FROM user WHERE email = ? AND mot_de_passe = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                messageLabel.setStyle("-fx-text-fill: #C6B9AB;");
                messageLabel.setText("Connexion réussie!");
                
                // Get current stage
                Stage stage = (Stage) loginButton.getScene().getWindow();
                
                // Load AddPack view
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AddPack.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                
                // Configure and show new scene
                stage.setScene(scene);
                stage.setTitle("Gestion Pack");
                stage.setWidth(1350);
                stage.show();
            } else {
                messageLabel.setText("Email ou mot de passe incorrect");
            }
        } catch (SQLException e) {
            messageLabel.setText("Erreur de connexion: " + e.getMessage());
        }
    }

    @FXML
    protected void switchToSignup() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Signup_new.fxml"));
            Stage stage = (Stage) signupLink.getScene().getWindow();
            Scene currentScene = stage.getScene();
            Scene scene = new Scene(fxmlLoader.load(), currentScene.getWidth(), currentScene.getHeight());
            stage.setScene(scene);
            stage.setTitle("Inscription");
        } catch (Exception e) {
            messageLabel.setText("Erreur lors du passage à l'inscription: " + e.getMessage());
        }
    }
} 