package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Partenaire;
import services.CategorieService;
import services.PartenaireService;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;

import java.awt.Desktop;
import java.net.URI;
import java.util.stream.Collectors;
import models.Partenaire;  // Replace with the actual package name of Partenaire


public class ListPartenaire {

    private PartenaireService ss;
    private CategorieService cs;
    private ObservableList<Partenaire> partenairesList;//ajout


    private Partenaire selectedPartenaire;

    public ListPartenaire() {
        this.ss = new PartenaireService();
        this.cs = new CategorieService();
    }

    @FXML
    private ComboBox<String> searchCriteriaComboBox;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;

    @FXML
    private ListView<Partenaire> listepartenaire;

    @FXML
    private ComboBox<String> typeTri;

    @FXML
    void initialize() {
        // Add items to the search criteria combo box and set default value
        searchCriteriaComboBox.getItems().addAll("Nom", "Email", "Adresse", "Description");
        searchCriteriaComboBox.setValue("Nom"); // Set default value to "Nom"

        // Add items to the sorting order combo box (typeTri) and set default value
        typeTri.getItems().addAll("Asc", "Desc");
        typeTri.setValue("Asc"); // Default sorting order is ascending

        // Display all partners initially
        afficherPartenaire();

        // Add event listener for dynamic search when the text changes in the search field
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchPartenaire();  // Trigger the search whenever the user types
        });

        // Add mouse click listener for selecting a partner from the list
        listepartenaire.setOnMouseClicked(event -> {
            selectedPartenaire = listepartenaire.getSelectionModel().getSelectedItem();
            if (selectedPartenaire != null) {
                System.out.println("Partenaire sélectionné : " + selectedPartenaire.getNom());
            }
        });
    }



    private ObservableList<Partenaire> allpartenaire = FXCollections.observableArrayList();
