package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Partenaire;
import services.CategorieService;
import services.PartenaireService;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ListPartenaire {

    PartenaireService ss;
    CategorieService cs;


    public ListPartenaire() {
        this.ss = new PartenaireService();
        this.cs = new CategorieService();
    }

    @FXML
    private ListView<Partenaire> listepartenaire;

    @FXML
    void initialize() {
        afficherPartenaire();
    }



    @FXML
    void goToAdd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddPartenaire.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer le contenu
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    private void afficherDialogueModification(Partenaire partenaire) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Modifier Partenaire");
        dialog.setHeaderText("Modifiez les informations du partenaire");

        TextField nomField = new TextField(partenaire.getNom());
        TextField emailField = new TextField(partenaire.getEmail());
        TextField adresseField = new TextField(partenaire.getAdresse());
        TextArea descriptionField = new TextArea(partenaire.getDescription());
        DatePicker dateAjoutPicker = new DatePicker(partenaire.getDate_ajout().toLocalDate());

        ComboBox<String> categorieBox = new ComboBox<>();
        try {
            categorieBox.getItems().addAll(cs.getAllNames());
            categorieBox.setValue(cs.getNomById(partenaire.getId_categorie()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Adresse:"), 0, 2);
        grid.add(adresseField, 1, 2);
        grid.add(new Label("Date d'ajout:"), 0, 3);
        grid.add(dateAjoutPicker, 1, 3);
        grid.add(new Label("Description:"), 0, 4);
        grid.add(descriptionField, 1, 4);
        grid.add(new Label("Catégorie:"), 0, 5);
        grid.add(categorieBox, 1, 5);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        while (true) {
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                String nom = nomField.getText().trim();
                String email = emailField.getText().trim();
                String adresse = adresseField.getText().trim();
                String description = descriptionField.getText().trim();
                LocalDate dateAjout = dateAjoutPicker.getValue();
                String categorie = categorieBox.getValue();

                if (nom.isEmpty() || nom.matches("\\d+")) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom ne peut pas être vide ou contenir uniquement des chiffres.");
                    continue;
                }
                if (email.isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "L'email n'est pas valide.");
                    continue;
                }

                if (adresse.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "L'adresse ne peut pas être vide.");
                    continue;
                }
                if (description.isEmpty() || description.matches("\\d+")) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "La description ne peut pas être vide ou contenir uniquement des chiffres.");
                    continue;
                }
                if (dateAjout == null || dateAjout.isBefore(LocalDate.now())) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez sélectionner une date valide (aujourd'hui ou une date future).");
                    continue;
                }

                if (categorie == null || categorie.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez sélectionner une catégorie.");
                    continue;
                }

                partenaire.setNom(nom);
                partenaire.setEmail(email);
                partenaire.setAdresse(adresse);
                partenaire.setDescription(description);
                partenaire.setDate_ajout(Date.valueOf(dateAjout));
                try {
                    partenaire.setId_categorie(cs.getIdByNom(categorie));
                    ss.update(partenaire);
                    afficherPartenaire();
                    showAlert(Alert.AlertType.INFORMATION, "Succès", "Partenaire mis à jour avec succès.");
                    break;
                } catch (Exception e) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la mise à jour : " + e.getMessage());
                }
            } else {
                break;
            }
        }
    }




    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    private void afficherPartenaire() {
        try {
            List<Partenaire> partenaires = ss.getAll();
            listepartenaire.getItems().setAll(partenaires);




            // Personnaliser l'affichage des éléments dans la ListView avec un bouton "Modifier" et "Supprimer"
            listepartenaire.setCellFactory(new Callback<>() {
                @Override
                public ListCell<Partenaire> call(ListView<Partenaire> param) {
                    return new ListCell<>() {
                        @Override
                        protected void updateItem(Partenaire partenaire, boolean empty) {
                            super.updateItem(partenaire, empty);

                            if (empty || partenaire == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                // Récupérer le nom de la catégorie
                                String categorieName = ss.getCategorieNameById(partenaire.getId_categorie());

                                // Création du texte affiché
                                String partenaireInfo = "Nom: " + partenaire.getNom() + "\n"
                                        + "Email: " + partenaire.getEmail() + "\n"
                                        + "Adresse: " + partenaire.getAdresse() + "\n"
                                        + "Date: " + partenaire.getDate_ajout() + "\n"
                                        + "Description: " + partenaire.getDescription() + "\n"
                                        + "Catégorie: " + (categorieName != null ? categorieName : "Inconnue");

                                Label infoLabel = new Label(partenaireInfo);
                                Button btnModifier = new Button("Modifier");
                                Button btnSupprimer = new Button("Supprimer");

                                // Événement du bouton "Modifier"
                                btnModifier.setOnAction(event -> {
                                    afficherDialogueModification(partenaire);
                                });

                                // Événement du bouton "Supprimer"
                                btnSupprimer.setOnAction(event -> {
                                    // Créer une alerte de confirmation
                                    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                                    confirmationAlert.setTitle("Confirmer la suppression");
                                    confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce partenaire ?");
                                    confirmationAlert.setContentText("Cette action est irréversible.");

                                    // Attendre la réponse de l'utilisateur
                                    confirmationAlert.showAndWait().ifPresent(response -> {
                                        if (response == ButtonType.OK) {
                                            try {
                                                ss.delete(partenaire.getId());  // Supprimer le partenaire de la base de données
                                                afficherPartenaire();  // Rafraîchir la liste après suppression

                                                // Afficher une notification de succès
                                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                alert.setTitle("Succès");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Le partenaire a été supprimé avec succès !");
                                                alert.showAndWait();  // Afficher la notification
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                });

                                // Ajouter les boutons dans un HBox (horizontal)
                                HBox hbox = new HBox(10, infoLabel, btnModifier, btnSupprimer);
                                setGraphic(hbox);
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
