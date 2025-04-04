package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import models.Partenaire;
import services.PartenaireService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PartenaireListController {

    @FXML
    private ListView<String> listPartenaire;

    private PartenaireService ps = new PartenaireService();
    private int categoryId; // This will store the passed category ID

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        afficherPartenaires();
    }

    private void afficherPartenaires() {
        try {
            List<Partenaire> partenaires = ps.getAll();
            List<String> partenairesFiltrés = new ArrayList<>();

            for (Partenaire p : partenaires) {
                if (p.getId_categorie() == categoryId) {
                    partenairesFiltrés.add("nom : "+p.getNom() +  " - Description :"+ p.getDescription() + " - Email :" + p.getEmail()+ " - Adresse :" + p.getAdresse());
                }
            }

            listPartenaire.getItems().setAll(partenairesFiltrés);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goAbon(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/abonnementFront.fxml"));
        try {
            Parent root = loader.load();
            listPartenaire.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void addRecl(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Test.fxml"));
        try {
            Parent root = loader.load();
            listPartenaire.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void goMiss(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/missionFront.fxml"));
        try {
            Parent root = loader.load();
            listPartenaire.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showDest(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Frontoffice.fxml"));
        try {
            Parent root = loader.load();
            listPartenaire.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void logOut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        try {
            Parent root = loader.load();
            listPartenaire.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