// Ensure this is populated before calling searchPartenaire()



    @FXML
    void searchPartenaire() {
        if (allpartenaire == null) {
            showAlert(AlertType.ERROR, "Erreur", "La liste des partenaires n'a pas été chargée.");
            return;
        }

        String selectedCriterion = searchCriteriaComboBox.getValue(); // Get selected search criterion
        String searchText = searchField.getText().trim(); // Get text entered in the search field

        // Debugging: Print search parameters
        System.out.println("Selected Criterion: " + selectedCriterion);
        System.out.println("Search Text: " + searchText);

        if (selectedCriterion == null || searchText.isEmpty()) {
            // If search field is empty or no criterion is selected, show all partenaires
            listepartenaire.setItems(FXCollections.observableArrayList(allpartenaire));
            return;
        }

        try {
            // Filter using Stream on the allpartenaire list
            List<Partenaire> filteredPartenaires = allpartenaire.stream()
                    .filter(partenaire -> {
                        switch (selectedCriterion) {
                            case "Nom":
                                return partenaire.getNom().toLowerCase().contains(searchText.toLowerCase());
                            case "Email":
                                return partenaire.getEmail().toLowerCase().contains(searchText.toLowerCase());
                            case "Adresse":
                                return partenaire.getAdresse().toLowerCase().contains(searchText.toLowerCase());
                            case "Description":
                                return partenaire.getDescription().toLowerCase().contains(searchText.toLowerCase());
                            default:
                                return false;
                        }
                    })
                    .collect(Collectors.toList());

            // Debugging: Print filtered results
            System.out.println("Filtered Partenaires: " + filteredPartenaires);

            // Update the list view with filtered partenaires
            listepartenaire.setItems(FXCollections.observableArrayList(filteredPartenaires));
        } catch (Exception e) {
            // Show error if something goes wrong
            showAlert(AlertType.ERROR, "Erreur", "Erreur lors de la recherche : " + e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void trierPartenaires() {
        if (allpartenaire.isEmpty()) return;

        String critere = searchCriteriaComboBox.getValue();
        String ordre = typeTri.getValue();

        Comparator<Partenaire> comparator = null;

        switch (critere) {
            case "Nom":
                comparator = Comparator.comparing(Partenaire::getNom);
                break;
            case "Email":
                comparator = Comparator.comparing(Partenaire::getEmail);
                break;
            case "Adresse":
                comparator = Comparator.comparing(Partenaire::getAdresse);
                break;
            case "Description":
                comparator = Comparator.comparing(Partenaire::getDescription);
                break;
        }

        if (comparator != null) {
            if ("Desc".equals(ordre)) {
                comparator = comparator.reversed();
            }

            List<Partenaire> sortedList = new ArrayList<>(listepartenaire.getItems());
            sortedList.sort(comparator);
            listepartenaire.setItems(FXCollections.observableArrayList(sortedList));
        }
    }




    @FXML
    void goToAdd(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddPartenaire.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherFenetreModificationPartenaire(Partenaire partenaire) {
        Stage modificationStage = new Stage();
        modificationStage.setTitle("Modifier Partenaire");

        TextField txtNom = new TextField(partenaire.getNom());
        TextField txtEmail = new TextField(partenaire.getEmail());
        TextField txtAdresse = new TextField(partenaire.getAdresse());
        TextField txtNumTel = new TextField(String.valueOf(partenaire.getNum_tel())); // Ajout du champ num_tel
        TextArea txtDescription = new TextArea(partenaire.getDescription());
        DatePicker dpDateAjout = new DatePicker(partenaire.getDate_ajout().toLocalDate());

        ComboBox<String> categorieBox = new ComboBox<>();
        try {
            categorieBox.getItems().addAll(cs.getAllNames());
            categorieBox.setValue(cs.getNomById(partenaire.getId_categorie()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // GridPane layout for organization
        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(txtNom, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(txtEmail, 1, 1);
        grid.add(new Label("Adresse:"), 0, 2);
        grid.add(txtAdresse, 1, 2);
        grid.add(new Label("Numéro de téléphone:"), 0, 3); // Ajout du label
        grid.add(txtNumTel, 1, 3); // Ajout du champ de saisie
        grid.add(new Label("Description:"), 0, 4);
        grid.add(txtDescription, 1, 4);
        grid.add(new Label("Date d'ajout:"), 0, 5);
        grid.add(dpDateAjout, 1, 5);
        grid.add(new Label("Catégorie:"), 0, 6);
        grid.add(categorieBox, 1, 6);

        Button btnEnregistrer = new Button("Sauvegarder");
        btnEnregistrer.getStyleClass().add("btn-primary");
        grid.add(btnEnregistrer, 1, 7);

        btnEnregistrer.setOnAction(event -> {
            String newNom = txtNom.getText().trim();
            String newEmail = txtEmail.getText().trim();
            String newAdresse = txtAdresse.getText().trim();
            String newNumTel = txtNumTel.getText().trim();
            String newDescription = txtDescription.getText().trim();
            LocalDate newDateAjout = dpDateAjout.getValue();
            String newCategorie = categorieBox.getValue();

            // Validation
            if (newNom.isEmpty() || newNom.matches("\\d+")) {
                showAlert(AlertType.ERROR, "Erreur", "Le nom ne peut pas être vide ou contenir uniquement des chiffres.");
                return;
            }
            if (newEmail.isEmpty() || !newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                showAlert(AlertType.ERROR, "Erreur", "L'email n'est pas valide.");
                return;
            }
            if (newAdresse.isEmpty()) {
                showAlert(AlertType.ERROR, "Erreur", "L'adresse ne peut pas être vide.");
                return;
            }
            if (newNumTel.isEmpty() || !newNumTel.matches("\\d{8,15}")) { // Validation du numéro de téléphone
                showAlert(AlertType.ERROR, "Erreur", "Le numéro de téléphone doit contenir entre 8 et 15 chiffres.");
                return;
            }
            if (newDescription.isEmpty() || newDescription.matches("\\d+")) {
                showAlert(AlertType.ERROR, "Erreur", "La description ne peut pas être vide ou contenir uniquement des chiffres.");
                return;
            }
            if (newDateAjout == null || newDateAjout.isBefore(LocalDate.now())) {
                showAlert(AlertType.ERROR, "Erreur", "Veuillez sélectionner une date valide (aujourd'hui ou une date future).");
                return;
            }
            if (newCategorie == null || newCategorie.isEmpty()) {
                showAlert(AlertType.ERROR, "Erreur", "Veuillez sélectionner une catégorie.");
                return;
            }

            // Mise à jour de l'objet partenaire
            partenaire.setNom(newNom);
            partenaire.setEmail(newEmail);
            partenaire.setAdresse(newAdresse);
            partenaire.setNum_tel(Integer.parseInt(newNumTel)); // Mise à jour du numéro de téléphone
            partenaire.setDescription(newDescription);
            partenaire.setDate_ajout(Date.valueOf(newDateAjout));

            try {
                partenaire.setId_categorie(cs.getIdByNom(newCategorie));
                ss.update(partenaire);
                afficherPartenaire();
                showAlert(AlertType.INFORMATION, "Succès", "Partenaire mis à jour avec succès.");
                modificationStage.close();
            } catch (Exception e) {
                showAlert(AlertType.ERROR, "Erreur", "Erreur lors de la mise à jour : " + e.getMessage());
            }
        });

        Scene scene = new Scene(grid, 450, 450); // Ajustement de la taille
        modificationStage.setScene(scene);
        modificationStage.show();
    }


    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void afficherPartenaire() {
        try {
            List<Partenaire> partenaires = ss.getAll();  // Fetch all partners
            allpartenaire.clear();  // Clear previous data to avoid duplicates
            allpartenaire.addAll(partenaires);  // Add fetched partners
            listepartenaire.setItems(FXCollections.observableArrayList(allpartenaire));  // Update the list view

            listepartenaire.setCellFactory(new Callback<ListView<Partenaire>, ListCell<Partenaire>>() {
                @Override
                public ListCell<Partenaire> call(ListView<Partenaire> param) {
                    return new ListCell<Partenaire>() {
                        @Override
                        protected void updateItem(Partenaire partenaire, boolean empty) {
                            super.updateItem(partenaire, empty);

                            if (empty || partenaire == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                String partenaireInfo = "Nom: " + partenaire.getNom() + "\n"
                                        + "Email: " + partenaire.getEmail() + "\n"
                                        + "Adresse: " + partenaire.getAdresse() + "\n"
                                        + "Date d'ajout: " + partenaire.getDate_ajout() + "\n"
                                        + "Numéro de téléphone: " + partenaire.getNum_tel() + "\n"
                                        + "Description: " + partenaire.getDescription();

                                VBox vbox = new VBox();
                                Label label = new Label(partenaireInfo);
                                label.setWrapText(true);
                                label.setMaxWidth(300);

                                Button btnModifier = new Button("Modifier");
                                Button btnSupprimer = new Button("Supprimer");

                                btnModifier.setOnAction(event -> afficherFenetreModificationPartenaire(partenaire));
                                btnSupprimer.setOnAction(event -> {
                                    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                                    confirmationAlert.setTitle("Confirmer la suppression");
                                    confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce partenaire ?");
                                    confirmationAlert.setContentText("Cette action est irréversible.");
                                    confirmationAlert.showAndWait().ifPresent(response -> {
                                        if (response == ButtonType.OK) {
                                            try {
                                                ss.delete(partenaire.getId());
                                                cs.decrementNbrCategorie(partenaire.getId_categorie());
                                                afficherPartenaire();  // Refresh list after deletion
                                                showAlert(Alert.AlertType.INFORMATION, "Succès", "Le partenaire a été supprimé avec succès !");
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                });

                                HBox hbox = new HBox(10, btnModifier, btnSupprimer);
                                vbox.getChildren().addAll(label, hbox);
                                setGraphic(vbox);
                            }
                        }
                    };
                }
            });
        } catch (Exception e) {
            // Show error to user and log it
            showAlert(Alert.AlertType.ERROR, "Erreur", "Une erreur est survenue lors de la récupération des partenaires.");
            e.printStackTrace();
        }
    }


    @FXML
    void exportToPDF(ActionEvent event) {
        if (selectedPartenaire == null) {
            showAlert(AlertType.ERROR, "Erreur", "Aucun partenaire sélectionné.");
            return;
        }

        try {
            // Créer un document PDF
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            // Créer un flux de contenu pour écrire dans le PDF
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Début du texte
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(25, 750); // Position initiale du texte

            // Ajouter les informations du partenaire avec un style simulé
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.setNonStrokingColor(0, 0, 0); // Couleur du texte: noir
            contentStream.showText("Nom: " + selectedPartenaire.getNom());
            contentStream.newLine();

            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.showText("Email: " + selectedPartenaire.getEmail());
            contentStream.newLine();

            contentStream.showText("Adresse: " + selectedPartenaire.getAdresse());
            contentStream.newLine();

            contentStream.showText("Date d'ajout: " + selectedPartenaire.getDate_ajout());
            contentStream.newLine();

            contentStream.showText("Description: " + selectedPartenaire.getDescription());
            contentStream.endText();

            // Fermer le flux de contenu
            contentStream.close();

            // Sauvegarder le fichier PDF généré
            File file = new File("Partenaire_" + selectedPartenaire.getNom() + ".pdf");
            document.save(file);
            document.close();

            // Ouvrir le fichier PDF généré
            Desktop.getDesktop().open(file);

            showAlert(AlertType.INFORMATION, "Succès", "PDF généré avec succès.");

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Erreur", "Erreur lors de l'exportation PDF.");
        }
    }



    // Cette méthode est un exemple de sélection du partenaire pour exporter ses informations
    public void setSelectedPartenaire(Partenaire selectedPartenaire) {
        this.selectedPartenaire = selectedPartenaire;
    }
}
