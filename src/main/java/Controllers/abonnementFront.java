package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Destination;
import models.Pack;
import services.CategorieService;
import services.ServiceAbonnement;
import services.ServicePack;

import java.io.IOException;
import java.util.List;

public class abonnementFront {

    @FXML
    private ComboBox<String> categorie;
    ServiceAbonnement serviceAbonnement = new ServiceAbonnement();
    ServicePack servicePack = new ServicePack();

    @FXML
    private GridPane gridContainer;
    CategorieService cs = new CategorieService();


    @FXML
    void initialize() {
        try {
            categorie.getItems().addAll(cs.getAllNames()); // Ajouter directement les catégories
        } catch (Exception e) {
            e.printStackTrace(); // Afficher l'exception si elle se produit
        }

        // Show the dropdown when the mouse enters
        categorie.setOnMouseEntered(event -> categorie.show());

        // Hide the dropdown when the mouse leaves
        categorie.setOnMouseExited(event -> categorie.hide());
        List<Pack> packList = null;
        try {
            packList = this.servicePack.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int column = 0, row = 0;
        for(Pack pack : packList) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PackCard.fxml"));
                AnchorPane card = loader.load();

                PackCard cardController = loader.getController();
                cardController.setPackData(pack); // Set data

                gridContainer.add(card, column, row);
                column++;
                if (column == 3) { // 3 cards per row
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
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
    void goMiss(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/missionFront.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goDest(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FrontOffice.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void logOut(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        try {
            Parent root = loader.load();
            categorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
