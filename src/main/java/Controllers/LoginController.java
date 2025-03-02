package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.MyDb;
import utils.SecurityUtil;
import utils.EmailUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.io.FileNotFoundException;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;
    
    private static final String ADMIN_PASSWORD = "12345678";

    @FXML
    protected void handleAdminAccess() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Admin Access");
        dialog.setHeaderText("Enter Admin Password");
        dialog.setContentText("Password:");

        // Get the password field from the dialog
        PasswordField pwd = new PasswordField();
        pwd.setPrefWidth(dialog.getEditor().getPrefWidth());
        dialog.getDialogPane().setContent(pwd);

        // Request focus on the password field by default
        pwd.requestFocus();

        // Convert the result using the password field instead of the editor
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return pwd.getText();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(password -> {
            if (password.equals(ADMIN_PASSWORD)) {
                try {
                    // Load the admin login page
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminLogin.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) emailField.getScene().getWindow();
                    stage.setScene(new Scene(root));
                } catch (Exception e) {
                    messageLabel.setText("Failed to load admin login page");
                    messageLabel.setStyle("-fx-text-fill: red;");
                    e.printStackTrace();
                }
            } else {
                messageLabel.setText("Invalid admin password");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });
    }

    @FXML
    protected void handleLogin() {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please fill in all fields");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            Connection conn = MyDb.getInstance().getConnection();
            String query = "SELECT id, mot_de_passe, type_user, is_verified FROM user WHERE email = ?";
            
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String hashedPassword = rs.getString("mot_de_passe");
                    String userType = rs.getString("type_user");
                    int userId = rs.getInt("id");
                    boolean isVerified = rs.getBoolean("is_verified");

                    if (SecurityUtil.checkPassword(password, hashedPassword)) {
                        if (!isVerified && !email.equals("admin")) {
                            // Load email verification page
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EmailVerification.fxml"));
                            Parent root = loader.load();
                            
                            EmailVerificationController controller = loader.getController();
                            controller.initData(email, userId);
                            
                            Stage stage = (Stage) emailField.getScene().getWindow();
                            stage.setScene(new Scene(root));
                            return;
                        }

                        // Load appropriate page based on user type
                        if (email.equals("admin")) {
                            loadDashboard(userId);
                        } else {
                            loadFrontOffice(userId);
                        }
                    } else {
                        messageLabel.setText("Invalid email or password");
                        messageLabel.setStyle("-fx-text-fill: red;");
                    }
                } else {
                    messageLabel.setText("Invalid email or password");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            }
        } catch (Exception e) {
            messageLabel.setText("Error during login: " + e.getMessage());
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleGmailLogin() {
        try {
            // Initialize Gmail service if not already initialized
            EmailUtil.initializeGmailService();
            
            String email = emailField.getText().trim();
            if (email.isEmpty()) {
                messageLabel.setText("Please enter your Gmail address");
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            // Check if user exists in database
            Connection conn = MyDb.getInstance().getConnection();
            String query = "SELECT * FROM user WHERE email = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int userId = rs.getInt("id");
                    String type = rs.getString("type_user");

                    // Navigate based on user type
                    if ("admin".equals(type)) {
                        loadDashboard(userId);
                    } else {
                        loadFrontOffice(userId);
                    }
                } else {
                    messageLabel.setText("No account found with this Gmail address");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            }
        } catch (Exception e) {
            messageLabel.setText("Gmail login failed. Please try again.");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleForgotPassword() {
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            messageLabel.setText("Please enter your email address");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            Connection conn = MyDb.getInstance().getConnection();
            String query = "SELECT id FROM user WHERE email = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    try {
                        // Generate reset token
                        String resetToken = SecurityUtil.generateResetToken(email);
                        // Send reset email
                        EmailUtil.sendPasswordResetEmail(email, resetToken);
                        messageLabel.setText("Password reset instructions sent to your email");
                        messageLabel.setStyle("-fx-text-fill: green;");
                    } catch (FileNotFoundException e) {
                        messageLabel.setText("Email service not configured. Please contact administrator.");
                        messageLabel.setStyle("-fx-text-fill: red;");
                        e.printStackTrace();
                    } catch (Exception e) {
                        messageLabel.setText("Failed to send reset email. Please try again later.");
                        messageLabel.setStyle("-fx-text-fill: red;");
                        e.printStackTrace();
                    }
                } else {
                    messageLabel.setText("No account found with this email address");
                    messageLabel.setStyle("-fx-text-fill: red;");
                }
            }
        } catch (Exception e) {
            messageLabel.setText("An error occurred. Please try again.");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleSignUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Signup_new.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            messageLabel.setText("Failed to load registration page");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void loadDashboard(int userId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
            Parent root = loader.load();
            
            Dashboard controller = loader.getController();
            controller.setUserId(userId);
            
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            messageLabel.setText("Failed to load dashboard");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void loadFrontOffice(int userId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontoffice.fxml"));
            Parent root = loader.load();
            
            FrontOffice controller = loader.getController();
            controller.setUserId(userId);
            
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            messageLabel.setText("Failed to load front office");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }
} 