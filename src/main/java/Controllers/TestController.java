package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Reclamation;
import services.ReclamationService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class TestController {
    ReclamationService reclamationService ;
    public TestController() {
        this.reclamationService = new ReclamationService();
    }

    @FXML
    private ComboBox<String> combo_rec;

    @FXML
    private TextField desc_rec;

    @FXML
    void initialize() {
        ObservableList<String> tList = FXCollections.observableArrayList("site", "bug", "pack", "commercant", "guide", "autre");
        combo_rec.setItems(tList);

    }

    @FXML
    void ajouterReclamation(ActionEvent event) {
        String type = combo_rec.getValue();
        String description = desc_rec.getText();
        LocalDate localDate = LocalDate.now(); // Example LocalDate
        Date sqlDate = Date.valueOf(localDate); // Convert to java.sql.Date

        Reclamation reclamation = new Reclamation(description,type, sqlDate);

        try {
            if(reclamationService.create(reclamation))
            {
                System.out.println("afafafa");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
