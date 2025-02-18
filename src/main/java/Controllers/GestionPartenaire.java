package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.Partenaire;
import services.CategorieService;
import services.PartenaireService;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static com.sun.javafx.scene.input.TouchPointHelper.reset;

public class GestionPartenaire {
    PartenaireService ps = new PartenaireService();
    CategorieService cs = new CategorieService();

    public GestionPartenaire() {
        this.ps = new PartenaireService();
    }


    @FXML
    private TextField nompartenaire;

    @FXML
    private TextField emailpartenaire;

    @FXML
    private TextField adresse;

    @FXML
    private DatePicker date;

    @FXML
    private TextField description;


    @FXML
    private ComboBox<String> idcategorie;



    @FXML

    void save(ActionEvent event) {
        try {
            // Conversion correcte de LocalDate en java.sql.Date
            Date sqlDate = Date.valueOf(this.date.getValue());

            // Récupération du nom de la catégorie sélectionnée
            String nomCategorie = this.idcategorie.getValue();

            // Conversion du nom de la catégorie en ID avec cs.getIdByNom
            int idCategorie = cs.getIdByNom(nomCategorie);

            // Création de l'objet Partenaire avec les bons types de paramètres
            Partenaire p = new Partenaire(this.nompartenaire.getText(), this.emailpartenaire.getText(), this.adresse.getText(), this.description.getText(), sqlDate, idCategorie);

            // Sauvegarde du partenaire
            this.ps.create(p);

            // Réinitialisation des champs après l'enregistrement
            reset();

            // Affichage de l'alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Partenaire créé avec succès");
            alert.showAndWait();
        } catch (Exception e) {
            // Affichage de l'alerte d'erreur en cas de problème
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Erreur lors de la création du Partenaire : " + e.getMessage());
            alert.showAndWait();

            // Affichage de l'erreur pour le débogage
            e.printStackTrace();
        }
    }

    private void reset() {
    }

    @FXML
    void show(ActionEvent event) {


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichagePartenaire.fxml"));
        try {
            Parent root = loader.load();
            nompartenaire.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    public void initialize() {
        try {
            idcategorie.getItems().addAll(cs.getAllNames()); // Ajouter directement les catégories
        } catch (Exception e) {
            e.printStackTrace(); // Afficher l'exception si elle se produit
        }
    }


    private Partenaire partenaireActuel;

    public void setPartenaire(Partenaire partenaire) {
        this.partenaireActuel = partenaire;

        // Pré-remplir les champs avec les données du partenaire sélectionné
        nompartenaire.setText(partenaire.getNom());
        emailpartenaire.setText(partenaire.getEmail());
        adresse.setText(partenaire.getAdresse());
        description.setText(partenaire.getDescription());
        date.setValue(partenaire.getDate_ajout().toLocalDate()); // Conversion Date -> LocalDate

        idcategorie.setValue(cs.getNomById(partenaire.getId_categorie()));
    }


    


}
