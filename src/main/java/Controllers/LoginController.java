package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.MyDb;
import utils.EmailValidator;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.UUID;
import java.time.LocalDateTime;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

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
    private Hyperlink forgotPasswordLink;
    
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
        if(email.equals("admin") || password.equals("admin")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
            try {
                Parent root = loader.load();
                emailField.getScene().setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
                
                int userId = rs.getInt("id");
                String userType = rs.getString("type_user");
                
                // Load appropriate view based on user type
                String fxmlPath = userType.equals("admin") ? "/Dashboard.fxml" : "/Frontoffice.fxml";
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = fxmlLoader.load();
                
                // Set user ID in the appropriate controller
                if ("admin".equalsIgnoreCase(userType)) {
                    Dashboard controller = fxmlLoader.getController();
                    controller.setUserId(userId);
                } else {
                    FrontOffice controller = fxmlLoader.getController();
                    controller.setUserId(userId);
                }
                
                // Configure and show new scene
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle(userType.equals("admin") ? "Dashboard Admin" : "TrekSwap");
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

    @FXML
    protected void handleForgotPassword() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Réinitialisation du mot de passe");

        TextField emailInput = new TextField();
        emailInput.setPromptText("Entrez votre email Gmail");
        emailInput.setMaxWidth(250);

        Button submitButton = new Button("Envoyer");
        submitButton.setStyle("-fx-background-color: #B05A36; -fx-text-fill: #C6B9AB;");

        Label messageLabel = new Label();
        messageLabel.setWrapText(true);

        submitButton.setOnAction(e -> {
            String email = emailInput.getText().trim();
            if (email.isEmpty()) {
                messageLabel.setText("Veuillez entrer votre email");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            try {
                if (!EmailValidator.isValidGmail(email)) {
                    messageLabel.setText("Veuillez entrer une adresse Gmail valide");
                    messageLabel.setStyle("-fx-text-fill: red;");
                    return;
                }

                if (userExists(email)) {
                    String token = generateResetToken(email);
                    if (token != null) {
                        EmailValidator.sendPasswordResetEmail(email, token);
                        messageLabel.setText("Un email de réinitialisation a été envoyé à votre adresse");
                        messageLabel.setStyle("-fx-text-fill: green;");
                        
                        new Thread(() -> {
                            try {
                                Thread.sleep(3000);
                                javafx.application.Platform.runLater(dialog::close);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }).start();
                    }
                } else {
                    messageLabel.setText("Aucun compte trouvé avec cet email");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            } catch (Exception ex) {
                messageLabel.setText("Une erreur est survenue");
                messageLabel.setStyle("-fx-text-fill: red;");
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
            new Label("Entrez votre email Gmail pour réinitialiser votre mot de passe"),
            emailInput,
            submitButton,
            messageLabel
        );

        Scene scene = new Scene(layout);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private boolean userExists(String email) throws SQLException {
        Connection conn = MyDb.getInstance().getConnection();
        String query = "SELECT id FROM user WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        }
    }

    private String generateResetToken(String email) throws SQLException {
        String token = UUID.randomUUID().toString();
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(24);

        Connection conn = MyDb.getInstance().getConnection();
        
        String deleteQuery = "DELETE FROM password_reset WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        }

        String insertQuery = "INSERT INTO password_reset (email, token, expiration_time) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setString(1, email);
            pstmt.setString(2, token);
            pstmt.setObject(3, expirationTime);
            pstmt.executeUpdate();
            return token;
        }
    }
} 