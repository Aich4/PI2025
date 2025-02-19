package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Mission;
import services.MissionService;
import services.RecompenseService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class showmission{

    MissionService ms;
    RecompenseService rs;

    public showmission() {
        this.ms = new MissionService();
        this.rs = new RecompenseService();
    }

    @FXML
    private ListView<Mission> listMission;

    @FXML
    private void afficherMissions() {
        try {
            // Récupérer toutes les missions depuis la base de données
            List<Mission> missions = ms.getAll();
            ObservableList<Mission> observableMissions = FXCollections.observableArrayList(missions);

            // Associer les missions à la ListView
            listMission.setItems(observableMissions);

            // Personnalisation de l'affichage avec une CellFactory
            listMission.setCellFactory(param -> new ListCell<Mission>() {
                @Override
                protected void updateItem(Mission mission, boolean empty) {
                    super.updateItem(mission, empty);

                    if (empty || mission == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // Création des éléments graphiques
                        Label label = new Label("Description: " + mission.getDescription() + "\n" +
                                "Points de récompense: " + mission.getPoints_recompense() + "\n" +
                                "Statut: " + mission.getStatut() + "\n" +
                                "ID Récompense: " + mission.getIdRec());

                        Button btnModifier = new Button("Modifier");
                        Button btnSupprimer = new Button("Supprimer");

                        // Actions des boutons
                        btnModifier.setOnAction(event -> modifierMission(mission));
                        btnSupprimer.setOnAction(event -> supprimerMission(mission));

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
        afficherMissions();
    }

    // Méthode pour modifier une mission
    private void modifierMission(Mission mission) {
        // Création de la boîte de dialogue
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Modifier la mission");
        dialog.setHeaderText("Modifier les informations de la mission");

        // Champs de saisie
        TextField descField = new TextField(mission.getDescription());
        descField.setPromptText("Description");

        TextField pointsField = new TextField(String.valueOf(mission.getPoints_recompense()));
        pointsField.setPromptText("Points de récompense");

        TextField statutField = new TextField(mission.getStatut());
        statutField.setPromptText("Statut");

        // ComboBox pour la récompense (idRec)
        ComboBox<String> idRecComboBox = new ComboBox<>();
        try {
            idRecComboBox.getItems().addAll(rs.getAllDescription()); // Ajouter les descriptions des récompenses
            // Sélectionner la description actuelle dans la ComboBox
            String currentDescription = rs.getDescriptionById(mission.getIdRec());
            idRecComboBox.setValue(currentDescription); // Prendre la description associée à la mission
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Mise en page
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("Description:"), 0, 0);
        grid.add(descField, 1, 0);
        grid.add(new Label("Points de récompense:"), 0, 1);
        grid.add(pointsField, 1, 1);
        grid.add(new Label("Statut:"), 0, 2);
        grid.add(statutField, 1, 2);
        grid.add(new Label("Récompense:"), 0, 3);
        grid.add(idRecComboBox, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Ajout des boutons
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Affichage de la boîte de dialogue
        Optional<ButtonType> result = dialog.showAndWait();

        // Si l'utilisateur valide, mettre à jour la mission
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                mission.setDescription(descField.getText());
                mission.setPoints_recompense(Integer.parseInt(pointsField.getText()));
                mission.setStatut(statutField.getText());

                // Récupérer l'ID de la récompense sélectionnée
                String selectedDescription = idRecComboBox.getValue();
                int idRec = rs.getIdByDescrption(selectedDescription);
                mission.setIdRec(idRec);

                ms.update(mission); // Mettre à jour la mission dans la base de données
                afficherMissions(); // Rafraîchir la liste
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Format incorrect");
                alert.setContentText("Veuillez entrer un nombre valide pour les points de récompense.");
                alert.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Méthode pour supprimer une mission
    private void supprimerMission(Mission mission) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer la mission");
        alert.setContentText("Es-tu sûr de vouloir supprimer cette mission ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                ms.delete(mission.getId()); // Suppression dans la base de données
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            afficherMissions(); // Rafraîchir la liste
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
