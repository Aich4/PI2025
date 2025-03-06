package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Abonnement;
import services.CategorieService;
import services.ServiceAbonnement;
import services.ServicePDF;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class abonnementFront {

    @FXML
    private ComboBox<String> categorie;
    ServiceAbonnement serviceAbonnement = new ServiceAbonnement();

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

        List<Abonnement> AList = null;
        try {
            AList = this.serviceAbonnement.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int column = 0, row = 0;
        for(Abonnement A : AList) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/PackCard.fxml"));
                AnchorPane card = loader.load();

                PackCard cardController = loader.getController();
                cardController.setPackData(A); // Set data

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

    @FXML
    protected void showProfile() {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserProfile.fxml"));
            try {
                Parent root = loader.load();
                categorie.getScene().setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @FXML
    private void generatePDF() {

        String filePath = "C:\\Users\\MSI\\Desktop\\integration user+aicha+dest\\src\\main\\resources" + ".pdf";
        ServicePDF.generatePDF(filePath);
        showAlert("PDF généré", " Exporté en PDF avec succès.");

        // Ouvrir le fichier PDF avec l'application par défaut
        File pdfFile = new File(filePath);
        try {
            Desktop.getDesktop().open(pdfFile);
        } catch (IOException e) {
            showAlert("Erreur", "Une erreur s'est produite lors de l'ouverture du PDF.");
            e.printStackTrace();
        }

    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




}
