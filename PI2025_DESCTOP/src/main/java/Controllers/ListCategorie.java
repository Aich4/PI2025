package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CategorieService;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import models.Categorie;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ListCategorie {

    CategorieService ss;

    public ListCategorie() {
        this.ss = new CategorieService();
    }

    @FXML
    private ListView<Categorie> listcategorie;

    @FXML
    void initialize() {
        afficherCategories();
    }

    @FXML
    void goToCat(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddCategorie.fxml"));
            Parent root = loader.load();

            // Récupérer la scène actuelle et changer le contenu
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void afficherCategories() {
        try {
            List<Categorie> categories = ss.getAll(); // Récupérer la liste des catégories depuis la base de données
            ObservableList<Categorie> observableCategories = FXCollections.observableArrayList(categories);

            listcategorie.setItems(observableCategories);
            listcategorie.setCellFactory(param -> new ListCell<Categorie>() {
                private final ImageView imageView = new ImageView();
                private final Button btnModifier = new Button("Modifier");
                private final Button btnSupprimer = new Button("Supprimer");

                @Override
                protected void updateItem(Categorie categorie, boolean empty) {
                    super.updateItem(categorie, empty);

                    if (empty || categorie == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // Affichage des informations de la catégorie
                        setText(null); // Clear any previous text

                        // Charger l'image du logo si disponible
                        if (categorie.getLogo() != null && !categorie.getLogo().isEmpty()) {
                            File imageFile = new File("../ProjetWebTrekSwap/public/uploads/logos/" + categorie.getLogo());
                            if (imageFile.exists()) {
                                imageView.setImage(new Image(imageFile.toURI().toString(), 50, 50, true, true));
                            }
                        }
                        else {
                            imageView.setImage(null);
                        }

                        // Créer un HBox avec l'image et les informations
                        Label label = new Label(categorie.getNom() + " - " + categorie.getDescription());
                        HBox leftHBox = new HBox(10, imageView, label);

                        // Créer un HBox pour les boutons Modifier et Supprimer et pousser à droite
                        HBox rightHBox = new HBox(10);
                        rightHBox.setHgrow(rightHBox, Priority.ALWAYS);
                        rightHBox.getChildren().addAll(btnModifier, btnSupprimer);

                        // Ajouter les deux HBox dans un seul
                        HBox fullHBox = new HBox(leftHBox, rightHBox);
                        fullHBox.setSpacing(10);

                        // Gérer l'action du bouton Modifier
                        btnModifier.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5 10;");

                        btnModifier.setOnAction(event -> afficherFenetreModificationCategorie(categorie));


                        // Événement du bouton "Supprimer"
                        btnSupprimer.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5 10;");

                        btnSupprimer.setOnAction(event -> {
                            // Créer une alerte de confirmation
                            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                            confirmationAlert.setTitle("Confirmer la suppression");
                            confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette catégorie ?");
                            confirmationAlert.setContentText("Cette action est irréversible.");

                            // Attendre la réponse de l'utilisateur
                            confirmationAlert.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    try {
                                        ss.delete(categorie.getId());  // Supprimer la catégorie de la base de données
                                        afficherCategories();  // Rafraîchir la liste après suppression

                                        // Afficher une notification de succès
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Succès");
                                        alert.setHeaderText(null);
                                        alert.setContentText("La catégorie a été supprimée avec succès !");
                                        alert.showAndWait();  // Afficher la notification
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        });

                        // Appliquer le HBox complet au graphique
                        setGraphic(fullHBox);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void afficherFenetreModificationCategorie(Categorie categorie) {
        Stage modificationStage = new Stage();
        modificationStage.setTitle("Modifier Catégorie");

        // Input fields
        TextField txtNom = new TextField(categorie.getNom());
        TextField txtDescription = new TextField(categorie.getDescription());
        final String[] newLogoPath = {null}; // Holds new selected image path (full)

        // Labels
        Label lblNom = new Label("Nom:");
        Label lblDescription = new Label("Description:");
        Label lblLogo = new Label("Logo:");

        // Image preview
        ImageView imageView = new ImageView();
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        imageView.setPreserveRatio(true);

        // Load current image
        if (categorie.getLogo() != null && !categorie.getLogo().isEmpty()) {
            File logoFile = new File("../ProjetWebTrekSwap/public/uploads/logos/" + categorie.getLogo());
            if (logoFile.exists()) {
                imageView.setImage(new Image(logoFile.toURI().toString()));
            }
        }

        // Choose new image button
        Button btnChoisirImage = new Button("Choisir Image");
        btnChoisirImage.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(modificationStage);

            if (file != null) {
                newLogoPath[0] = file.getAbsolutePath(); // Store selected path
                imageView.setImage(new Image(file.toURI().toString())); // Preview
            }
        });

        // Save button
        Button btnEnregistrer = new Button("Sauvegarder");
        btnEnregistrer.setOnAction(event -> {
            String newNom = txtNom.getText().trim();
            String newDescription = txtDescription.getText().trim();

            // Validation
            if (newNom.isEmpty() || newDescription.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Tous les champs doivent être remplis.");
                return;
            }
            if (newNom.length() < 3) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom doit contenir au moins 3 caractères.");
                return;
            }

            // Update category fields
            categorie.setNom(newNom);
            categorie.setDescription(newDescription);

            try {
                // Handle logo update if new image selected
                if (newLogoPath[0] != null) {
                    File originalFile = new File(newLogoPath[0]);
                    String extension = newLogoPath[0].substring(newLogoPath[0].lastIndexOf('.') + 1);
                    String uniqueFileName = java.util.UUID.randomUUID().toString() + "." + extension;

                    String baseDir = System.getProperty("user.dir");
                    Path destinationPath = Paths.get(baseDir, "../ProjetWebTrekSwap/public/uploads/logos", uniqueFileName).normalize();
                    Files.createDirectories(destinationPath.getParent());
                    Files.copy(originalFile.toPath(), destinationPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                    categorie.setLogo(uniqueFileName); // Save only the filename
                }

                // Save to DB
                ss.update(categorie);
                afficherCategories(); // Refresh list
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Catégorie mise à jour avec succès.");
                modificationStage.close();

            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la mise à jour : " + e.getMessage());
                e.printStackTrace();
            }
        });

        // Layout
        VBox vbox = new VBox(10,
                lblNom, txtNom,
                lblDescription, txtDescription,
                lblLogo, imageView,
                btnChoisirImage,
                btnEnregistrer
        );
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 450, 500);
        modificationStage.setScene(scene);
        modificationStage.show();
    }



    private boolean isOnlyDigits(String str) {
        return str.matches("\\d+");
    }



    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void supprimerCategorie(Categorie categorie) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cette catégorie ?");
        alert.setContentText("Cette action est irréversible.");

        ButtonType buttonTypeYes = new ButtonType("Oui");
        ButtonType buttonTypeNo = new ButtonType("Non");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        alert.showAndWait().ifPresent(response -> {
            if (response == buttonTypeYes) {
                try {
                    ss.delete(categorie.getId());  // Supprimer la catégorie
                    afficherCategories();  // Rafraîchir la liste des catégories
                    showSuccessMessage(); // Afficher un message de succès
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showSuccessMessage() {
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Suppression réussie");
        successAlert.setHeaderText(null);
        successAlert.setContentText("La catégorie a été supprimée avec succès !");
        successAlert.showAndWait();
    }
    @FXML
    void showDash(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
        try {
            Parent root = loader.load();
            listcategorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void logOut(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
        try {
            Parent root = loader.load();
            listcategorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void Part(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichagePartenaire.fxml"));
        try {
            Parent root = loader.load();
            listcategorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}