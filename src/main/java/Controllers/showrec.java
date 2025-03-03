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


import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;


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
            // Récupérer toutes les récompenses depuis la base de données
            List<Recompense> recompenses = rs.getAll();
            ObservableList<Recompense> observableRecompenses = FXCollections.observableArrayList(recompenses);

            // Associer les récompenses à la ListView
            listRec.setItems(observableRecompenses);

            // Personnalisation de l'affichage avec une CellFactory
            listRec.setCellFactory(param -> new ListCell<Recompense>() {
                @Override
                protected void updateItem(Recompense recompense, boolean empty) {
                    super.updateItem(recompense, empty);

                    if (empty || recompense == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // 🔹 Création des éléments graphiques
                        Label lblDescription = new Label("📜 Description : " + recompense.getDescription());
                        Label lblCout = new Label("💰 Coût : " + recompense.getCout_en_points() + " points");
                        Label lblDisponibilite = new Label("📅 Disponibilité : " + recompense.getDisponibilite());

                        // 🎨 Appliquer du style
                        lblDescription.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
                        lblCout.setStyle("-fx-font-size: 13px;");
                        lblDisponibilite.setStyle("-fx-font-size: 13px;");

                        // 🔘 Création des boutons
                        Button btnModifier = new Button("✏ Modifier");
                        Button btnSupprimer = new Button("🗑 Supprimer");

                        // 🛠 Actions des boutons
                        btnModifier.setOnAction(event -> modifierRecompense(recompense, event));
                        btnSupprimer.setOnAction(event -> supprimerRecompense(recompense));

                        // 📌 Organisation des éléments
                        VBox detailsBox = new VBox(5, lblDescription, lblCout, lblDisponibilite);
                        HBox buttonsBox = new HBox(10, btnModifier, btnSupprimer);
                        VBox fullBox = new VBox(10, detailsBox, buttonsBox);

                        // Application des styles pour l'espacement et les dimensions
                        detailsBox.setPadding(new Insets(5));
                        buttonsBox.setPadding(new Insets(5));
                        fullBox.setPadding(new Insets(10));
                        fullBox.setStyle("-fx-border-color: #ccc; -fx-border-radius: 10; -fx-padding: 10; -fx-background-radius: 10;");

                        // Définir l'élément graphique de la cellule
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
        // Ajouter un écouteur pour le bouton "Trier"
        sortButton.setOnAction(event -> handleSort());

        // Ajouter un écouteur sur le champ de recherche pour effectuer la recherche pendant la saisie
        searchLabel.textProperty().addListener((observable, oldValue, newValue) -> handleSearch(newValue)); // Met à jour lors de la saisie
    }

    private void handleSearch(String searchText) {
        String critere = comboBoxFilter.getSelectionModel().getSelectedItem();

        if (critere == null || searchText.isEmpty()) {
            try {
                listRec.setItems(FXCollections.observableArrayList(rs.getAll()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        try {
            List<Recompense> filteredRecompenses;

            if (critere.equals("Coût en points")) {
                // Vérifier si la saisie est un nombre valide
                if (!searchText.matches("\\d+")) {
                    listRec.setItems(FXCollections.observableArrayList()); // Liste vide si lettres dans "Coût"
                    return;
                }
            }

            // Appeler la méthode de recherche avec le critère et la saisie
            filteredRecompenses = rs.searchRecompenses(critere, searchText);

            // Vérifier si la liste n'est pas vide avant de l'afficher
            if (filteredRecompenses.isEmpty()) {
                System.out.println("Aucune récompense trouvée pour ce critère.");
            }

            listRec.setItems(FXCollections.observableArrayList(filteredRecompenses));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    private void handleSort() {
        String critere = comboBoxFilter.getSelectionModel().getSelectedItem();

        if (critere != null) {
            try {
                List<Recompense> sortedRecompenses = rs.sortRecompenses(critere);
                listRec.setItems(FXCollections.observableArrayList(sortedRecompenses));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }






    // Méthode pour modifier une récompense
    private void modifierRecompense(Recompense recompense, ActionEvent event) {
        try {
            // Charger la vue FXML de modification de la récompense
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/UpdateRecompense.fxml"));
            Parent root = loader.load();

            // Accéder au contrôleur de la vue UpdateRecompense
            updateRec controller = loader.getController();

            // Passer la recompense sélectionnée au contrôleur de UpdateRecompense
            controller.setRecompense(recompense);

            // Mettre à jour la scène avec la nouvelle vue
            ((Button) event.getSource()).getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur de chargement de UpdateRecompense.fxml", e);
        }
    }


    // Méthode pour afficher une alerte
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    // Méthode pour supprimer une récompense
    private void supprimerRecompense(Recompense recompense) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Supprimer la récompense");
        alert.setContentText("Es-tu sûr de vouloir supprimer cette récompense ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                rs.delete(recompense.getId()); // Suppression dans la base de données
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            afficherRecompenses(); // Rafraîchir la liste
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



