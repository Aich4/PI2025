package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.SecurityUtil;

public class ResetPasswordCodeController {
    @FXML private TextField resetCodeField;
    @FXML private Label messageLabel;
    
    private String userEmail;
    private String expectedCode;

    public void initData(String email, String code) {
        this.userEmail = email;
        this.expectedCode = code;
    }

    @FXML
    protected void handleVerifyCode() {
        String enteredCode = resetCodeField.getText().trim();
        
        if (enteredCode.isEmpty()) {
            messageLabel.setText("Veuillez entrer le code de réinitialisation");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        if (enteredCode.equals(expectedCode)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ResetPassword.fxml"));
                Parent root = loader.load();
                
                ResetPasswordController controller = loader.getController();
                controller.setUserEmail(userEmail);
                
                Stage stage = (Stage) resetCodeField.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (Exception e) {
                messageLabel.setText("Erreur lors du chargement de la page de réinitialisation");
                messageLabel.setStyle("-fx-text-fill: red;");
                e.printStackTrace();
            }
        } else {
            messageLabel.setText("Code de réinitialisation invalide");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    protected void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) resetCodeField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            messageLabel.setText("Erreur lors du retour à la page de connexion");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
} 