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
import javafx.stage.Modality;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;


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
        this.reponseService = new ReponseService();
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

            setupCellFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupCellFactory() {
        listRec.setCellFactory(lv -> new ListCell<Reclamation>() {
            @Override
            protected void updateItem(Reclamation rec, boolean empty) {
                super.updateItem(rec, empty);
                if (empty || rec == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox container = createReclamationCell(rec);
                    setGraphic(container);
                }
            }
        });
    }

    private HBox createReclamationCell(Reclamation rec) {
        HBox container = new HBox(10);
        container.setPadding(new Insets(5));
        container.getStyleClass().add("list-cell-container");

        // Informations de la réclamation
        VBox infoContainer = new VBox(5);
        Label typeLabel = new Label("Type: " + rec.getType());
        Label descLabel = new Label("Description: " + rec.getDescription());
        Label dateLabel = new Label("Date: " + rec.getDate().toString());
        Label etatLabel = new Label("État: " + rec.getEtatDescription());

        // Stylisation des labels
        typeLabel.setStyle("-fx-font-weight: bold;");
        etatLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: " + getEtatColor(rec.getEtat()));

        infoContainer.getChildren().addAll(typeLabel, descLabel, dateLabel, etatLabel);

        // Conteneur pour les boutons
        VBox buttonContainer = new VBox(5);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);

        // Boutons d'action
        Button repondreBtn = new Button("Répondre");
        Button modifierBtn = new Button("Modifier");
        Button supprimerBtn = new Button("Supprimer");

        // Style des boutons
        repondreBtn.getStyleClass().add("action-button");
        modifierBtn.getStyleClass().add("action-button");
        supprimerBtn.getStyleClass().add("action-button");

        // Actions des boutons
        repondreBtn.setOnAction(e -> showReponsePopup(rec));
        modifierBtn.setOnAction(e -> showModifyPopup(rec));
        supprimerBtn.setOnAction(e -> deleteReclamation(rec));

        buttonContainer.getChildren().addAll(repondreBtn, modifierBtn, supprimerBtn);

        // Ajout des conteneurs à la cellule
        container.getChildren().addAll(infoContainer, buttonContainer);
        HBox.setHgrow(infoContainer, Priority.ALWAYS);

        return container;
    }

    private String getEtatColor(String etat) {
        switch (etat) {
            case "0": return "red"; // Non traité
            case "1": return "orange"; // En cours
            case "2": return "green"; // Traité
            default: return "black";
        }
    }

    private void showReponsePopup(Reclamation rec) {
        try {
            Stage stage = new Stage();
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(10));
            
            // Afficher les informations de la réclamation
            Label recInfoLabel = new Label(
                "Détails de la réclamation:\n" +
                "Type: " + rec.getType() + "\n" +
                "Description: " + rec.getDescription()
            );
            recInfoLabel.setStyle("-fx-font-weight: bold;");
            
            // Champs pour la réponse
            Label contentLabel = new Label("Votre réponse :");
            TextArea contentArea = new TextArea();
            contentArea.setPromptText("Entrez votre réponse ici...");
            contentArea.setPrefRowCount(4);
            
            // Date actuelle en Timestamp
            Timestamp now = new Timestamp(System.currentTimeMillis());
            
            Button submitButton = new Button("Envoyer la réponse");
            submitButton.setOnAction(e -> {
                String contenu = contentArea.getText().trim();
                
                if (contenu.isEmpty()) {
                    showAlert("Erreur", "Le contenu de la réponse ne peut pas être vide.", Alert.AlertType.ERROR);
                    return;
                }
                
                try {
                    // Créer la réponse
                    Reponse reponse = new Reponse();
                    reponse.setId_rec(rec.getIdReclamation());
                    reponse.setContenu_rep(contenu);
                    reponse.setDate(now);
                    
                    // Sauvegarder la réponse
                    if (reponseService.create(reponse)) {
                        // Mettre à jour l'état de la réclamation
                        rec.setEtat("1"); // En cours de traitement
                        reclamationService.update(rec);
                        
                        showAlert("Succès", "Réponse envoyée avec succès", Alert.AlertType.INFORMATION);
                        stage.close();
                        loadReclamations(); // Rafraîchir la liste
                    }
                } catch (Exception ex) {
                    showAlert("Erreur", "Erreur lors de l'envoi de la réponse: " + ex.getMessage(), Alert.AlertType.ERROR);
                }
            });
            
            // Style du bouton
            submitButton.getStyleClass().add("action-button");
            
            layout.getChildren().addAll(
                recInfoLabel,
                new Separator(),
                contentLabel,
                contentArea,
                submitButton
            );
            
            // Style de la fenêtre
            layout.setStyle("-fx-background-color: white;");
            Scene scene = new Scene(layout, 400, 300);
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            
            stage.setScene(scene);
            stage.setTitle("Répondre à la réclamation");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Erreur lors de l'ouverture de la fenêtre de réponse", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
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
