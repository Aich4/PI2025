package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Destination;
import models.Mission;
import services.CategorieService;
import services.MissionService;

import java.io.IOException;
import java.util.List;

public class missionFront {
    private int userId;
    @FXML
    private ListView<Mission> listMission;
    MissionService ms = new MissionService();
    Mission mission = new Mission();
    public void setUserId(int userId) {
        this.userId = userId;
    }
    @FXML
    private ComboBox<String> categorie;
    CategorieService cs = new CategorieService();

    @FXML
    public void initialize() {

        try {
            categorie.getItems().addAll(cs.getAllNames()); // Ajouter directement les catégories
        } catch (Exception e) {
            e.printStackTrace(); // Afficher l'exception si elle se produit
        }

        // Show the dropdown when the mouse enters
        categorie.setOnMouseEntered(event -> categorie.show());

        // Hide the dropdown when the mouse leaves
        categorie.setOnMouseExited(event -> categorie.hide());
        try {
            // Récupérer toutes les missions depuis la base de données
            List<Mission> missions = ms.getAll();
            ObservableList<Mission> observableMissions = FXCollections.observableArrayList(missions);

            // Associer les missions à la ListView
            listMission.setItems(observableMissions);

            // Personnalisation de l'affichage avec une CellFactory
            listMission.setCellFactory(param -> new ListCell<Mission>() {
                @Override
                protected void updateItem(Mission mission, boolean empty) {
                    super.updateItem(mission, empty);

                    if (empty || mission == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // Create Label
                        Label label = new Label("Description: " + mission.getDescription() + "\n" +
                                "Points de récompense: " + mission.getPoints_recompense() + "\n" +
                                "Statut: " + mission.getStatut() + "\n" +
                                "ID Récompense: " + mission.getIdRec());

                        // Create "Choisir" Button
                        Button choisirButton = new Button("Choisir");
                        choisirButton.setOnAction(event -> {
                            System.out.println("Mission choisie: " + mission.getDescription());
                            // Add your logic here (e.g., update UI, store selected mission, etc.)
                        });

                        // Layout: HBox to align Label and Button
                        HBox hbox = new HBox(100, label, choisirButton);
                        setGraphic(hbox);
                    }
                }
            });


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }


    @FXML
    void addRecl(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Test.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goAbon(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/abonnementFront.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void goDest(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontoffice.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void showProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserProfile.fxml"));
            Parent root = loader.load();

            UserProfileController controller = loader.getController();
            controller.setUserId(userId);

            categorie.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading profile view: " + e.getMessage());
        }
    }

    @FXML
    protected void logOut() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) listMission.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
