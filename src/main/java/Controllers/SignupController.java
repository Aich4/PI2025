package Controllers;

import utils.MyDb;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SignupController implements Initializable {
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private ComboBox<String> userTypeComboBox;
    @FXML private ImageView profileImageView;
    @FXML private ImageView cardPhoto1View;
    @FXML private ImageView cardPhoto2View;
    @FXML private VBox cardPhotosBox;
    @FXML private Button signupButton;
    @FXML private Hyperlink loginLink;
    @FXML private Label messageLabel;

    private String profilePhotoPath;
    private String cardPhoto1Path;
    private String cardPhoto2Path;
    private static final String UPLOAD_DIR = "uploads/";

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeComboBox.setItems(FXCollections.observableArrayList(
            "Touriste", "Commerçant", "Guide"
        ));

        userTypeComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean showCardPhotos = "Commerçant".equals(newVal) || "Guide".equals(newVal);
            cardPhotosBox.setVisible(showCardPhotos);
            cardPhotosBox.setManaged(showCardPhotos);
            
            // Clear card photos if switching to Touriste
            if ("Touriste".equals(newVal)) {
                cardPhoto1View.setImage(null);
                cardPhoto2View.setImage(null);
                cardPhoto1Path = null;
                cardPhoto2Path = null;
            }
        });

        // Create uploads directory if it doesn't exist
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            System.err.println("Could not create upload directory: " + e.getMessage());
        }
    }

    private String saveImage(File sourceFile, String prefix) throws IOException {
        String fileName = prefix + "_" + System.currentTimeMillis() + "_" + sourceFile.getName();
        String targetPath = UPLOAD_DIR + fileName;
        
        // Copy file to uploads directory
        Files.copy(sourceFile.toPath(), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
        
        return targetPath;
    }

    @FXML
    protected void handleProfilePhotoUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        
        File file = fileChooser.showOpenDialog(profileImageView.getScene().getWindow());
        if (file != null) {
            try {
                profilePhotoPath = saveImage(file, "profile");
                Image image = new Image(file.toURI().toString());
                profileImageView.setImage(image);
            } catch (IOException e) {
                messageLabel.setText("Erreur lors de l'enregistrement de l'image: " + e.getMessage());
            }
        }
    }

    @FXML
    protected void handleCard1Upload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        
        File file = fileChooser.showOpenDialog(cardPhoto1View.getScene().getWindow());
        if (file != null) {
            try {
                cardPhoto1Path = saveImage(file, "card1");
                Image image = new Image(file.toURI().toString());
                cardPhoto1View.setImage(image);
            } catch (IOException e) {
                messageLabel.setText("Erreur lors de l'enregistrement de l'image: " + e.getMessage());
            }
        }
    }

    @FXML
    protected void handleCard2Upload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        
        File file = fileChooser.showOpenDialog(cardPhoto2View.getScene().getWindow());
        if (file != null) {
            try {
                cardPhoto2Path = saveImage(file, "card2");
                Image image = new Image(file.toURI().toString());
                cardPhoto2View.setImage(image);
            } catch (IOException e) {
                messageLabel.setText("Erreur lors de l'enregistrement de l'image: " + e.getMessage());
            }
        }
    }

    @FXML
    protected void handleSignup() {
        messageLabel.setStyle("-fx-text-fill: #B05A36;");
        
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String userType = userTypeComboBox.getValue();

        // Validation
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || 
            password.isEmpty() || confirmPassword.isEmpty() || userType == null) {
            messageLabel.setText("Veuillez remplir tous les champs");
            return;
        }

        if (profilePhotoPath == null) {
            messageLabel.setText("Veuillez ajouter une photo de profil");
            return;
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            messageLabel.setText("Veuillez entrer une adresse email valide");
            return;
        }

        if (password.length() < 6) {
            messageLabel.setText("Le mot de passe doit contenir au moins 6 caractères");
            return;
        }

        if (!password.equals(confirmPassword)) {
            messageLabel.setText("Les mots de passe ne correspondent pas");
            return;
        }

        // Only validate card photos for Commerçant and Guide
        if (!"Touriste".equals(userType)) {
            if (cardPhoto1Path == null || cardPhoto2Path == null) {
                messageLabel.setText("Veuillez ajouter les photos de votre carte professionnelle");
                return;
            }
        }

        // Database operation
        try {
            Connection conn = MyDb.getInstance().getConnection();
            String query = "INSERT INTO user (nom, prenom, email, mot_de_passe, photo_profil, type_user, photo_carte_f1, photo_carte_f2) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.setString(5, profilePhotoPath);
            pstmt.setString(6, userType);
            
            // Set card photos to null for Touriste
            if ("Touriste".equals(userType)) {
                pstmt.setNull(7, Types.VARCHAR);
                pstmt.setNull(8, Types.VARCHAR);
            } else {
                pstmt.setString(7, cardPhoto1Path);
                pstmt.setString(8, cardPhoto2Path);
            }

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                messageLabel.setStyle("-fx-text-fill: #C6B9AB;");
                messageLabel.setText("Inscription réussie! Veuillez vous connecter.");
                clearFields();
                
                // Automatically switch to login after 2 seconds
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        javafx.application.Platform.runLater(this::switchToLogin);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                if (e.getMessage().contains("email")) {
                    messageLabel.setText("Cette adresse email est déjà utilisée");
                } else {
                    messageLabel.setText("Un compte existe déjà avec ces informations");
                }
            } else {
                messageLabel.setText("Erreur: " + e.getMessage());
            }
        }
    }

    @FXML
    protected void switchToLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Stage stage = (Stage) loginLink.getScene().getWindow();
            Scene currentScene = stage.getScene();
            Scene scene = new Scene(fxmlLoader.load(), currentScene.getWidth(), currentScene.getHeight());
            stage.setScene(scene);
            stage.setTitle("Connexion");
        } catch (Exception e) {
            messageLabel.setText("Erreur lors du passage à la page de connexion: " + e.getMessage());
        }
    }

    private void clearFields() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
        userTypeComboBox.setValue(null);
        profileImageView.setImage(null);
        cardPhoto1View.setImage(null);
        cardPhoto2View.setImage(null);
        profilePhotoPath = null;
        cardPhoto1Path = null;
        cardPhoto2Path = null;
    }
} 