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

public class ProfileManagementController {
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private ImageView profileImageView;
    @FXML private Label messageLabel;
    @FXML private Button updateButton;
    @FXML private Button changePhotoButton;

    private int userId;
    private String currentPhotoPath;
    private boolean isAdmin;
    private int targetUserId; // The ID of the user being viewed/edited
    
    // Define the base directory for profile pictures
    private static final String PROFILE_PICS_DIR = "src/main/resources/css/profile_pics";

    @FXML
    public void initialize() {
        // Create profile pictures directory if it doesn't exist
        try {
            Files.createDirectories(Paths.get(PROFILE_PICS_DIR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserData(int currentUserId, int targetUserId, boolean isAdmin) {
        this.userId = currentUserId;
        this.targetUserId = targetUserId;
        this.isAdmin = isAdmin;
        
        // If admin is viewing another user's profile
        if (isAdmin && currentUserId != targetUserId) {
            // Disable update functionality
            if (updateButton != null) updateButton.setVisible(false);
            if (changePhotoButton != null) changePhotoButton.setVisible(false);
            if (passwordField != null) passwordField.setVisible(false);
            
            // Make fields non-editable
            nomField.setEditable(false);
            prenomField.setEditable(false);
            emailField.setEditable(false);
        }
        
        loadUserData();
    }

    private void loadUserData() {
        try {
            Connection conn = MyDb.getInstance().getConnection();
            String query = "SELECT * FROM user WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, targetUserId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    nomField.setText(rs.getString("nom"));
                    prenomField.setText(rs.getString("prenom"));
                    emailField.setText(rs.getString("email"));
                    currentPhotoPath = rs.getString("photo_profil");
                    
                    if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
                        File imageFile = new File(PROFILE_PICS_DIR, currentPhotoPath);
                        if (imageFile.exists()) {
                            Image image = new Image(imageFile.toURI().toString());
                            profileImageView.setImage(image);
                        } else {
                            loadDefaultImage();
                        }
                    } else {
                        loadDefaultImage();
                    }
                }
            }
        } catch (SQLException e) {
            messageLabel.setText("Erreur lors du chargement des données");
            e.printStackTrace();
        }
    }

    private void loadDefaultImage() {
        Image defaultImage = new Image(getClass().getResourceAsStream("/css/profile_pics/default_profile.png"));
        profileImageView.setImage(defaultImage);
    }

    @FXML
    protected void handleChangePhoto() {
        if (isAdmin && userId != targetUserId) {
            messageLabel.setText("Les administrateurs ne peuvent pas modifier les photos des autres utilisateurs");
            return;
        }
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une photo de profil");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );
        
        File selectedFile = fileChooser.showOpenDialog(profileImageView.getScene().getWindow());
        if (selectedFile != null) {
            try {
                String fileName = "profile_" + targetUserId + "_" + System.currentTimeMillis() + 
                                selectedFile.getName().substring(selectedFile.getName().lastIndexOf('.'));
                
                Path targetPath = Paths.get(PROFILE_PICS_DIR, fileName);
                
                if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
                    try {
                        Files.deleteIfExists(Paths.get(PROFILE_PICS_DIR, currentPhotoPath));
                    } catch (IOException e) {
                        System.out.println("Could not delete old profile picture: " + e.getMessage());
                    }
                }
                
                Files.copy(selectedFile.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                
                Image image = new Image(targetPath.toUri().toString());
                profileImageView.setImage(image);
                
                currentPhotoPath = fileName;
                updateProfilePhoto(fileName);
                
                messageLabel.setText("Photo de profil mise à jour avec succès");
                messageLabel.setStyle("-fx-text-fill: green;");
                
            } catch (IOException e) {
                messageLabel.setText("Erreur lors du changement de la photo");
                e.printStackTrace();
            }
        }
    }

    private void updateProfilePhoto(String photoFileName) {
        try {
            Connection conn = MyDb.getInstance().getConnection();
            String query = "UPDATE user SET photo_profil = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, photoFileName);
                pstmt.setInt(2, targetUserId);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            messageLabel.setText("Erreur lors de la mise à jour de la photo");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleUpdate() {
        if (isAdmin && userId != targetUserId) {
            messageLabel.setText("Les administrateurs ne peuvent pas modifier les profils des autres utilisateurs");
            return;
        }

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
                    pstmt.setInt(5, targetUserId);
                } else {
                    pstmt.setInt(4, targetUserId);
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
        // Only allow admin to delete other users, or users to delete their own account
        if (!isAdmin && userId != targetUserId) {
            messageLabel.setText("Vous n'avez pas la permission de supprimer ce compte");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce compte ?");
        alert.setContentText("Cette action est irréversible.");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                if (currentPhotoPath != null && !currentPhotoPath.isEmpty()) {
                    try {
                        Files.deleteIfExists(Paths.get(PROFILE_PICS_DIR, currentPhotoPath));
                    } catch (IOException e) {
                        System.out.println("Could not delete profile picture: " + e.getMessage());
                    }
                }

                Connection conn = MyDb.getInstance().getConnection();
                String query = "DELETE FROM user WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setInt(1, targetUserId);
                    pstmt.executeUpdate();
                    
                    // If admin deleted another user's account, return to dashboard
                    // If user deleted their own account, return to login
                    String returnPath = (isAdmin && userId != targetUserId) ? "/Dashboard.fxml" : "/Login.fxml";
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(returnPath));
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

    @FXML
    protected void handleBack() {
        try {
            String returnPath = isAdmin ? "/Dashboard.fxml" : "/Frontoffice.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(returnPath));
            Parent root = loader.load();
            
            if (isAdmin) {
                Dashboard controller = loader.getController();
                controller.setUserId(userId);
            } else {
                FrontOffice controller = loader.getController();
                controller.setUserId(userId);
            }
            
            Stage stage = (Stage) nomField.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            messageLabel.setText("Erreur lors du retour au tableau de bord");
            e.printStackTrace();
        }
    }

    public void setUserId(int userId) {
        setUserData(userId, userId, false); // For normal users viewing their own profile
    }

    public void setAdminView(int adminId, int targetUserId) {
        setUserData(adminId, targetUserId, true); // For admin viewing other user's profile
    }
} 