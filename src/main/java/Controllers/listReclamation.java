package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Reclamation;
import models.Reponse;
import services.ReclamationService;
import services.ReponseService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class listReclamation {
    private ReclamationService service;
    private ReponseService reponseService;

    @FXML
    private ListView<Reclamation> listRec;

    public listReclamation() {
        this.service = new ReclamationService();
        this.reponseService = new ReponseService();
    }

    @FXML
    public void initialize() {
        loadReclamations();
    }

    private void loadReclamations() {
        try {
            List<Reclamation> reclamations = service.getAll();
            ObservableList<Reclamation> observableList = FXCollections.observableArrayList(reclamations);
            listRec.setItems(observableList);

            // Custom cell factory with a "Répondre" button
            listRec.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Reclamation rec, boolean empty) {
                    super.updateItem(rec, empty);
                    if (empty || rec == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Label label = new Label("Type: " + rec.getType() + " | Desc: " + rec.getDescription() + " | Date: " + rec.getDate());

                        Button btnRepondre = new Button("Répondre");
                        btnRepondre.setOnAction(event -> showResponsePopup(rec, btnRepondre));

                        setGraphic(new VBox(5, label, btnRepondre));
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Show notification-like popup for response
    private void showResponsePopup(Reclamation rec, Button sourceButton) {
        Popup popup = new Popup();

        // Form elements
        TextField contenuField = new TextField();
        contenuField.setPromptText("Contenu de la réponse");

        DatePicker datePicker = new DatePicker();
        Button saveButton = new Button("Enregistrer");

        // Save response when clicking "Enregistrer"
        saveButton.setOnAction(event -> {
            String contenu = contenuField.getText();
            LocalDate date = datePicker.getValue();

            if (contenu.isEmpty() || date == null) {
                System.out.println("Veuillez remplir tous les champs.");
                return;
            }

            Reponse newReponse = new Reponse(rec.getIdReclamation(), Date.valueOf(date), contenu);
            try {
                if (reponseService.create(newReponse)) {
                    System.out.println("Réponse ajoutée avec succès !");
                    popup.hide(); // Hide popup after saving
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Popup layout
        VBox popupLayout = new VBox(10, new Label("Répondre à la réclamation"), contenuField, datePicker, saveButton);
        popupLayout.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: black;");

        popup.getContent().add(popupLayout);

        // Show popup near the "Répondre" button
        popup.show(sourceButton.getScene().getWindow(), sourceButton.localToScreen(0, 0).getX() + 50, sourceButton.localToScreen(0, 0).getY() + 30);
    }
}
