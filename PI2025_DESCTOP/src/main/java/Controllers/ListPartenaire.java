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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Partenaire;
import services.CategorieService;
import services.PartenaireService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javafx.scene.control.Alert.AlertType;

import java.awt.Desktop;
import java.util.stream.Collectors;


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
    private Button sms;


    @FXML
    private Button Dropbox;
    @FXML
    private Button Suggest;

    @FXML
    void navigateToSuggestionPart(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/suggestionPart.fxml")); // Path to your suggestionPart.fxml
        try {
            Parent root = loader.load();  // Load the suggestionPart.fxml
            // Get the current scene and set the root to the new scene
            Suggest.getScene().setRoot(root);
        } catch (IOException e) {
            // Handle exception if loading fails
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger la page de suggestion.");
        }
    }


    @FXML
    void navigateToDropboxView(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dropbox_view.fxml")); // Path to your dropbox_view.fxml
        try {
            Parent root = loader.load();  // Load the dropbox_view FXML
            // Get the current scene and set the root to the new scene
            searchField.getScene().setRoot(root);
        } catch (IOException e) {
            // Handle exception if loading fails
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger la page Dropbox.");
        }
    }


    @FXML
    void navigateToSMS(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sms.fxml")); // Path to your AddPartenaire.fxml
        try {
            Parent root = loader.load();  // Load the AddPartenaire FXML
            // Get the current scene and set the root to the new scene
            searchField.getScene().setRoot(root);
        } catch (IOException e) {
            // Handle exception if loading fails
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger la page Partenaire.");
        }
    }

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
        TextField txtNumTel = new TextField(String.valueOf(partenaire.getNum_tel()));
        TextArea txtDescription = new TextArea(partenaire.getDescription());
        DatePicker dpDateAjout = new DatePicker(partenaire.getDate_ajout().toLocalDate());
        TextField txtMontant = new TextField(String.valueOf(partenaire.getMontant()));

        ImageView logoPreview = new ImageView();
        if (partenaire.getLogo() != null) {
            File logoFile = new File("../ProjetWebTrekSwap/public/uploads/partenaires/" + partenaire.getLogo());
            if (logoFile.exists()) {
                logoPreview.setImage(new Image(logoFile.toURI().toString()));
                logoPreview.setFitWidth(100);
                logoPreview.setFitHeight(100);
                logoPreview.setPreserveRatio(true);
            }
        }

        Button btnChooseImage = new Button("Choisir un nouveau logo");
        final String[] newLogoPath = {null}; // used to store selected image path

        btnChooseImage.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choisir une image");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.jpeg", "*.png"));
            File selectedFile = fileChooser.showOpenDialog(modificationStage);
            if (selectedFile != null) {
                newLogoPath[0] = selectedFile.getAbsolutePath();
                logoPreview.setImage(new Image(selectedFile.toURI().toString()));
            }
        });

        ComboBox<String> categorieBox = new ComboBox<>();
        try {
            categorieBox.getItems().addAll(cs.getAllNames());
            categorieBox.setValue(cs.getNomById(partenaire.getId_categorie()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        GridPane grid = new GridPane();
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        grid.add(new Label("Nom:"), 0, 0); grid.add(txtNom, 1, 0);
        grid.add(new Label("Email:"), 0, 1); grid.add(txtEmail, 1, 1);
        grid.add(new Label("Adresse:"), 0, 2); grid.add(txtAdresse, 1, 2);
        grid.add(new Label("Numéro de téléphone:"), 0, 3); grid.add(txtNumTel, 1, 3);
        grid.add(new Label("Montant (TND):"), 0, 4); grid.add(txtMontant, 1, 4);
        grid.add(new Label("Description:"), 0, 5); grid.add(txtDescription, 1, 5);
        grid.add(new Label("Date d'ajout:"), 0, 6); grid.add(dpDateAjout, 1, 6);
        grid.add(new Label("Catégorie:"), 0, 7); grid.add(categorieBox, 1, 7);
        grid.add(new Label("Logo actuel / nouveau:"), 0, 8); grid.add(logoPreview, 1, 8);
        grid.add(btnChooseImage, 1, 9);

        Button btnEnregistrer = new Button("Sauvegarder");
        btnEnregistrer.getStyleClass().add("btn-primary");
        grid.add(btnEnregistrer, 1, 10);

        btnEnregistrer.setOnAction(event -> {
            String newNom = txtNom.getText().trim();
            String newEmail = txtEmail.getText().trim();
            String newAdresse = txtAdresse.getText().trim();
            String newNumTel = txtNumTel.getText().trim();
            String newMontantStr = txtMontant.getText().trim();
            String newDescription = txtDescription.getText().trim();
            LocalDate newDateAjout = dpDateAjout.getValue();
            String newCategorie = categorieBox.getValue();

            // Validation
            if (newNom.isEmpty() || newNom.matches("\\d+")) {
                showAlert(AlertType.ERROR, "Erreur", "Le nom ne peut pas être vide ou contenir uniquement des chiffres."); return;
            }
            if (newEmail.isEmpty() || !newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                showAlert(AlertType.ERROR, "Erreur", "L'email n'est pas valide."); return;
            }
            if (newAdresse.isEmpty()) {
                showAlert(AlertType.ERROR, "Erreur", "L'adresse ne peut pas être vide."); return;
            }
            if (newNumTel.isEmpty() || !newNumTel.matches("\\d{8,15}")) {
                showAlert(AlertType.ERROR, "Erreur", "Le numéro de téléphone doit contenir entre 8 et 15 chiffres."); return;
            }
            if (newMontantStr.isEmpty()) {
                showAlert(AlertType.ERROR, "Erreur", "Le montant est obligatoire."); return;
            }
            double montant;
            try {
                montant = Double.parseDouble(newMontantStr);
                if (montant < 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                showAlert(AlertType.ERROR, "Erreur", "Montant invalide."); return;
            }
            if (newDescription.isEmpty() || newDescription.matches("\\d+")) {
                showAlert(AlertType.ERROR, "Erreur", "La description est invalide."); return;
            }
            if (newDateAjout == null || newDateAjout.isBefore(LocalDate.now())) {
                showAlert(AlertType.ERROR, "Erreur", "Veuillez sélectionner une date valide."); return;
            }
            if (newCategorie == null || newCategorie.isEmpty()) {
                showAlert(AlertType.ERROR, "Erreur", "Veuillez sélectionner une catégorie."); return;
            }

            partenaire.setNom(newNom);
            partenaire.setEmail(newEmail);
            partenaire.setAdresse(newAdresse);
            partenaire.setNum_tel(Integer.parseInt(newNumTel));
            partenaire.setMontant(montant);
            partenaire.setDescription(newDescription);
            partenaire.setDate_ajout(Date.valueOf(newDateAjout));

            try {
                partenaire.setId_categorie(cs.getIdByNom(newCategorie));

                // if new image selected, copy it and update the filename
                if (newLogoPath[0] != null) {
                    File originalFile = new File(newLogoPath[0]);
                    String extension = newLogoPath[0].substring(newLogoPath[0].lastIndexOf('.') + 1);
                    String uniqueFileName = java.util.UUID.randomUUID().toString() + "." + extension;

                    String baseDir = System.getProperty("user.dir");
                    Path destinationPath = Paths.get(baseDir, "../ProjetWebTrekSwap/public/uploads/partenaires", uniqueFileName).normalize();
                    Files.createDirectories(destinationPath.getParent());
                    Files.copy(originalFile.toPath(), destinationPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                    partenaire.setLogo(uniqueFileName); // update logo
                }

                ss.update(partenaire);
                afficherPartenaire();
                showAlert(AlertType.INFORMATION, "Succès", "Partenaire mis à jour avec succès.");
                modificationStage.close();
            } catch (Exception e) {
                showAlert(AlertType.ERROR, "Erreur", "Erreur lors de la mise à jour : " + e.getMessage());
            }
        });

        Scene scene = new Scene(grid, 550, 550);
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
                                // Assemble partner info
                                String partenaireInfo = "Nom: " + partenaire.getNom() + "\n"
                                        + "Email: " + partenaire.getEmail() + "\n"
                                        + "Adresse: " + partenaire.getAdresse() + "\n"
                                        + "Date d'ajout: " + partenaire.getDate_ajout() + "\n"
                                        + "Téléphone: " + partenaire.getNum_tel() + "\n"
                                        + "Montant: " + partenaire.getMontant() + " TND\n"
                                        + "Description: " + partenaire.getDescription();

                                Label label = new Label(partenaireInfo);
                                label.setWrapText(true);
                                label.setMaxWidth(300);

                                // ImageView for logo
                                ImageView logoView = new ImageView();
                                try {
                                    File logoFile = new File("../ProjetWebTrekSwap/public/uploads/partenaires/" + partenaire.getLogo());
                                    if (logoFile.exists()) {
                                        logoView.setImage(new Image(logoFile.toURI().toString()));
                                        logoView.setFitWidth(100);
                                        logoView.setFitHeight(100);
                                        logoView.setPreserveRatio(true);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                // Buttons
                                Button btnModifier = new Button("Modifier");
                                btnModifier.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5 10;");
                                btnModifier.setOnAction(event -> afficherFenetreModificationPartenaire(partenaire));

                                Button btnSupprimer = new Button("Supprimer");
                                btnSupprimer.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5 10;");
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

                                // Layout
                                HBox hboxButtons = new HBox(10, btnModifier, btnSupprimer);
                                VBox vbox = new VBox(10, label, logoView, hboxButtons);
                                vbox.setPadding(new Insets(10));

                                setGraphic(vbox);
                            }
                        }
                    };
                }
            });
        } catch (Exception e) {
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
    @FXML
    void Categorie(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichageCategorie.fxml"));
        try {
            Parent root = loader.load();
            searchField.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showDash(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
        try {
            Parent root = loader.load();
            searchField.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void logOut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        try {
            Parent root = loader.load();
            searchField.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
