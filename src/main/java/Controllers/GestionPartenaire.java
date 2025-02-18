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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            // Vérification du nom
            if (this.nompartenaire.getText() == null || this.nompartenaire.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Le nom du partenaire est obligatoire.");
            }

            // Vérification de l'email
            String email = this.emailpartenaire.getText();
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("L'email du partenaire est obligatoire.");
            }

            // Validation de l'email (format correct et domaine valide)
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("L'email du partenaire est invalide. Veuillez entrer un email au format valide (par exemple : user@example.com).");
            }

            // Vérification de l'adresse
            if (this.adresse.getText() == null || this.adresse.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("L'adresse du partenaire est obligatoire.");
            }

            // Vérification de la description
            if (this.description.getText() == null || this.description.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("La description du partenaire est obligatoire.");
            }

            // Vérification de la catégorie
            String nomCategorie = this.idcategorie.getValue();
            if (nomCategorie == null || nomCategorie.trim().isEmpty()) {
                throw new IllegalArgumentException("La catégorie est obligatoire.");
            }

            // Vérification de la date
            if (this.date.getValue() == null) {
                throw new IllegalArgumentException("La date d'ajout est obligatoire.");
            }

// Vérification que la date sélectionnée n'est pas dans le passé
            LocalDate selectedDate = this.date.getValue();
            LocalDate currentDate = LocalDate.now();  // Date actuelle

            if (selectedDate.isBefore(currentDate)) {
                throw new IllegalArgumentException("La date d'ajout ne peut pas être dans le passé. Veuillez sélectionner une date actuelle ou future.");
            }



            // Conversion correcte de LocalDate en java.sql.Date
            Date sqlDate = Date.valueOf(this.date.getValue());

            // Conversion du nom de la catégorie en ID avec cs.getIdByNom
            int idCategorie = cs.getIdByNom(nomCategorie);

            // Création de l'objet Partenaire
            Partenaire p = new Partenaire(this.nompartenaire.getText(), this.emailpartenaire.getText(), this.adresse.getText(), this.description.getText(), sqlDate, idCategorie);

            // Sauvegarde du partenaire
            this.ps.create(p);

            // Réinitialisation des champs après l'enregistrement
            reset();

            // Affichage de l'alerte de succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setContentText("Partenaire créé avec succès");
            alert.showAndWait();
        } catch (IllegalArgumentException e) {
            // Affichage de l'alerte d'erreur pour les erreurs de saisie
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Erreur : " + e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            // Affichage de l'alerte d'erreur en cas de problème général
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
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
