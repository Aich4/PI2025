package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.EmailUtil;

public class GmailVerificationController {
    @FXML private TextField gmailField;
    @FXML private Label messageLabel;
    
    private String nom;
    private String prenom;
    private String password;
    private String type;

    public void initData(String nom, String prenom, String password, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.type = type;
    }

    @FXML
    protected void handleVerify() {
        String gmail = gmailField.getText().trim();
        
        if (gmail.isEmpty()) {
            messageLabel.setText("Please enter your Gmail address");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (!gmail.endsWith("@gmail.com")) {
            messageLabel.setText("Please enter a valid Gmail address");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            // Initialize Gmail service and attempt to send a test email
            EmailUtil.initializeGmailService();
            String subject = "TrekSwap Email Verification";
            String message = "This email confirms that your Gmail account is valid and can be used with TrekSwap.";
            
            EmailUtil.sendEmail(gmail, subject, message);

            // If email sends successfully, proceed to create account
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Signup_new.fxml"));
            Parent root = loader.load();
            
            SignupController controller = loader.getController();
            controller.createVerifiedAccount(nom, gmail, password, type);
            
            Stage stage = (Stage) gmailField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            messageLabel.setText("Failed to verify Gmail. Please check your email address and try again.");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Signup_new.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) gmailField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            messageLabel.setText("Failed to return to signup page");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
} 