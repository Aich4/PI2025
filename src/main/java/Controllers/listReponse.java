package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Reclamation;
import models.Reponse;
import services.ReponseService;
import javafx.scene.control.DatePicker;
import services.ReclamationService;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.Timestamp;
import java.util.List;

public class listReponse {
    ReponseService reponseService ;
    ReclamationService reclamationService;

    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> triComboBox;
    @FXML
    private ListView<Reponse> listRep;

    private ObservableList<Reponse> allReponses;

    public listReponse() {
        reponseService = new ReponseService();
        reclamationService = new ReclamationService();
    }

    @FXML
    public void initialize() {
        // Initialiser le ComboBox de tri
        triComboBox.getItems().addAll("Date (Plus récent)", "Date (Plus ancien)", "Contenu (A-Z)", "Contenu (Z-A)");
        triComboBox.setOnAction(e -> trierReponses());

        // Configurer la recherche en temps réel
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrerReponses(newValue);
        });

        loadReponse();
    }

    private void loadReponse() {
        try {
            List<Reponse> reponses = reponseService.getAll();
            allReponses = FXCollections.observableArrayList(reponses);
            listRep.setItems(allReponses);

            listRep.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Reponse rep, boolean empty) {
                    super.updateItem(rep, empty);
                    if (empty || rep == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        VBox container = new VBox(5);

                        // Afficher les détails de la réponse
                        Label reponseLabel = new Label("Réponse: " + rep.getContenu_rep() +
                                "\nDate: " + rep.getDate_rep());

                        // Récupérer et afficher les détails de la réclamation associée
                        try {
                            Reclamation reclamation = reclamationService.getById(rep.getId_rec());
                            Label reclamationLabel = new Label("Réclamation associée:\n" +
                                    "Type: " + reclamation.getType() +
                                    "\nDescription: " + reclamation.getDescription() +
                                    "\nÉtat: " + reclamation.getEtatDescription());
                            reclamationLabel.setStyle("-fx-text-fill: #666666;");

                            container.getChildren().addAll(reponseLabel, reclamationLabel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        HBox buttonBox = new HBox(5);
                        Button btnModifier = new Button("Modifier");
                        Button btnSupprimer = new Button("Supprimer");
                        buttonBox.getChildren().addAll(btnModifier, btnSupprimer);

                        btnModifier.setOnAction(event -> showModifyPopup(rep));
                        btnSupprimer.setOnAction(event -> deleteReclamation(rep));

                        container.getChildren().add(buttonBox);
                        setGraphic(container);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filtrerReponses(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            listRep.setItems(allReponses);
        } else {
            ObservableList<Reponse> filteredList = allReponses.filtered(reponse ->
                    reponse.getContenu_rep().toLowerCase().contains(searchText.toLowerCase())
            );
            listRep.setItems(filteredList);
        }
    }

    private void trierReponses() {
        String triSelection = triComboBox.getValue();
        if (triSelection == null) return;

        switch (triSelection) {
            case "Date (Plus récent)":
                listRep.getItems().sort((r1, r2) -> r2.getDate_rep().compareTo(r1.getDate_rep()));
                break;
            case "Date (Plus ancien)":
                listRep.getItems().sort((r1, r2) -> r1.getDate_rep().compareTo(r2.getDate_rep()));
                break;
            case "Contenu (A-Z)":
                listRep.getItems().sort((r1, r2) -> r1.getContenu_rep().compareTo(r2.getContenu_rep()));
                break;
            case "Contenu (Z-A)":
                listRep.getItems().sort((r1, r2) -> r2.getContenu_rep().compareTo(r1.getContenu_rep()));
                break;
        }
    }

    private void showModifyPopup(Reponse rep) {
        Stage stage = new Stage();
        VBox layout = new VBox(10);

        // Afficher les informations non modifiables
        Label dateLabel = new Label("Date de réponse : " + rep.getDate_rep());
        dateLabel.setStyle("-fx-text-fill: #666666;");

        // Champ de texte pour le contenu
        TextField descField = new TextField(rep.getContenu_rep());
        descField.setPromptText("Contenu de la réponse");

        Button saveButton = new Button("Modifier");

        saveButton.setOnAction(event -> {
            try {
                if (descField.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de validation");
                    alert.setContentText("Le contenu de la réponse ne peut pas être vide");
                    alert.showAndWait();
                    return;
                }

                rep.setContenu_rep(descField.getText());
                reponseService.update(rep);
                System.out.println("Réponse modifiée avec succès !");
                loadReponse();
                stage.close();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Erreur lors de la modification: " + e.getMessage());
                alert.showAndWait();
            }
        });

        layout.getChildren().addAll(
            new Label("Modifier réponse"),
            dateLabel,
            new Label("Contenu :"),
            descField,
            saveButton
        );
        
        layout.setStyle("-fx-spacing: 10; -fx-padding: 15;");
        stage.setScene(new Scene(layout, 350, 200));
        stage.setTitle("Modifier la réponse");
        stage.show();
    }

    private void deleteReclamation(Reponse rep) {
        try {
            reponseService.delete(rep.getId_rep());
            System.out.println("reponse supprimée avec succès !");
            loadReponse();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
