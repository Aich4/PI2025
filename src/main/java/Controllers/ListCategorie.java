package Controllers;

import javafx.scene.control.ListView;
import services.CategorieService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Categorie;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import services.CategorieService;
import models.Categorie;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.util.List;
public class ListCategorie {


    CategorieService ss;

    public ListCategorie() {
        this.ss = new CategorieService();
    }

    @FXML
    private ListView<Categorie> listcategorie;

    @FXML
    void initialize() {
        afficherCategories();
    }

    private void afficherCategories() {
        try {
            List<Categorie> categories = ss.getAll();
            listcategorie.getItems().setAll(categories);

            // Personnaliser l'affichage des éléments dans la ListView
            listcategorie.setCellFactory(new Callback<>() {
                @Override
                public ListCell<Categorie> call(ListView<Categorie> param) {
                    return new ListCell<>() {
                        private final ImageView imageView = new ImageView();

                        @Override
                        protected void updateItem(Categorie categorie, boolean empty) {
                            super.updateItem(categorie, empty);

                            if (empty || categorie == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                setText(categorie.getNom() + " - " + categorie.getDescription());

                                // Charger l'image du logo si disponible
                                if (categorie.getLogo() != null && !categorie.getLogo().isEmpty()) {
                                    String imagePath = categorie.getLogo(); // Get the stored path
                                    imagePath = imagePath.replace("file:/", ""); // Remove "file:/" to get the correct path
                                    imagePath = imagePath.replace("%20", " "); // Replace encoded spaces

                                    Image image = new Image(new File(imagePath).toURI().toString(), 50, 50, true, true);
                                    imageView.setImage(image);
                                    setGraphic(imageView);
                                } else {
                                    setGraphic(null);
                                }
                            }
                        }
                    };
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
