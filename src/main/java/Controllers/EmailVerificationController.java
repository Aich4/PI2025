package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.EmailUtil;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class EmailVerificationController {
    @FXML private TextField verificationCodeField;
    @FXML private Label messageLabel;

    private String userEmail;
    private String verificationCode;
    private int userId;

    public void initData(String email, int userId) {
        this.userEmail = email;
        this.userId = userId;
        sendVerificationCode();
    }

    private void sendVerificationCode() {
        // Generate a 6-digit verification code
        Random random = new Random();
        verificationCode = String.format("%06d", random.nextInt(1000000));

        try {
            String subject = "TrekSwap Email Verification";
            String message = String.format("Your verification code is: %s\n\n" +
                    "Please enter this code in the application to verify your email address.\n\n" +
                    "If you didn't request this code, please ignore this email.", verificationCode);

            EmailUtil.sendEmail(userEmail, subject, message);
            messageLabel.setText("Verification code sent to your email");
            messageLabel.setStyle("-fx-text-fill: green;");
        } catch (Exception e) {
            messageLabel.setText("Failed to send verification code");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleVerify() {
        String enteredCode = verificationCodeField.getText().trim();
        
        if (enteredCode.isEmpty()) {
            messageLabel.setText("Please enter the verification code");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (enteredCode.equals(verificationCode)) {
            try {
                // Update user's verified status in database
                Connection conn = MyDb.getInstance().getConnection();
                String query = "UPDATE user SET is_verified = true WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setInt(1, userId);
                    pstmt.executeUpdate();
                }

                // Load the front office page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontoffice.fxml"));
                Parent root = loader.load();
                
                FrontOffice controller = loader.getController();
                controller.setUserId(userId);
                
                Stage stage = (Stage) verificationCodeField.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (Exception e) {
                messageLabel.setText("Error verifying email");
                messageLabel.setStyle("-fx-text-fill: red;");
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Invalid verification code");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    protected void handleResendCode() {
        sendVerificationCode();
    }
} 