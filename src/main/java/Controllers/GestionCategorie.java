package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import models.Categorie;
import services.CategorieService;

import java.io.File;
import java.io.IOException;

public class GestionCategorie {

        CategorieService ss;

        public GestionCategorie() {
            this.ss = new CategorieService();
        }

    @FXML
    private TextField descriptioncategorie;


    @FXML
    private TextField nomcategorie;

        @FXML
        void save(ActionEvent event) {

            Categorie s = new Categorie(this.nomcategorie.getText(), this.descriptioncategorie.getText(),this.imagePath);
            try {
                this.ss.create(s);
                reset();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Categorie created");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }



        }
    @FXML
    void show(ActionEvent event) {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichageCategorie.fxml"));
        try {
            Parent root = loader.load();
            nomcategorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String imagePath;
        void reset (){
            this.nomcategorie.clear();
            this.descriptioncategorie.clear();
            this.imagePath = "";

        }
    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            this.imagePath = selectedFile.toURI().toString();
        }
    }



}




