package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Reclamation;
import models.Reponse;
import services.ReclamationService;
import services.ReponseService;
import javafx.event.ActionEvent;

import java.io.IOException;
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

            // Custom cell factory with buttons
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
                        Button btnModifier = new Button("Modifier");
                        Button btnSupprimer = new Button("Supprimer");

                        btnRepondre.setOnAction(event -> showResponsePopup(rec, btnRepondre));
                        btnModifier.setOnAction(event -> showModifyPopup(rec));
                        btnSupprimer.setOnAction(event -> deleteReclamation(rec));

                        setGraphic(new VBox(5, label, btnRepondre, btnModifier, btnSupprimer));
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            try {
                if (reponseService.create(newReponse)) {
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
        TextField typeField = new TextField(rec.getType());
        TextField descField = new TextField(rec.getDescription());
        DatePicker datePicker = new DatePicker(rec.getDate().toLocalDate());
        Button saveButton = new Button("Modifier");

        saveButton.setOnAction(event -> {
            rec.setType(typeField.getText());
            rec.setDescription(descField.getText());
            rec.setDate(Date.valueOf(datePicker.getValue()));

            try {
                service.update(rec);
                    System.out.println("Réclamation modifiée avec succès !");
                    loadReclamations();
                    stage.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        layout.getChildren().addAll(new Label("Modifier Réclamation"), typeField, descField, datePicker, saveButton);
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    private void deleteReclamation(Reclamation rec) {
        try {
            service.delete(rec.getIdReclamation());
                System.out.println("Réclamation supprimée avec succès !");
                loadReclamations();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showDash(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
        try {
            Parent root = loader.load();
            listRec.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showRec(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listReclamation.fxml"));
        try {
            Parent root = loader.load();
            listRec.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showRep(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listReponse.fxml"));
        try {
            Parent root = loader.load();
            listRec.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void logOut(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        try {
            Parent root = loader.load();
            listRec.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
