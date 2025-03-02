package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.ArrayList;
import javafx.geometry.Insets;

public class listReponse {
    @FXML
    private ListView<Reponse> listView;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> triComboBox;

    private ReponseService reponseService;
    private ObservableList<Reponse> reponseList;

    public listReponse() {
        reponseService = new ReponseService();
    }

    @FXML
    public void initialize() {
        // Initialiser le ComboBox de tri
        triComboBox.getItems().addAll(
            "Date (Plus récent)",
            "Date (Plus ancien)",
            "Contenu (A-Z)",
            "Contenu (Z-A)"
        );
        triComboBox.setOnAction(e -> trierReponses());

        // Configuration de la ListView
        listView.setCellFactory(lv -> new ListCell<Reponse>() {
            @Override
            protected void updateItem(Reponse reponse, boolean empty) {
                super.updateItem(reponse, empty);
                if (empty || reponse == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    VBox container = new VBox(8);
                    container.setPadding(new Insets(10));
                    container.setStyle("-fx-background-color: white; -fx-border-color: #E8E8E8; -fx-border-width: 0 0 1 0;");

                    // En-tête avec la date
                    Label dateLabel = new Label(reponse.getDate_rep().toString());
                    dateLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #4682B4; -fx-font-size: 14px;");

                    // Contenu de la réponse
                    Label contenuLabel = new Label(reponse.getContenu_rep());
                    contenuLabel.setWrapText(true);
                    contenuLabel.setStyle("-fx-text-fill: #333333; -fx-font-size: 13px;");

                    // ID Réclamation
                    Label idRecLabel = new Label("Réclamation #" + reponse.getId_rec());
                    idRecLabel.setStyle("-fx-font-style: italic; -fx-text-fill: #666666; -fx-font-size: 12px;");

                    // Boutons d'action
                    HBox buttonBox = new HBox(10);
                    buttonBox.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);

                    Button modifierBtn = new Button("Modifier");
                    modifierBtn.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white;");
                    
                    Button supprimerBtn = new Button("Supprimer");
                    supprimerBtn.setStyle("-fx-background-color: #DC143C; -fx-text-fill: white;");

                    modifierBtn.setOnAction(event -> modifierReponse(reponse));
                    supprimerBtn.setOnAction(event -> supprimerReponse(reponse));

                    buttonBox.getChildren().addAll(modifierBtn, supprimerBtn);

                    container.getChildren().addAll(dateLabel, contenuLabel, idRecLabel, buttonBox);

                    // Effet de survol
                    container.setOnMouseEntered(e -> 
                        container.setStyle("-fx-background-color: #F5F5F5; -fx-border-color: #E8E8E8; -fx-border-width: 0 0 1 0;"));
                    container.setOnMouseExited(e -> 
                        container.setStyle("-fx-background-color: white; -fx-border-color: #E8E8E8; -fx-border-width: 0 0 1 0;"));

                    setGraphic(container);
                }
            }
        });

        // Charger les données
        loadReponses();

        // Configurer la recherche
        setupSearch();
    }

    private void loadReponses() {
        try {
            List<Reponse> reponses = reponseService.getAll();
            reponseList = FXCollections.observableArrayList(reponses);
            listView.setItems(reponseList);
        } catch (Exception e) {
            showAlert("Erreur", "Erreur lors du chargement des réponses: " + e.getMessage());
        }
    }

    private void setupSearch() {
        FilteredList<Reponse> filteredData = new FilteredList<>(reponseList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reponse -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return reponse.getContenu_rep().toLowerCase().contains(lowerCaseFilter);
            });
            
            listView.setItems(filteredData);
        });
    }

    private void trierReponses() {
        String tri = triComboBox.getValue();
        if (tri != null) {
            List<Reponse> sorted = new ArrayList<>(listView.getItems());
            switch (tri) {
                case "Date (Plus récent)" -> sorted.sort((r1, r2) -> r2.getDate_rep().compareTo(r1.getDate_rep()));
                case "Date (Plus ancien)" -> sorted.sort((r1, r2) -> r1.getDate_rep().compareTo(r2.getDate_rep()));
                case "Contenu (A-Z)" -> sorted.sort((r1, r2) -> r1.getContenu_rep().compareTo(r2.getContenu_rep()));
                case "Contenu (Z-A)" -> sorted.sort((r1, r2) -> r2.getContenu_rep().compareTo(r1.getContenu_rep()));
            }
            listView.setItems(FXCollections.observableArrayList(sorted));
        }
    }

    private void modifierReponse(Reponse reponse) {
        // Implémenter la logique de modification
    }

    private void supprimerReponse(Reponse reponse) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer cette réponse ?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    reponseService.delete(reponse.getId_rep());
                    loadReponses();
                } catch (Exception e) {
                    showAlert("Erreur", "Erreur lors de la suppression: " + e.getMessage());
                }
            }
        });
    }

    @FXML
    void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/test.fxml"));
            Parent root = loader.load();
            Scene scene = ((Button) event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            showAlert("Erreur", "Erreur lors du chargement de la page: " + e.getMessage());
        }
    }

    @FXML
    void retourner(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/listReclamation.fxml"));
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
