package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Abonnement;
import models.Pack;
import services.ServiceAbonnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ServicePack;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ShowAbonnement {

    @FXML
    private ListView<Abonnement> listview;

    @FXML
    private VBox DetailsA;

    @FXML
    private TextField idrecher;

    @FXML
    private Label pack, dateE, dateS, statuta;

    private final ServiceAbonnement serviceAbonnement = new ServiceAbonnement();
    private final ServicePack servicePack = new ServicePack();  // Create instance of ServicePack


    @FXML
    void tri(ActionEvent event) {
        try {
            // Trier les abonnements par statut (ordre alphabétique croissant)
            List<Abonnement> resultatsTries = serviceAbonnement.getAll().stream()
                    .sorted((a1, a2) -> a1.getStatut().compareToIgnoreCase(a2.getStatut())) // Comparaison alphabétique par statut
                    .collect(Collectors.toList());

            // Mettre à jour la ListView avec les résultats triés
            ObservableList<Abonnement> observableResults = FXCollections.observableArrayList(resultatsTries);
            listview.setItems(observableResults); // Affecte les résultats triés à la ListView

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception
            showAlert("Erreur", "Une erreur est survenue lors du tri des abonnements.");
        }
    }






    @FXML
    void recherche(InputMethodEvent event) {
        String recherche = idrecher.getText().trim(); // Retrieve the text entered in the TextField

        // If the search field is empty, reload all the abonnements (show all)
        if (recherche.isEmpty()) {
            loadAbonnements(); // Load all abonnements if nothing is typed
            return;
        }

        try {
            // Filter the abonnements by avantages using Stream API (case-insensitive)
            List<Abonnement> resultats = serviceAbonnement.getAll().stream() // Assuming serviceAbonnement is your Service class
                    .filter(abonnement -> abonnement.getStatut().toLowerCase()  // Filter based on the avantages field
                            .contains(recherche.toLowerCase()))
                    .collect(Collectors.toList());

            // Update the ListView with the filtered results
            ObservableList<Abonnement> observableResults = FXCollections.observableArrayList(resultats);
            listview.setItems(observableResults); // Set the filtered results in the ListView
        } catch (SQLException e) {
            e.printStackTrace();  // Handle the exception
            showAlert("Erreur", "Une erreur est survenue lors de la recherche des abonnements.");
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);  // You can change the type (ERROR, WARNING, INFORMATION) as needed
        alert.setTitle(title);  // Set the title of the alert
        alert.setHeaderText(null);  // Optionally, you can set a header, but in this case, it's null
        alert.setContentText(message);  // Set the content message for the alert
        alert.showAndWait();  // Show the alert and wait for the user to close it
    }





    @FXML
    void initialize() throws SQLException {
        loadAbonnements();
        listview.setOnMouseClicked(event -> showAbonnementDetails());
        idrecher.textProperty().addListener((observable, oldValue, newValue) -> {
            recherche(null);  // Call the recherche method with the updated text from the TextField
        });
    }

    private void loadAbonnements() {
        ObservableList<Abonnement> abonnementList = FXCollections.observableArrayList();

        try {
            List<Abonnement> abonnements = serviceAbonnement.getAll();  // Fetch packs from database
            abonnementList.addAll(abonnements);  // Add all packs to the observable list
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listview.setItems(abonnementList);

        // Set a custom cell factory to format how packs are displayed
        listview.setCellFactory(param -> new ListCell<Abonnement>() {
            @Override
            protected void updateItem(Abonnement item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Get the pack name by calling getIdByName function
                    String packName = servicePack.getPackNameById(item.getId_Pack());

                    // Set the text with the pack name instead of id_Pack
                    setText(
                            "  Pack: " + (packName != null ? packName : "Inconnu") +
                                    " | 📅 Début: " + item.getDate_Souscription() +
                                    " | 🔚 Fin: " + item.getDate_Expiration() +
                                    " | 🔄 Statut: " + item.getStatut());
                }
            }
        });
    }

    private void showAbonnementDetails() {
        Abonnement selectedAbonnement = listview.getSelectionModel().getSelectedItem();
        if (selectedAbonnement != null) {
            pack.setText(String.valueOf(selectedAbonnement.getId_Pack()));
            dateS.setText(selectedAbonnement.getDate_Souscription().toString());
            dateE.setText(selectedAbonnement.getDate_Expiration().toString());
            statuta.setText(selectedAbonnement.getStatut());
        }
    }

    @FXML
    void supprimerA(ActionEvent event) {
        Abonnement selectedA = listview.getSelectionModel().getSelectedItem();
        if (selectedA != null) {
            ServiceAbonnement serviceA= new ServiceAbonnement(); // Service de suppression du pack
            try {
                // Suppression du pack de la base de données
                serviceA.delete(selectedA.getId_Abonnement());

                // Retirer le pack de la ListView pour mettre à jour l'affichage
                listview.getItems().remove(selectedA);
                listview.refresh();

                System.out.println("✅ Abonnement supprimé avec succès !");
                loadAbonnements();// Confirmation dans la console
            } catch (SQLException e) {
                System.out.println("❌ Erreur : L'abonnement n'a pas pu être supprimé de la base de données.");
                e.printStackTrace();  // Pour plus de détails sur l'erreur
            }
        } else {
            System.out.println("⚠ Aucun abonnement sélectionné. Veuillez sélectionner un pack à supprimer.");
        }

    }

    @FXML
    void updateabonnement(ActionEvent event) {
        Abonnement selectedA = listview.getSelectionModel().getSelectedItem();

        if (selectedA != null) {
            try {
                // Open the update window with the selected Pack
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateAbonnement.fxml"));
                Parent root = loader.load();

                UpdateAbonnement updateAController = loader.getController();
                updateAController.setAbonnDetails(selectedA);

                Scene currentScene = ((Node) event.getSource()).getScene();
                currentScene.setRoot(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("⚠ Please select a pack to modify.");
        }

    }
    @FXML
    void reload(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ShowAbonnement.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/AddAbonnement.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void showDash(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
        try {
            Parent root = loader.load();
            idrecher.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void showAbon(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowAbonnement.fxml"));
        try {
            Parent root = loader.load();
            idrecher.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void showPack(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowPack.fxml"));
        try {
            Parent root = loader.load();
            idrecher.getScene().setRoot(root);

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
            idrecher.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
