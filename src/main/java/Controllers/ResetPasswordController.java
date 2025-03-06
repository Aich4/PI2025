package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import utils.MyDb;
import utils.SecurityUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResetPasswordController {
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label messageLabel;

    private String userEmail;

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    @FXML
    protected void handleResetPassword() {
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate passwords
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Les mots de passe ne correspondent pas");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (password.length() < 8) {
            messageLabel.setText("Le mot de passe doit contenir au moins 8 caractères");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Hash the new password

            String hashedPassword = SecurityUtil.hashPassword(password);


            // Update password in database
            Connection conn = MyDb.getInstance().getConnection();
            String query = "UPDATE user SET mot_de_passe = ? WHERE email = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, hashedPassword);

                pstmt.setString(2, userEmail);
                int updated = pstmt.executeUpdate();

                if (updated > 0) {
                    // Show success message and redirect to login
                    messageLabel.setText("Mot de passe réinitialisé avec succès!");
                    messageLabel.setStyle("-fx-text-fill: green;");

                    // Redirect to login after 2 seconds
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                            javafx.application.Platform.runLater(() -> {
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
                                    Parent root = loader.load();
                                    Stage stage = (Stage) passwordField.getScene().getWindow();
                                    stage.setScene(new Scene(root));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }).start();
                } else {
                    messageLabel.setText("Échec de la réinitialisation du mot de passe. Veuillez réessayer.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            }
        } catch (Exception e) {
            messageLabel.setText("Une erreur s'est produite. Veuillez réessayer.");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
} 