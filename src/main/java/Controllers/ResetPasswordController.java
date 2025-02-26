package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ResetPasswordController {
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button resetButton;
    @FXML private Label messageLabel;

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    @FXML
    protected void handlePasswordReset() {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate passwords
        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            messageLabel.setText("Les mots de passe ne correspondent pas");
            return;
        }

        if (newPassword.length() < 8) {
            messageLabel.setText("Le mot de passe doit contenir au moins 8 caractères");
            return;
        }

        try {
            if (isTokenValid(token)) {
                String email = getEmailFromToken(token);
                if (updatePassword(email, newPassword)) {
                    messageLabel.setText("Mot de passe réinitialisé avec succès");
                    messageLabel.setStyle("-fx-text-fill: green;");
                    
                    // Close the window after 2 seconds
                    new Thread(() -> {
                        try {
                            Thread.sleep(2000);
                            javafx.application.Platform.runLater(() -> {
                                Stage stage = (Stage) resetButton.getScene().getWindow();
                                stage.close();
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } else {
                    messageLabel.setText("Erreur lors de la réinitialisation du mot de passe");
                }
            } else {
                messageLabel.setText("Le lien de réinitialisation est invalide ou a expiré");
            }
        } catch (SQLException e) {
            messageLabel.setText("Une erreur est survenue");
            e.printStackTrace();
        }
    }

    private boolean isTokenValid(String token) throws SQLException {
        Connection conn = MyDb.getInstance().getConnection();
        String query = "SELECT expiration_time FROM password_reset WHERE token = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                LocalDateTime expirationTime = rs.getObject("expiration_time", LocalDateTime.class);
                return LocalDateTime.now().isBefore(expirationTime);
            }
            return false;
        }
    }

    private String getEmailFromToken(String token) throws SQLException {
        Connection conn = MyDb.getInstance().getConnection();
        String query = "SELECT email FROM password_reset WHERE token = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, token);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
            return null;
        }
    }

    private boolean updatePassword(String email, String newPassword) throws SQLException {
        Connection conn = MyDb.getInstance().getConnection();
        String updateQuery = "UPDATE user SET mot_de_passe = ? WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, email);
            int rowsAffected = pstmt.executeUpdate();

            // Delete the used token
            String deleteQuery = "DELETE FROM password_reset WHERE email = ?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                deleteStmt.setString(1, email);
                deleteStmt.executeUpdate();
            }

            return rowsAffected > 0;
        }
    }
} 