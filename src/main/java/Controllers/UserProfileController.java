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
                    
                    // Load profile picture
                    if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
                        // Try to load from profile_pics directory first
                        File imageFile = new File(PROFILE_PICS_DIR, currentPhotoPath);
                        if (imageFile.exists()) {
                            try {
                                Image image = new Image(imageFile.toURI().toString());
                                if (!image.isError()) {
                                    profileImageView.setImage(image);
                                    return; // Successfully loaded profile picture
                                }
                            } catch (Exception e) {
                                System.out.println("Error loading profile image: " + e.getMessage());
                            }
                        }
                        
                        // If the file doesn't exist in profile_pics, try the full path
                        File fullPathFile = new File(currentPhotoPath);
                        if (fullPathFile.exists()) {
                            try {
                                Image image = new Image(fullPathFile.toURI().toString());
                                if (!image.isError()) {
                                    profileImageView.setImage(image);
                                    return; // Successfully loaded profile picture
                                }
                            } catch (Exception e) {
                                System.out.println("Error loading profile image from full path: " + e.getMessage());
                            }
                        }
                    }
                    
                    // If we get here, either there's no profile picture or loading failed
                    loadDefaultImage();
                }
            }
        } catch (SQLException e) {
            messageLabel.setText("Erreur lors du chargement des données");
            e.printStackTrace();
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
                System.out.println("Saving profile picture to: " + targetPath.toAbsolutePath());
                
                // Copy the new image
                Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                
                // Delete the old image if it exists
                if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
                    try {
                        Path oldPhotoPath = Paths.get(PROFILE_PICS_DIR, currentPhotoPath);
                        System.out.println("Deleting old profile picture: " + oldPhotoPath.toAbsolutePath());
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
                    
                    // Update current photo path and display
                    currentPhotoPath = fileName;
                    Image newImage = new Image(targetPath.toUri().toString());
                    profileImageView.setImage(newImage);
                    
                    messageLabel.setText("Photo de profil mise à jour avec succès");
                    messageLabel.setStyle("-fx-text-fill: green;");
                }
            } catch (IOException | SQLException e) {
                messageLabel.setText("Erreur lors de la mise à jour de la photo");
                messageLabel.setStyle("-fx-text-fill: red;");
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void handleUpdate() {
        String nom = nomField.getText().trim();
        String prenom = prenomField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty()) {
            messageLabel.setText("Veuillez remplir tous les champs obligatoires");
            return;
        }

        try {
            Connection conn = MyDb.getInstance().getConnection();
            String query = password.isEmpty() ?
                "UPDATE user SET nom = ?, prenom = ?, email = ? WHERE id = ?" :
                "UPDATE user SET nom = ?, prenom = ?, email = ?, mot_de_passe = ? WHERE id = ?";
            
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, nom);
                pstmt.setString(2, prenom);
                pstmt.setString(3, email);
                if (!password.isEmpty()) {
                    pstmt.setString(4, password);
                    pstmt.setInt(5, userId);
                } else {
                    pstmt.setInt(4, userId);
                }
                
                pstmt.executeUpdate();
                messageLabel.setText("Profil mis à jour avec succès");
                messageLabel.setStyle("-fx-text-fill: green;");
            }
        } catch (SQLException e) {
            messageLabel.setText("Erreur lors de la mise à jour du profil");
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