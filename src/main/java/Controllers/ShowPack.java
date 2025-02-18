package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ServicePack;
import models.Pack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.MyDb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShowPack {

    @FXML
    private ListView<Pack> listview;  // Change the ListView type to Pack

    @FXML
    private VBox DetailsP;

    @FXML
    private Label nompack, desc, dure, priix, avantage, statut;

    private final ServicePack servicePack = new ServicePack(); // Service to fetch packs

    private Pack selectedPack; // Stores selected pack for modification

    @FXML
    void initialize() throws SQLException {
        System.out.println("dkhal");
        loadPacks();

        // Handle item selection
        listview.setOnMouseClicked(event -> showPackDetails());
    }

    private void loadPacks() throws SQLException {
        ObservableList<Pack> packList = FXCollections.observableArrayList();  // Use ObservableList of Packs
        String sql = "SELECT * FROM pack";

        try (Connection conn = MyDb.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (conn == null) {
                System.out.println("Erreur : Connexion à la base de données échouée !");
                return;
            } else {
                System.out.println("Connexion réussie !");
            }

            if (!rs.isBeforeFirst()) {
                System.out.println(" Aucun pack trouvé dans la base de données !");
                return;
            }

            while (rs.next()) {
                // Fetch the data from the ResultSet
                int id = rs.getInt("id_Pack");
                System.out.println("yahdik rabby " + id);
                String nomPack = rs.getString("nom_Pack");
                String description = rs.getString("description");
                float prix = rs.getFloat("prix");
                int duree = rs.getInt("duree");
                String avantages = rs.getString("avantages");
                String statut = rs.getString("statut");

                // Create a Pack object
                Pack pack = new Pack(id, nomPack, description, prix, duree, avantages, statut);

                // Add the Pack object to the list
                packList.add(pack);
                System.out.println("📦 Pack trouvé : " + nomPack);
            }

            // Set the ListView items to the pack list
            listview.setItems(packList);
            listview.refresh();
            System.out.println("✅ Liste mise à jour avec " + packList.size() + " packs.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void showPackDetails() {
        Pack selectedPack = listview.getSelectionModel().getSelectedItem();
        if (selectedPack != null) {
            // Set the details in the labels from the selected Pack
            nompack.setText(selectedPack.getNom_Pack());
            desc.setText(selectedPack.getDescription());
            dure.setText(String.valueOf(selectedPack.getDuree()));
            priix.setText(String.valueOf(selectedPack.getPrix()));
            avantage.setText(selectedPack.getAvantages());
            statut.setText(selectedPack.getStatut());
        }
    }

    @FXML
    void updatepack(ActionEvent event) {
        Pack selectedPack = listview.getSelectionModel().getSelectedItem();

        if (selectedPack != null) {
            try {
                // Open the update window with the selected Pack
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdatePack.fxml"));
                Parent root = loader.load();

                UpdatePack updatePackController = loader.getController();
                updatePackController.setPackDetails(selectedPack); // Pass selected Pack object to the update screen

                Scene currentScene = ((Node) event.getSource()).getScene();
                currentScene.setRoot(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠ Please select a pack to modify.");
        }
    }

    @FXML
    void supprimer(ActionEvent event) throws SQLException {
        // Assumons que vous avez déjà une ListView appelée 'listview' pour les packs
        Pack selectedPack = listview.getSelectionModel().getSelectedItem();
        if (selectedPack != null) {
            ServicePack servicePack = new ServicePack(); // Service de suppression du pack
            try {
                // Suppression du pack de la base de données
                servicePack.delete(selectedPack.getId_Pack());

                // Retirer le pack de la ListView pour mettre à jour l'affichage
                listview.getItems().remove(selectedPack);
                listview.refresh();

                System.out.println("✅ Pack supprimé avec succès !");  // Confirmation dans la console
            } catch (SQLException e) {
                System.out.println("❌ Erreur : Le pack n'a pas pu être supprimé de la base de données.");
                e.printStackTrace();  // Pour plus de détails sur l'erreur
            }
        } else {
            System.out.println("⚠ Aucun pack sélectionné. Veuillez sélectionner un pack à supprimer.");
        }

    }

}



















