package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.MyDb;
import utils.EmailUtil;
import utils.SecurityUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class UserProfileController {
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ImageView profileImageView;
    @FXML private Button changePhotoButton;
    @FXML private Label messageLabel;

    private int userId;
    private String currentPhotoPath;
    private static final String PROFILE_PICS_DIR = "src/main/resources/css/profile_pics";

    @FXML
    public void initialize() {
        // Create profile pictures directory if it doesn't exist
        try {
            Files.createDirectories(Paths.get(PROFILE_PICS_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up change photo button handler
        changePhotoButton.setOnAction(event -> handleChangePhoto());
    }

    public void setUserId(int userId) {
        this.userId = userId;
        loadUserData();
    }

    @FXML
    protected void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontoffice.fxml"));
            Parent root = loader.load();
            
            FrontOffice controller = loader.getController();
            controller.setUserId(userId);
            
            Stage stage = (Stage) nomField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("TrekSwap");
        } catch (IOException e) {
            messageLabel.setText("Erreur lors du retour à l'accueil");
            e.printStackTrace();
        }
    }

    private void loadUserData() {
        try {
            Connection conn = MyDb.getInstance().getConnection();
            String query = "SELECT * FROM user WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    nomField.setText(rs.getString("nom"));
                    prenomField.setText(rs.getString("prenom"));
                    emailField.setText(rs.getString("email"));
                    currentPhotoPath = rs.getString("photo_profil");
                    
                    // Load profile picture from the uploads directory
                    if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
                        Path imagePath = Paths.get("uploads", currentPhotoPath);
                        if (Files.exists(imagePath)) {
                            try {
                                Image image = new Image(imagePath.toUri().toString());
                                if (!image.isError()) {
                                    profileImageView.setImage(image);
                                } else {
                                    loadDefaultImage();
                                }
                            } catch (Exception e) {
                                System.out.println("Error loading profile image: " + e.getMessage());
                                loadDefaultImage();
                            }
                        } else {
                            // Try the old path in case it's an old photo
                            Path oldImagePath = Paths.get(PROFILE_PICS_DIR, currentPhotoPath);
                            if (Files.exists(oldImagePath)) {
                                try {
                                    Image image = new Image(oldImagePath.toUri().toString());
                                    if (!image.isError()) {
                                        profileImageView.setImage(image);
                                    } else {
                                        loadDefaultImage();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error loading profile image from old path: " + e.getMessage());
                                    loadDefaultImage();
                                }
                            } else {
                                loadDefaultImage();
                            }
                        }
                    } else {
                        loadDefaultImage();
                    }
                }
            }
        } catch (SQLException e) {
            messageLabel.setText("Erreur lors du chargement des données");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
            loadDefaultImage();
        }
    }

    private void loadDefaultImage() {
        try {
            // Try to load from the resources directory
            Image defaultImage = new Image(getClass().getResourceAsStream("/LOGO.png"));
            if (defaultImage.isError()) {
                // If that fails, use a data URL for a simple placeholder
                defaultImage = new Image("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII=");
            }
            profileImageView.setImage(defaultImage);
        } catch (Exception e) {
            System.out.println("Could not load default profile image: " + e.getMessage());
        }
    }

    private void handleChangePhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo de profil");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(changePhotoButton.getScene().getWindow());
        if (selectedFile != null) {
            try {
                // Create the profile_pics directory if it doesn't exist
                Files.createDirectories(Paths.get(PROFILE_PICS_DIR));

                // Generate a unique filename with timestamp and original extension
                String originalFileName = selectedFile.getName();
                String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
                String fileName = System.currentTimeMillis() + extension;
                
                Path targetPath = Paths.get(PROFILE_PICS_DIR, fileName);
                
                // Load and display the image before saving to verify it's valid
                Image newImage = new Image(selectedFile.toURI().toString());
                if (newImage.isError()) {
                    throw new IOException("Invalid image file");
                }
                profileImageView.setImage(newImage);
                
                // Copy the file only if the image is valid
                Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                
                // Delete the old image if it exists
                if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
                    try {
                        Path oldPhotoPath = Paths.get(PROFILE_PICS_DIR, currentPhotoPath);
                        Files.deleteIfExists(oldPhotoPath);
                    } catch (IOException e) {
                        System.out.println("Could not delete old profile picture: " + e.getMessage());
                    }
                }
                
                // Update the database with new image path
                Connection conn = MyDb.getInstance().getConnection();
                String query = "UPDATE user SET photo_profil = ? WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, fileName);
                    pstmt.setInt(2, userId);
                    pstmt.executeUpdate();
                    
                    currentPhotoPath = fileName;
                    messageLabel.setText("Photo de profil mise à jour avec succès");
                    messageLabel.setStyle("-fx-text-fill: green;");
                }
            } catch (SQLException e) {
                messageLabel.setText("Erreur lors de la mise à jour de la base de données");
                messageLabel.setStyle("-fx-text-fill: red;");
                e.printStackTrace();
                loadDefaultImage();
            } catch (IOException e) {
                messageLabel.setText("Erreur lors du traitement de l'image");
                messageLabel.setStyle("-fx-text-fill: red;");
                e.printStackTrace();
                loadDefaultImage();
            }
        }
    }

    @FXML
    protected void handleUpdate() {
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String newEmail = emailField.getText().trim();
        String password = passwordField.getText();

        if (nom.isEmpty() || prenom.isEmpty() || newEmail.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs obligatoires");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            Connection conn = MyDb.getInstance().getConnection();
            
            // Check if email has changed
            String checkEmailQuery = "SELECT email FROM user WHERE id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkEmailQuery)) {
                checkStmt.setInt(1, userId);
                ResultSet rs = checkStmt.executeQuery();
                
                if (rs.next()) {
                    String currentEmail = rs.getString("email");
                    
                    // If email has changed, start verification process
                    if (!currentEmail.equals(newEmail)) {
                        // Generate verification code
                        Random random = new Random();
                        String verificationCode = String.format("%06d", random.nextInt(1000000));
                        
                        try {
                            // Send verification email
                            String subject = "TrekSwap - Vérification du nouvel email";
                            String message = String.format("Votre code de vérification est : %s\n\n" +
                                    "Veuillez entrer ce code dans l'application pour vérifier votre nouvelle adresse email.\n\n" +
                                    "Si vous n'avez pas demandé ce changement, veuillez ignorer cet email.", verificationCode);
                            
                            EmailUtil.sendEmail(newEmail, subject, message);
                            
                            // Store temporary data and show verification page
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EmailVerification.fxml"));
                            Parent root = loader.load();
                            
                            EmailVerificationController controller = loader.getController();
                            controller.initProfileUpdate(userId, nom, prenom, newEmail, password, verificationCode);
                            
                            Stage stage = (Stage) nomField.getScene().getWindow();
                            stage.setScene(new Scene(root));
                            return;
                        } catch (Exception e) {
                            messageLabel.setText("Erreur lors de l'envoi de l'email de vérification");
                            messageLabel.setStyle("-fx-text-fill: red;");
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }
            
            // If email hasn't changed, proceed with normal update
            String query = password.isEmpty() ?
                "UPDATE user SET nom = ?, prenom = ? WHERE id = ?" :
                "UPDATE user SET nom = ?, prenom = ?, mot_de_passe = ? WHERE id = ?";
            
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, nom);
                pstmt.setString(2, prenom);
                if (!password.isEmpty()) {
                    String hashedPassword = SecurityUtil.hashPassword(password);
                    pstmt.setString(3, hashedPassword);
                    pstmt.setInt(4, userId);
                } else {
                    pstmt.setInt(3, userId);
                }
                
                pstmt.executeUpdate();
                messageLabel.setText("Profil mis à jour avec succès");
                messageLabel.setStyle("-fx-text-fill: green;");
            }
        } catch (SQLException e) {
            messageLabel.setText("Erreur lors de la mise à jour du profil");
            messageLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer votre compte ?");
        alert.setContentText("Cette action est irréversible.");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                // Delete profile picture if it exists
                if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
                    try {
                        Files.deleteIfExists(Paths.get(PROFILE_PICS_DIR, currentPhotoPath));
                    } catch (IOException e) {
                        System.out.println("Could not delete profile picture: " + e.getMessage());
                    }
                }

                // Delete user from database
                Connection conn = MyDb.getInstance().getConnection();
                String query = "DELETE FROM user WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setInt(1, userId);
                    pstmt.executeUpdate();
                    
                    // Return to login screen
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) nomField.getScene().getWindow();
                    stage.setScene(new Scene(root));
                }
            } catch (SQLException | IOException e) {
                messageLabel.setText("Erreur lors de la suppression du compte");
                e.printStackTrace();
            }
        }
    }
} 