package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Reclamation;
import services.ReclamationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class affichageReclamation {
    @FXML
    private ListView<Reclamation> listView;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> triComboBox;

    private ReclamationService reclamationService;
    private ObservableList<Reclamation> reclamationList;

    public affichageReclamation() {
        reclamationService = new ReclamationService();
    }

    @FXML
    public void initialize() {
        // Initialiser le ComboBox de tri
        triComboBox.getItems().addAll(
            "Date (Plus récent)",
            "Date (Plus ancien)",
            "Type (A-Z)",
            "Type (Z-A)",
            "État"
        );
        triComboBox.setOnAction(e -> trierReclamations());

        // Configuration de la ListView
        listView.setCellFactory(lv -> new ListCell<Reclamation>() {
            @Override
            protected void updateItem(Reclamation reclamation, boolean empty) {
                super.updateItem(reclamation, empty);
                if (empty || reclamation == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Création du conteneur principal
                    HBox container = new HBox(15); // Espacement entre les colonnes
                    container.setAlignment(Pos.CENTER_LEFT);
                    container.setPrefHeight(40); // Hauteur fixe pour chaque ligne
                    container.setPadding(new Insets(5, 10, 5, 10));
                    
                    // Style tableau
                    container.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;");
                    
                    // Création des "colonnes" avec largeur fixe
                    Label typeLabel = new Label(reclamation.getType());
                    typeLabel.setPrefWidth(100);
                    
                    Label descLabel = new Label(reclamation.getDescription());
                    descLabel.setPrefWidth(200);
                    
                    Label dateLabel = new Label(reclamation.getDate().toString());
                    dateLabel.setPrefWidth(150);
                    
                    Label etatLabel = new Label(reclamation.getEtat());
                    etatLabel.setPrefWidth(100);
                    
                    // Conteneur pour les boutons
                    HBox buttonsBox = new HBox(5);
                    buttonsBox.setAlignment(Pos.CENTER);
                    Button modifierBtn = new Button("Modifier");
                    Button supprimerBtn = new Button("Supprimer");
                    
                    // Style des boutons
                    modifierBtn.setStyle("-fx-background-color: #808080; -fx-text-fill: white;");
                    supprimerBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
                    
                    buttonsBox.getChildren().addAll(modifierBtn, supprimerBtn);
                    
                    // Ajout des "colonnes" au conteneur
                    container.getChildren().addAll(
                        typeLabel, 
                        descLabel, 
                        dateLabel, 
                        etatLabel, 
                        buttonsBox
                    );
                    
                    // Événements des boutons
                    modifierBtn.setOnAction(event -> modifierReclamation(reclamation));
                    supprimerBtn.setOnAction(event -> supprimerReclamation(reclamation));
                    
                    // Effet hover
                    container.setOnMouseEntered(e -> 
                        container.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;")
                    );
                    container.setOnMouseExited(e -> 
                        container.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-width: 0 0 1 0;")
                    );
                    
                    setGraphic(container);
                }
            }
        });

        // Charger les données
        loadReclamations();

        // Configurer la recherche
        setupSearch();
    }

    private void loadReclamations() {
        try {
            List<Reclamation> reclamations = reclamationService.getAll();
            reclamationList = FXCollections.observableArrayList(reclamations);
            listView.setItems(reclamationList);
        } catch (Exception e) {
            showAlert("Erreur", "Erreur lors du chargement des réclamations: " + e.getMessage());
        }
    }

    private void setupSearch() {
        FilteredList<Reclamation> filteredData = new FilteredList<>(reclamationList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reclamation -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return reclamation.getType().toLowerCase().contains(lowerCaseFilter)
                    || reclamation.getDescription().toLowerCase().contains(lowerCaseFilter)
                    || reclamation.getEtat().toLowerCase().contains(lowerCaseFilter);
            });
            
            listView.setItems(filteredData);
        });
    }

    private void trierReclamations() {
        String tri = triComboBox.getValue();
        if (tri != null) {
            List<Reclamation> sorted = new ArrayList<>(listView.getItems());
            switch (tri) {
                case "Date (Plus récent)" -> sorted.sort((r1, r2) -> r2.getDate().compareTo(r1.getDate()));
                case "Date (Plus ancien)" -> sorted.sort((r1, r2) -> r1.getDate().compareTo(r2.getDate()));
                case "Type (A-Z)" -> sorted.sort((r1, r2) -> r1.getType().compareTo(r2.getType()));
                case "Type (Z-A)" -> sorted.sort((r1, r2) -> r2.getType().compareTo(r1.getType()));
                case "État" -> sorted.sort((r1, r2) -> r1.getEtat().compareTo(r2.getEtat()));
            }
            listView.setItems(FXCollections.observableArrayList(sorted));
        }
    }

    private void modifierReclamation(Reclamation reclamation) {
        // Implémenter la logique de modification
    }

    private void supprimerReclamation(Reclamation reclamation) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer cette réclamation ?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    reclamationService.delete(reclamation.getIdReclamation());
                    loadReclamations();
                } catch (Exception e) {
                    showAlert("Erreur", "Erreur lors de la suppression: " + e.getMessage());
                }
            }
        });
    }

    @FXML
    void test(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/test.fxml"));
            Parent root = loader.load();
            Scene scene = ((Button) event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            showAlert("Erreur", "Erreur lors du chargement de la page: " + e.getMessage());
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


