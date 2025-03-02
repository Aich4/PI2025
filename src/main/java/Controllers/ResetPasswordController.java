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

    private String resetToken;
    private String userEmail;

    public void setResetToken(String token) {
        this.resetToken = token;
        // Validate token and get associated email
        this.userEmail = SecurityUtil.validateResetToken(token);
        if (this.userEmail == null) {
            messageLabel.setText("Invalid or expired reset token. Please request a new password reset.");
            messageLabel.setStyle("-fx-text-fill: red;");
            passwordField.setDisable(true);
            confirmPasswordField.setDisable(true);
        }
    }

    @FXML
    protected void handleResetPassword() {
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate passwords
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            messageLabel.setText("Please fill in all fields");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Passwords do not match");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (password.length() < 8) {
            messageLabel.setText("Password must be at least 8 characters long");
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
                    // Remove the used token
                    SecurityUtil.removeResetToken(resetToken);

                    // Show success message and redirect to login
                    messageLabel.setText("Password reset successful!");
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
                            e.printStackTrace();
                        }
                    }).start();
                } else {
                    messageLabel.setText("Failed to reset password. Please try again.");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            }
        } catch (Exception e) {
            messageLabel.setText("An error occurred. Please try again.");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
} 