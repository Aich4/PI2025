package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Recompense;
import services.RecompenseService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import services.RecompenseService;


public class showrec {

    RecompenseService rs;

    public showrec() {
        this.rs = new RecompenseService();
    }

    @FXML
    private ListView<Recompense> listRec;


    @FXML
    private void afficherRecompenses() {
        try {
            // Récupérer toutes les récompenses depuis la base de données
            List<Recompense> recompenses = rs.getAll();
            ObservableList<Recompense> observableRecompenses = FXCollections.observableArrayList(recompenses);

            // Associer les récompenses à la ListView
            listRec.setItems(observableRecompenses);

            // Personnalisation de l'affichage avec une CellFactory
            listRec.setCellFactory(param -> new ListCell<Recompense>() {
                @Override
                protected void updateItem(Recompense recompense, boolean empty) {
                    super.updateItem(recompense, empty);

                    if (empty || recompense == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // Création des éléments graphiques
                        Label label = new Label("Description: " + recompense.getDescription() + "\n" +
                                "Coût en points: " + recompense.getCout_en_points() + "\n" +
                                "Disponibilité: " + recompense.getDisponibilite());

                        Button btnModifier = new Button("Modifier");
                        Button btnSupprimer = new Button("Supprimer");

                        // Actions des boutons
                        btnModifier.setOnAction(event -> modifierRecompense(recompense));
                        btnSupprimer.setOnAction(event -> supprimerRecompense(recompense));

                        // Regrouper les éléments dans un HBox
                        HBox buttonsBox = new HBox(10, btnModifier, btnSupprimer);
                        HBox fullBox = new HBox(10, label, buttonsBox);

                        setGraphic(fullBox);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        afficherRecompenses();
    }
    // Méthode pour modifier une récompense
    private void modifierRecompense(Recompense recompense) {
        // Création de la boîte de dialogue
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Modifier la récompense");
        dialog.setHeaderText("Modifier les informations de la récompense");
        dialog.setResizable(true); // Permet le redimensionnement

        // Champs de saisie
        TextArea descField = new TextArea(recompense.getDescription()); // TextArea au lieu de TextField
        descField.setPromptText("Description");
        descField.setPrefWidth(300); // Largeur ajustée
        descField.setPrefHeight(80); // Augmenter la hauteur pour plus de visibilité

        TextField coutField = new TextField(String.valueOf(recompense.getCout_en_points()));
        coutField.setPromptText("Coût en points");
        coutField.setPrefWidth(150);

        ComboBox<String> dispoBox = new ComboBox<>();
        dispoBox.getItems().addAll("Disponible", "Indisponible");
        dispoBox.setValue(recompense.getDisponibilite());
        dispoBox.setPrefWidth(150);

        // Mise en page avec GridPane
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(20, 20, 20, 20)); // Ajoute du padding pour aérer

        grid.add(new Label("Description:"), 0, 0);
        grid.add(descField, 1, 0);
        grid.add(new Label("Coût en points:"), 0, 1);
        grid.add(coutField, 1, 1);
        grid.add(new Label("Disponibilité:"), 0, 2);
        grid.add(dispoBox, 1, 2);

        // Mise en page globale avec VBox
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(grid);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Affichage de la boîte de dialogue
        Optional<ButtonType> result = dialog.showAndWait();

        // Si l'utilisateur valide, on vérifie les données avant de mettre à jour
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                String description = descField.getText().trim();
                String coutText = coutField.getText().trim();
                String disponibilite = dispoBox.getValue();

                // Vérification des champs
                if (description.isEmpty()) {
                    showAlert("Erreur", "La description ne peut pas être vide.");
                    return;
                }

                int cout;
                try {
                    cout = Integer.parseInt(coutText);
                    if (cout < 0) {
                        showAlert("Erreur", "Le coût en points doit être un nombre positif.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    showAlert("Erreur", "Veuillez entrer un nombre valide pour le coût en points.");
                    return;
                }

                if (disponibilite == null) {
                    showAlert("Erreur", "Veuillez sélectionner une disponibilité.");
                    return;
                }

                // Mise à jour de la récompense
                recompense.setDescription(description);
                recompense.setCout_en_points(cout);
                recompense.setDisponibilite(disponibilite);

                rs.update(recompense); // Mettre à jour la base de données
                afficherRecompenses(); // Rafraîchir la liste

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    // Méthode pour supprimer une récompense
    private void supprimerRecompense(Recompense recompense) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer la récompense");
        alert.setContentText("Es-tu sûr de vouloir supprimer cette récompense ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                rs.delete(recompense.getId()); // Suppression dans la base de données
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            afficherRecompenses(); // Rafraîchir la liste
        }
    }

    @FXML
    void mission(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/mission.fxml"));
            Parent root = loader.load();

            // Obtenir la scène depuis l'événement (plus sûr que d'utiliser descriptionRec)
            ((Button) event.getSource()).getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de ShowRecompense.fxml", e);
        }
    }

    @FXML
    void recompense(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/recompense.fxml"));
            Parent root = loader.load();

            // Obtenir la scène depuis l'événement (plus sûr que d'utiliser descriptionRec)
            ((Button) event.getSource()).getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de ShowRecompense.fxml", e);
        }
    }


}



