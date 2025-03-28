package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.MyDb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserManagementController {
    @FXML private ListView<String> userListView;
    @FXML private Label messageLabel;
    
    private int adminId;
    private Map<String, Integer> userIdMap = new HashMap<>();

    @FXML
    public void initialize() {
        userListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double click
                String selectedItem = userListView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int selectedUserId = userIdMap.get(selectedItem);
                    showUserProfile(selectedUserId);
                }
            }
        });
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
        loadUsers();
    }

    private void loadUsers() {
        try {
            Connection conn = MyDb.getInstance().getConnection();
            String query = "SELECT id, nom, prenom, email, type_user FROM user WHERE id != ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, adminId); // Exclude the admin themselves
                ResultSet rs = pstmt.executeQuery();
                
                userListView.getItems().clear();
                userIdMap.clear();
                
                while (rs.next()) {
                    String userInfo = String.format("%s %s (%s) - %s",
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("type_user")
                    );
                    userListView.getItems().add(userInfo);
                    userIdMap.put(userInfo, rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            messageLabel.setText("Erreur lors du chargement des utilisateurs");
            e.printStackTrace();
        }
    }

    private void showUserProfile(int userId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProfileManagement.fxml"));
            Parent root = loader.load();
            
            ProfileManagementController controller = loader.getController();
            controller.setAdminView(adminId, userId);
            
            Stage stage = (Stage) userListView.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            messageLabel.setText("Erreur lors de l'ouverture du profil");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
            Parent root = loader.load();
            
            Dashboard controller = loader.getController();
            controller.setUserId(adminId);
            
            Stage stage = (Stage) userListView.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            messageLabel.setText("Erreur lors du retour au tableau de bord");
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleDeleteUser() {
        String selectedItem = userListView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            messageLabel.setText("Veuillez sélectionner un utilisateur à supprimer");
            return;
        }

        int selectedUserId = userIdMap.get(selectedItem);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet utilisateur ?");
        alert.setContentText("Cette action est irréversible.");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                // Delete user's profile picture if it exists
                Connection conn = MyDb.getInstance().getConnection();
                String photoQuery = "SELECT photo_profil FROM user WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(photoQuery)) {
                    pstmt.setInt(1, selectedUserId);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        String photoPath = rs.getString("photo_profil");
                        if (photoPath != null && !photoPath.isEmpty()) {
                            try {
                                Files.deleteIfExists(Paths.get("src/main/resources/css/profile_pics", photoPath));
                            } catch (IOException e) {
                                System.out.println("Could not delete profile picture: " + e.getMessage());
                            }
                        }
                    }
                }

                // Delete user from database
                String deleteQuery = "DELETE FROM user WHERE id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                    pstmt.setInt(1, selectedUserId);
                    pstmt.executeUpdate();
                    
                    messageLabel.setText("Utilisateur supprimé avec succès");
                    messageLabel.setStyle("-fx-text-fill: green;");
                    
                    // Refresh the user list
                    loadUsers();
                }
            } catch (SQLException e) {
                messageLabel.setText("Erreur lors de la suppression de l'utilisateur");
                e.printStackTrace();
            }
        }
    }
} 