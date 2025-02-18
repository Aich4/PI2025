package Controllers;

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
import services.PartenaireService;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class ListPartenaire {

    PartenaireService ss;

    public ListPartenaire() {
        this.ss = new PartenaireService();
    }

    @FXML
    private ListView<Partenaire> listepartenaire;

    @FXML
    void initialize() {
        afficherPartenaire();
    }

    private void afficherDialogueModification(Partenaire partenaire) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Modifier Partenaire");
        dialog.setHeaderText("Modifiez les informations du partenaire");

        // Création des champs pré-remplis
        TextField nomField = new TextField(partenaire.getNom());
        TextField emailField = new TextField(partenaire.getEmail());
        TextField adresseField = new TextField(partenaire.getAdresse());
        TextArea descriptionField = new TextArea(partenaire.getDescription());

        DatePicker dateAjoutPicker = new DatePicker(partenaire.getDate_ajout().toLocalDate());

        // Ajout des champs dans un GridPane
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

        dialog.getDialogPane().setContent(grid);

        // Ajout des boutons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Gestion du clic sur "OK"
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                partenaire.setNom(nomField.getText());
                partenaire.setEmail(emailField.getText());
                partenaire.setAdresse(adresseField.getText());
                partenaire.setDescription(descriptionField.getText());
                partenaire.setDate_ajout(Date.valueOf(dateAjoutPicker.getValue()));

                try {
                    ss.update(partenaire);  // Mettre à jour la base de données
                    afficherPartenaire();   // Rafraîchir la liste après modification
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        });

        dialog.showAndWait();
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
                                    try {
                                        ss.delete(partenaire.getId());  // Supprimer le partenaire de la base de données
                                        afficherPartenaire();  // Rafraîchir la liste après suppression

                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Succès");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Le partenaire a été supprimé avec succès !");
                                        alert.showAndWait();  // Afficher la notification



                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
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
