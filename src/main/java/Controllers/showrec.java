package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Recompense;
import services.RecompenseService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class showrec {

    RecompenseService rs;

    public showrec() {
        this.rs = new RecompenseService();
    }

    @FXML
    private ListView<Recompense> listRec;

    @FXML
    private ComboBox<String> comboBoxFilter;

    @FXML
    private TextField searchLabel;

    @FXML
    private Button sortButton;

    @FXML
    private void afficherRecompenses() {
        try {
            List<Recompense> recompenses = rs.getAll();
            ObservableList<Recompense> observableRecompenses = FXCollections.observableArrayList(recompenses);
            listRec.setItems(observableRecompenses);

            listRec.setCellFactory(param -> new ListCell<Recompense>() {
                @Override
                protected void updateItem(Recompense recompense, boolean empty) {
                    super.updateItem(recompense, empty);

                    if (empty || recompense == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        Label lblDescription = new Label("📜 Description : " + recompense.getDescription());
                        Label lblCout = new Label("💰 Coût : " + recompense.getCout_en_points() + " points");
                        Label lblDisponibilite = new Label("📅 Disponibilité : " + recompense.getDisponibilite());

                        lblDescription.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
                        lblCout.setStyle("-fx-font-size: 13px;");
                        lblDisponibilite.setStyle("-fx-font-size: 13px;");

                        Button btnModifier = new Button("✏ Modifier");
                        Button btnSupprimer = new Button("🗑 Supprimer");

                        btnModifier.setOnAction(event -> modifierRecompense(recompense, event));
                        btnSupprimer.setOnAction(event -> supprimerRecompense(recompense));

                        VBox detailsBox = new VBox(5, lblDescription, lblCout, lblDisponibilite);
                        HBox buttonsBox = new HBox(10, btnModifier, btnSupprimer);
                        VBox fullBox = new VBox(10, detailsBox, buttonsBox);

                        detailsBox.setPadding(new Insets(5));
                        buttonsBox.setPadding(new Insets(5));
                        fullBox.setPadding(new Insets(10));
                        fullBox.setStyle("-fx-border-color: #ccc; -fx-border-radius: 10; -fx-padding: 10; -fx-background-radius: 10;");

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
        comboBoxFilter.setItems(FXCollections.observableArrayList("Description", "Coût en points", "Disponibilité"));

        sortButton.setOnAction(event -> handleSort());

        searchLabel.textProperty().addListener((observable, oldValue, newValue) -> handleSearch(newValue));
    }

    private void handleSearch(String searchText) {
        String critere = comboBoxFilter.getSelectionModel().getSelectedItem();

        // Vérifier que le critère est bien sélectionné
        if (critere == null) {
            System.out.println("Aucun critère sélectionné !");
            return;
        }

        // Si la recherche est vide, afficher toutes les récompenses
        if (searchText.isEmpty()) {
            try {
                listRec.setItems(FXCollections.observableArrayList(rs.getAll()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return;
        }

        System.out.println("Recherche en cours : " + searchText + " - Critère : " + critere); // DEBUG

        List<Recompense> filteredRecompenses = null;
        try {
            filteredRecompenses = rs.getAll().stream()
                    .filter(r -> {
                        switch (critere) {
                            case "Description":
                                return r.getDescription() != null &&
                                        r.getDescription().toLowerCase().contains(searchText.toLowerCase());
                            case "Coût en points":
                                return searchText.matches("\\d+") &&
                                        String.valueOf(r.getCout_en_points()).startsWith(searchText);
                            case "Disponibilité":
                                String disponibilite = r.getDisponibilite();
                                if (disponibilite != null) {
                                    // Recherche basée sur le début du mot pour "d" : doit correspondre à "Disponible" ou "Indisponible"
                                    return disponibilite.toLowerCase().startsWith(searchText.toLowerCase());
                                }
                                return false; // Si disponibilité est null ou vide, ne pas inclure l'élément
                            default:
                                return false;
                        }
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Résultats trouvés : " + filteredRecompenses.size()); // DEBUG

        listRec.setItems(FXCollections.observableArrayList(filteredRecompenses));
    }


    // 📌 Tri avec Streams
    private void handleSort() {
        String critere = comboBoxFilter.getSelectionModel().getSelectedItem();
        if (critere == null) return;

        Comparator<Recompense> comparator;

        switch (critere) {
            case "Description":
                comparator = Comparator.comparing(r -> r.getDescription().toLowerCase());
                break;
            case "Coût en points":
                comparator = Comparator.comparingInt(Recompense::getCout_en_points);
                break;
            case "Disponibilité":
                comparator = Comparator.comparing(r -> r.getDisponibilite().toLowerCase());
                break;
            default:
                return;
        }

        List<Recompense> sortedRecompenses = listRec.getItems().stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        listRec.setItems(FXCollections.observableArrayList(sortedRecompenses));
    }


    private void modifierRecompense(Recompense recompense, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/UpdateRecompense.fxml"));
            Parent root = loader.load();
            updateRec controller = loader.getController();
            controller.setRecompense(recompense);
            ((Button) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de UpdateRecompense.fxml", e);
        }
    }

    private void supprimerRecompense(Recompense recompense) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer la récompense");
        alert.setContentText("Es-tu sûr de vouloir supprimer cette récompense ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                rs.delete(recompense.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            afficherRecompenses();
        }
    }

    @FXML
    void mission(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/mission.fxml"));
            Parent root = loader.load();
            ((Button) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de mission.fxml", e);
        }
    }

    @FXML
    void recompense(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/recompense.fxml"));
            Parent root = loader.load();
            ((Button) event.getSource()).getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de recompense.fxml", e);
        }
    }
}
