package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.CategorieService;
import services.PartenaireService;

import java.util.List;

public class GestionPartenaire {
    PartenaireService ps = new PartenaireService();
    CategorieService cs = new CategorieService();

    public GestionPartenaire() {
        this.ps = new PartenaireService();
    }
    @FXML
    private TextField adresse;

    @FXML
    private DatePicker date;

    @FXML
    private TextField description;

    @FXML
    private TextField emailpartenaire;

    @FXML
    private ComboBox<String> idcategorie;

    @FXML
    private TextField nompartenaire;

    @FXML
    void save(ActionEvent event) {
        int id;
        String nomCategorie = idcategorie.getValue();
        try {
            id=cs.getIdByNom(nomCategorie);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        


    }

    @FXML
    void show(ActionEvent event) {

    }



    @FXML
    public void initialize() {
        List<String> categories = null; // Call your function to get the list
        try {
            categories = cs.getAllNames();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        idcategorie.getItems().addAll(categories); // Populate the ComboBox
    }

}
