package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Pack;
import services.ServicePack;

import java.sql.SQLException;

public class UpdatePack {

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

    private Pack selectedPack;
    private final ServicePack servicePack = new ServicePack();

    public void setPackDetails(Pack pack) {
        this.selectedPack = pack;

        // Set the text fields with pack details, including ID if needed
        nom_Pack.setText(pack.getNom_Pack());
        description.setText(pack.getDescription());
        prix.setText(String.valueOf(pack.getPrix()));
        duree.setText(String.valueOf(pack.getDuree()));
        avantages.setText(pack.getAvantages());
        statut1.setText(pack.getStatut());
    }

    @FXML
    void saveChanges(ActionEvent event) throws SQLException {

        // Update the selected pack with new data from the text fields
        selectedPack.setNom_Pack(nom_Pack.getText());
        selectedPack.setDescription(description.getText());
        selectedPack.setPrix(Float.parseFloat(prix.getText()));
        selectedPack.setDuree(Integer.parseInt(duree.getText()));
        selectedPack.setAvantages(avantages.getText());
        selectedPack.setStatut(statut1.getText());
        servicePack.update(selectedPack);

        // Print updated pack details for debugging
        System.out.println(" Data after update:");
        System.out.println("Nom: " + selectedPack.getNom_Pack());
        System.out.println("Description: " + selectedPack.getDescription());
        System.out.println("Prix: " + selectedPack.getPrix());
        System.out.println("Durée: " + selectedPack.getDuree());
        System.out.println("Avantages: " + selectedPack.getAvantages());
        System.out.println("Statut: " + selectedPack.getStatut());

        // Call the service to update the pack in the database, including its ID

        System.out.println(" Pack updated successfully!");

        // Close the window after saving the changes
        Stage stage = (Stage) nom_Pack.getScene().getWindow();
        stage.close();
    }
}























