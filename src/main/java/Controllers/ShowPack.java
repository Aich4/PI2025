package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import services.ServicePack;
import models.Pack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ShowPack {

    @FXML
    private ListView<Pack> listview;

    @FXML
    private VBox DetailsP;

    @FXML
    private TextField idrecher;

    @FXML
    private Label nompack, desc, dure, priix, avantage, statut;

    private final ServicePack servicePack = new ServicePack(); // Service to fetch packs

    private Pack selectedPack;

    @FXML
    void tri(ActionEvent event) {
        try {
            // Trier les packs par nom de pack (ordre alphabétique croissant)
            List<Pack> resultatsTries = servicePack.getAll().stream()
                    .sorted((p1, p2) -> p1.getNom_Pack().compareToIgnoreCase(p2.getNom_Pack())) // Comparaison alphabétique par nom de pack
                    .collect(Collectors.toList());

            // Mettre à jour la ListView avec les résultats triés
            ObservableList<Pack> observableResults = FXCollections.observableArrayList(resultatsTries);
            listview.setItems(observableResults); // Affecte les résultats triés à la ListView

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception
            showAlert("Erreur", "Une erreur est survenue lors du tri des packs.");
        }
    }




    @FXML
    void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/AddPack.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void actualiser(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/ShowPack.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void recherche(InputMethodEvent event) {

            String recherche = idrecher.getText().trim(); // Retrieve the text entered in the TextField

            // If the search field is empty, reload all the packs (show all)
            if (recherche.isEmpty()) {
                loadPacks(); // Load all packs if nothing is typed
                return;
            }

            try {
                // Filter the packs by name using Stream API (case-insensitive)
                List<Pack> resultats = servicePack.getAll().stream() // Assuming servicePack is your Service class
                        .filter(pack -> pack.getNom_Pack().toLowerCase()
                                .contains(recherche.toLowerCase()))  // Assuming getNom_Pack() returns the pack name
                        .collect(Collectors.toList());

                // Update the ListView with the filtered results
                ObservableList<Pack> observableResults = FXCollections.observableArrayList(resultats);
                listview.setItems(observableResults); // Set the filtered results in the ListView
            } catch (SQLException e) {
                e.printStackTrace();  // Handle the exception
                showAlert("Erreur", "Une erreur est survenue lors de la recherche des packs.");
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
        System.out.println("dkhal");
        loadPacks();

        // Handle item selection
        listview.setOnMouseClicked(event -> showPackDetails());
        idrecher.textProperty().addListener((observable, oldValue, newValue) -> {
            recherche(null);  // Call the recherche method with the updated text from the TextField
        });
    }

    private void loadPacks() {
        ObservableList<Pack> packList = FXCollections.observableArrayList();

        try {
            List<Pack> packs = servicePack.getAll();  // Fetch packs from database
            packList.addAll(packs);  // Add all packs to the observable list
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listview.setItems(packList);

        // Set a custom cell factory to format how packs are displayed
        listview.setCellFactory(param -> new ListCell<Pack>() {
            @Override
            protected void updateItem(Pack pack, boolean empty) {
                super.updateItem(pack, empty);
                if (empty || pack == null) {
                    setText(null);
                } else {
                    setText(pack.getNom_Pack() + " - " +
                            pack.getDescription() + " - " +
                            pack.getPrix() + " TND - " +
                            pack.getDuree() + " days - " +
                            pack.getAvantages() + " - " +
                            pack.getStatut());
                }
            }
        });
    }



    private void showPackDetails() {
        Pack selectedPack = listview.getSelectionModel().getSelectedItem();
        if (selectedPack != null) {
            // Set the details in the labels from the selected Pack
            nompack.setText(selectedPack.getNom_Pack());
            desc.setText(selectedPack.getDescription());
            dure.setText(String.valueOf(selectedPack.getDuree()));
            priix.setText(String.valueOf(selectedPack.getPrix()));
            avantage.setText(selectedPack.getAvantages());
            statut.setText(selectedPack.getStatut());
        }
    }

    @FXML
    void updatepack(ActionEvent event) {
        Pack selectedPack = listview.getSelectionModel().getSelectedItem();

        if (selectedPack != null) {
            try {
                // Open the update window with the selected Pack
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdatePack.fxml"));
                Parent root = loader.load();

                UpdatePack updatePackController = loader.getController();
                updatePackController.setPackDetails(selectedPack); // Pass selected Pack object to the update screen

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
    void supprimer(ActionEvent event) throws SQLException {
        // Assumons que vous avez déjà une ListView appelée 'listview' pour les packs
        Pack selectedPack = listview.getSelectionModel().getSelectedItem();
        if (selectedPack != null) {
            ServicePack servicePack = new ServicePack(); // Service de suppression du pack
            try {
                // Suppression du pack de la base de données
                servicePack.delete(selectedPack.getId_Pack());

                // Retirer le pack de la ListView pour mettre à jour l'affichage
               /* listview.getItems().remove(selectedPack);
                listview.refresh();*/

                System.out.println("✅ Pack supprimé avec succès !");
                loadPacks();// Confirmation dans la console
            } catch (SQLException e) {
                System.out.println("❌ Erreur : Le pack n'a pas pu être supprimé de la base de données.");
                e.printStackTrace();  // Pour plus de détails sur l'erreur
            }
        } else {
            System.out.println("⚠ Aucun pack sélectionné. Veuillez sélectionner un pack à supprimer.");
        }

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
    void logOut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        try {
            Parent root = loader.load();
            listview.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}



















