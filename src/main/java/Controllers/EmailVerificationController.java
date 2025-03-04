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
    private String nom;
    private String prenom;
    private String newPassword;
    private boolean isProfileUpdate = false;

    public void initData(String email, int userId) {
        this.userEmail = email;
        this.userId = userId;
        this.isProfileUpdate = false;
        sendVerificationCode();
    }

    public void initProfileUpdate(int userId, String nom, String prenom, String newEmail, String newPassword, String code) {
        this.userId = userId;
        this.nom = nom;
        this.prenom = prenom;
        this.userEmail = newEmail;
        this.newPassword = newPassword;
        this.verificationCode = code;
        this.isProfileUpdate = true;
        messageLabel.setText("Code de vérification envoyé à votre nouvelle adresse email");
        messageLabel.setStyle("-fx-text-fill: green;");
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
            messageLabel.setText("Veuillez entrer le code de vérification");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (enteredCode.equals(verificationCode)) {
            try {
                if (isProfileUpdate) {
                    // Update user profile with new email
                    Connection conn = MyDb.getInstance().getConnection();
                    String query = newPassword.isEmpty() ?
                        "UPDATE user SET nom = ?, prenom = ?, email = ? WHERE id = ?" :
                        "UPDATE user SET nom = ?, prenom = ?, email = ?, mot_de_passe = ? WHERE id = ?";
                    
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, nom);
                        pstmt.setString(2, prenom);
                        pstmt.setString(3, userEmail);
                        if (!newPassword.isEmpty()) {
                            pstmt.setString(4, newPassword);
                            pstmt.setInt(5, userId);
                        } else {
                            pstmt.setInt(4, userId);
                        }
                        pstmt.executeUpdate();
                        
                        // Return to profile page
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserProfile.fxml"));
                        Parent root = loader.load();
                        UserProfileController controller = loader.getController();
                        controller.setUserId(userId);
                        Stage stage = (Stage) verificationCodeField.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    }
                } else {
                    // Original email verification logic
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontoffice.fxml"));
                    Parent root = loader.load();
                    FrontOffice controller = loader.getController();
                    controller.setUserId(userId);
                    Stage stage = (Stage) verificationCodeField.getScene().getWindow();
                    stage.setScene(new Scene(root));
                }
            } catch (Exception e) {
                messageLabel.setText("Erreur lors de la mise à jour du profil");
                messageLabel.setStyle("-fx-text-fill: red;");
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Code de vérification invalide");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    protected void handleResendCode() {
        sendVerificationCode();
    }
} 