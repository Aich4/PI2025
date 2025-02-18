package Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Pack;
import services.ServicePack;

import java.io.IOException;





public class AddPack {
    ServicePack servicePack;
    public AddPack() {
        this.servicePack = new ServicePack();
    }

    @FXML
    private TextField avantages;

    @FXML
    private TextField description;

    @FXML
    private TextField duree;

    @FXML
    private TextField nom_Pack;

    @FXML
    private TextField prix;

    @FXML
    private TextField statut1;


    @FXML
    void save(ActionEvent event) {
        Pack pack = new Pack(
                nom_Pack.getText(),
                description.getText(),
                Integer.parseInt(prix.getText()),
                Integer.parseInt(duree.getText()),
                avantages.getText(),
                statut1.getText()

        );
        try {


            servicePack.create(pack);
            reset();

            // Success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Pack created successfully!");
            alert.showAndWait();
        } catch (Exception e) {
            // Error alert
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error: " + e.getMessage());
            System.out.println(e.getMessage());
            alert.showAndWait();
        }}

    @FXML
    void showpack(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowPack.fxml"));
            Parent root = loader.load();

            // Afficher la nouvelle fenêtre
            Scene currentScene = ((Node) event.getSource()).getScene();
            currentScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void reset() {
        this.nom_Pack.clear();
        this.description.clear();
        this.avantages.clear();
        this.prix.clear();
        this.duree.clear();
        this.statut1.clear();
    }


}




























