package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Abonnement;

import services.ServiceAbonnement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ServicePack;
import utils.MyDb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private PieChart pieChart;
    private Connection connection = MyDb.getInstance().getConnection();

    @FXML
    private ComboBox<String> searchby;




    private void logAction(String action, Abonnement abonnement) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logMessage = timestamp + " - " + action + " Abonnement ID: " + abonnement.getId_Abonnement() +
                ", Pack ID: " + abonnement.getId_Pack() +
                ", User ID: " + abonnement.getId_utilisateur() +
                ", Status: " + abonnement.getStatut();

        System.out.println("📜 History Log: " + logMessage);
    }


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
        String recherche = idrecher.getText().trim(); // Retrieve search input
        String selectedAttribute = searchby.getValue(); // Get selected attribute from ComboBox

        // If search field is empty, reload all abonnements
        if (recherche.isEmpty() || selectedAttribute == null) {
            loadAbonnements(); // Show all abonnements
            return;
        }

        try {
            List<Abonnement> filteredResults = serviceAbonnement.getAll().stream()
                    .filter(abonnement -> {
                        // Perform filtering based on selected attribute
                        switch (selectedAttribute) {
                            case "id_abonnement":
                                return String.valueOf(abonnement.getId_Abonnement()).contains(recherche);
                            case "id_utilisateur":
                                return String.valueOf(abonnement.getId_utilisateur()).contains(recherche);
                            case "statut":
                                return abonnement.getStatut().toLowerCase().contains(recherche.toLowerCase());
                            case "id_Pack":
                                return String.valueOf(abonnement.getId_Pack()).contains(recherche);
                            case "date_Souscription":
                                return abonnement.getDate_Souscription().toString().contains(recherche);
                            case "date_Expiration":
                                return abonnement.getDate_Expiration().toString().contains(recherche);
                            default:
                                return false;
                        }
                    })
                    .collect(Collectors.toList());

            // Update the ListView with the filtered results
            ObservableList<Abonnement> observableResults = FXCollections.observableArrayList(filteredResults);
            listview.setItems(observableResults);
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erreur", "Une erreur est survenue lors de la recherche.");
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
        listview.setOnMouseClicked(event -> {
            Abonnement selectedA = listview.getSelectionModel().getSelectedItem();
            if (selectedA != null) {
                // Display pack details directly
                System.out.println("dateE: " + selectedA.getDate_Expiration());
                System.out.println("dateD: " + selectedA.getDate_Souscription());
                System.out.println("ID PACK: " + selectedA.getId_Pack());
                System.out.println("Statut: " + selectedA.getStatut());

            }
        });
        // Listener for TextField (search box)
        idrecher.textProperty().addListener((observable, oldValue, newValue) -> {
            recherche(null);  // Call the search method when text changes
        });

        // Check if ComboBox is initialized
        if (searchby == null) {
            System.out.println("⚠ ComboBox 'searchby' is not initialized!");
        } else {
            System.out.println("✅ ComboBox 'searchby' loaded successfully.");
            loadSearchOptions();

            // Add a listener to the ComboBox to trigger a search when changed
            searchby.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                recherche(null); // Call search when selection changes
            });
        }
    }


    private void loadSearchOptions() {
        searchby.getItems().addAll(
                "id_abonnement", "id_utilisateur", "statut",
                "id_Pack", "date_Souscription", "date_Expiration"
        );
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

    /*private void showAbonnementDetails() {
        Abonnement selectedAbonnement = listview.getSelectionModel().getSelectedItem();
        if (selectedAbonnement != null) {
            pack.setText(String.valueOf(selectedAbonnement.getId_Pack()));
            dateS.setText(selectedAbonnement.getDate_Souscription().toString());
            dateE.setText(selectedAbonnement.getDate_Expiration().toString());
            statuta.setText(selectedAbonnement.getStatut());
        }
    }*/

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
                logAction("Deleted", selectedA);
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
                logAction("Updated", selectedA);
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
    void stat(ActionEvent event) {
        updatePieChartWithStatutCount();
    }

    private void updatePieChartWithStatutCount() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        String sql = "SELECT statut, COUNT(*) as count FROM abonnement GROUP BY statut";  // Adjust this query as needed

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String statut = rs.getString("statut");  // Ensure the column name 'statut' matches the database
                int count = rs.getInt("count");

                // Add data to the PieChart
                pieChartData.add(new PieChart.Data(statut + " (" + count + ")", count));
            }
        } catch (SQLException e) {
            // Handle any database-related errors
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }

        // Clear old data and update the PieChart with the new data
        if (!pieChartData.isEmpty()) {
            pieChart.getData().clear();
            pieChart.getData().addAll(pieChartData);
        } else {
            System.out.println("No data to display.");
        }

        // Add tooltips to display values on mouse hover
        pieChartData.forEach(data -> {
            data.getNode().setOnMouseEntered(event1 -> {
                System.out.println(data.getName() + " : " + data.getPieValue());
            });
        });
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
