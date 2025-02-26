package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Reclamation;
import models.Reponse;
import services.ReclamationService;
import services.ReponseService;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;


import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;


public class listReclamation {
    private ReclamationService service;
    private ReponseService reponseService;

    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> triComboBox;
    @FXML
    private ListView<Reclamation> listRec;

    private ObservableList<Reclamation> allReclamations;
    private ReclamationService reclamationService;

    public listReclamation() {
        this.reclamationService = new ReclamationService();
    }

    @FXML
    public void initialize() {
        // Initialiser le ComboBox de tri
        ObservableList<String> triOptions = FXCollections.observableArrayList(
            "Date (Plus récent)",
            "Date (Plus ancien)",
            "Type (A-Z)",
            "Type (Z-A)",
            "État (Non traité)",
            "État (En attente)",
            "État (Traité)"
        );
        triComboBox.setItems(triOptions);
        triComboBox.setOnAction(e -> trierReclamations());

        // Configurer la recherche en temps réel
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrerReclamations(newValue);
        });

        loadReclamations();
    }

    private void loadReclamations() {
        try {
            List<Reclamation> reclamations = reclamationService.getAll();
            allReclamations = FXCollections.observableArrayList(reclamations);
            listRec.setItems(allReclamations);

            listRec.setCellFactory(lv -> new ListCell<Reclamation>() {
                @Override
                protected void updateItem(Reclamation rec, boolean empty) {
                    super.updateItem(rec, empty);
                    if (empty || rec == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        VBox container = new VBox(5);

                        Label infoLabel = new Label(
                            "Type: " + rec.getType() +
                            "\nDescription: " + rec.getDescription() +
                            "\nDate: " + rec.getDate() +
                            "\nÉtat: " + rec.getEtatDescription()
                        );

                        HBox buttonBox = new HBox(5);
                        Button btnModifier = new Button("Modifier");
                        Button btnSupprimer = new Button("Supprimer");

                        buttonBox.getChildren().addAll(btnModifier, btnSupprimer);

                        btnModifier.setOnAction(event -> showModifyPopup(rec));
                        btnSupprimer.setOnAction(event -> deleteReclamation(rec));

                        container.getChildren().addAll(infoLabel, buttonBox);
                        setGraphic(container);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filtrerReclamations(String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            listRec.setItems(allReclamations);
        } else {
            ObservableList<Reclamation> filteredList = FXCollections.observableArrayList();
            for (Reclamation rec : allReclamations) {
                if (rec.getType().toLowerCase().contains(searchText.toLowerCase()) ||
                    rec.getDescription().toLowerCase().contains(searchText.toLowerCase()) ||
                    rec.getEtatDescription().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(rec);
                }
            }
            listRec.setItems(filteredList);
        }
    }

    private void trierReclamations() {
        String triSelection = triComboBox.getValue();
        if (triSelection == null) return;

        List<Reclamation> sorted = new ArrayList<>(listRec.getItems());

        switch (triSelection) {
            case "Date (Plus récent)":
                sorted.sort((r1, r2) -> r2.getDate().compareTo(r1.getDate()));
                break;
            case "Date (Plus ancien)":
                sorted.sort((r1, r2) -> r1.getDate().compareTo(r2.getDate()));
                break;
            case "Type (A-Z)":
                sorted.sort((r1, r2) -> r1.getType().compareTo(r2.getType()));
                break;
            case "Type (Z-A)":
                sorted.sort((r1, r2) -> r2.getType().compareTo(r1.getType()));
                break;
            case "État (Non traité)":
                sorted.sort((r1, r2) -> r1.getEtat().compareTo(r2.getEtat()));
                break;
            case "État (En attente)":
                sorted.sort((r1, r2) -> r2.getEtat().compareTo(r1.getEtat()));
                break;
            case "État (Traité)":
                sorted.sort((r1, r2) -> r1.getEtat().compareTo(r2.getEtat()));
                break;
        }

        listRec.setItems(FXCollections.observableArrayList(sorted));
    }

    private void showResponsePopup(Reclamation rec, Button sourceButton) {
        Popup popup = new Popup();

        TextField contenuField = new TextField();
        contenuField.setPromptText("Contenu de la réponse");

        DatePicker datePicker = new DatePicker();
        Button saveButton = new Button("Enregistrer");

        saveButton.setOnAction(event -> {
            String contenu = contenuField.getText();
            LocalDate date = datePicker.getValue();

            if (contenu.isEmpty() || date == null) {
                System.out.println("Veuillez remplir tous les champs.");
                return;
            }

            Reponse newReponse = new Reponse(rec.getIdReclamation(), Date.valueOf(date), contenu);
            ReponseService service = new ReponseService();
            try {
                if (service.create(newReponse)) {
                    System.out.println("Réponse ajoutée avec succès !");
                    popup.hide();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        VBox popupLayout = new VBox(10, new Label("Répondre à la réclamation"), contenuField, datePicker, saveButton);
        popupLayout.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: black;");

        popup.getContent().add(popupLayout);
        popup.show(sourceButton.getScene().getWindow(), sourceButton.localToScreen(0, 0).getX() + 50, sourceButton.localToScreen(0, 0).getY() + 30);
    }

    private void showModifyPopup(Reclamation rec) {
        Stage stage = new Stage();
        VBox layout = new VBox(10);

        // Afficher les informations non modifiables
        Label infoLabel = new Label(
            "Type: " + rec.getType() +
            "\nDescription: " + rec.getDescription() +
            "\nDate: " + rec.getDate()
        );
        infoLabel.setStyle("-fx-text-fill: #666666;");

        // ComboBox pour l'état uniquement
        ComboBox<String> etatCombo = new ComboBox<>();
        etatCombo.getItems().addAll("0", "1", "2");
        etatCombo.setValue(rec.getEtat());
        Label etatLabel = new Label("État :");
        Label etatDescription = new Label("0: non traité, 1: traité, 2: en attente");
        etatDescription.setStyle("-fx-font-size: 11; -fx-text-fill: #666666;");

        Button saveButton = new Button("Modifier l'état");
        saveButton.setOnAction(event -> {
            try {
                rec.setEtat(etatCombo.getValue());
                reclamationService.update(rec);
                System.out.println("État de la réclamation modifié avec succès !");
                loadReclamations();
                stage.close();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Erreur lors de la modification de l'état: " + e.getMessage());
                alert.showAndWait();
            }
        });

        layout.getChildren().addAll(
            new Label("Détails de la réclamation :"),
            infoLabel,
            new Separator(),
            etatLabel,
            etatCombo,
            etatDescription,
            saveButton
        );

        layout.setStyle("-fx-spacing: 10; -fx-padding: 15;");
        stage.setScene(new Scene(layout, 300, 250));
        stage.setTitle("Modifier l'état de la réclamation");
        stage.show();
    }

    private void deleteReclamation(Reclamation rec) {
        try {
            reclamationService.delete(rec.getIdReclamation());
                System.out.println("Réclamation supprimée avec succès !");
                loadReclamations();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void basculeReponse(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListReponse.fxml"));
        try {
            Parent root = loader.load();
            listRec.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
