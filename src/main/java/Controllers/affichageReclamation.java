package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import models.Reclamation;
import services.ReclamationService;

import java.sql.Timestamp;
import java.util.List;

public class affichageReclamation {
    @FXML
    private TableView<Reclamation> tableView;
    @FXML
    private TableColumn<Reclamation, Integer> idColumn;
    @FXML
    private TableColumn<Reclamation, String> typeColumn;
    @FXML
    private TableColumn<Reclamation, String> descriptionColumn;
    @FXML
    private TableColumn<Reclamation, Timestamp> dateColumn;
    @FXML
    private TableColumn<Reclamation, String> etatColumn;
    @FXML
    private TableColumn<Reclamation, Void> actionsColumn;
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
        // Initialiser les colonnes
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        etatColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));

        // Configuration de la colonne d'actions
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button modifierBtn = new Button("Modifier");
            private final Button supprimerBtn = new Button("Supprimer");
            private final HBox buttons = new HBox(5, modifierBtn, supprimerBtn);

            {
                modifierBtn.setOnAction(event -> {
                    Reclamation reclamation = getTableView().getItems().get(getIndex());
                    modifierReclamation(reclamation);
                });

                supprimerBtn.setOnAction(event -> {
                    Reclamation reclamation = getTableView().getItems().get(getIndex());
                    supprimerReclamation(reclamation);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttons);
                }
            }
        });

        // Initialiser le ComboBox de tri
        triComboBox.getItems().addAll(
            "Date (Plus récent)",
            "Date (Plus ancien)",
            "Type (A-Z)",
            "Type (Z-A)",
            "État"
        );
        triComboBox.setOnAction(e -> trierReclamations());

        // Charger les données
        loadReclamations();

        // Configurer la recherche
        setupSearch();
    }

    private void loadReclamations() {
        try {
            List<Reclamation> reclamations = reclamationService.getAll();
            reclamationList = FXCollections.observableArrayList(reclamations);
            tableView.setItems(reclamationList);
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
        });

        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    private void trierReclamations() {
        String tri = triComboBox.getValue();
        if (tri != null) {
            switch (tri) {
                case "Date (Plus récent)" -> reclamationList.sort((r1, r2) -> r2.getDate().compareTo(r1.getDate()));
                case "Date (Plus ancien)" -> reclamationList.sort((r1, r2) -> r1.getDate().compareTo(r2.getDate()));
                case "Type (A-Z)" -> reclamationList.sort((r1, r2) -> r1.getType().compareTo(r2.getType()));
                case "Type (Z-A)" -> reclamationList.sort((r1, r2) -> r2.getType().compareTo(r1.getType()));
                case "État" -> reclamationList.sort((r1, r2) -> r1.getEtat().compareTo(r2.getEtat()));
            }
        }
    }

    private void modifierReclamation(Reclamation reclamation) {
        // Implémenter la logique de modification
        // Vous pouvez ouvrir une nouvelle fenêtre ou un dialogue
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
