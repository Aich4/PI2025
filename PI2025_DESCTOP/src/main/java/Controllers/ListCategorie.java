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
                            String imagePath = categorie.getLogo();
                            imagePath = imagePath.replace("file:/", "").replace("%20", " ");
                            Image image = new Image(new File(imagePath).toURI().toString(), 50, 50, true, true);
                            imageView.setImage(image);
                        } else {
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

        // Champs modifiables
        TextField txtNom = new TextField(categorie.getNom());
        TextField txtDescription = new TextField(categorie.getDescription());
        TextField txtLogo = new TextField(categorie.getLogo());

        // Labels
        Label lblNom = new Label("Nom:");
        Label lblDescription = new Label("Description:");
        Label lblLogo = new Label("Logo:");

        // ImageView pour afficher l'image actuelle
        ImageView imageView = new ImageView();
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        // Charger l'image actuelle si elle existe
        if (categorie.getLogo() != null && !categorie.getLogo().isEmpty()) {
            try {
                Image image = new Image("file:" + categorie.getLogo());
                imageView.setImage(image);
            } catch (Exception e) {
                System.out.println("Erreur de chargement de l'image.");
            }
        }

        // Bouton pour sélectionner une image
        Button btnChoisirImage = new Button("Choisir Image");
        btnChoisirImage.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));
            File file = fileChooser.showOpenDialog(modificationStage);

            if (file != null) {
                txtLogo.setText(file.getAbsolutePath()); // Stocker le chemin de l'image
                imageView.setImage(new Image(file.toURI().toString())); // Mettre à jour l'aperçu
            }
        });

        // Bouton pour enregistrer les modifications
        Button btnEnregistrer = new Button("Sauvegarder");

        btnEnregistrer.setOnAction(event -> {
            String newNom = txtNom.getText().trim();
            String newDescription = txtDescription.getText().trim();
            String newLogo = txtLogo.getText().trim(); // Nouveau chemin de l’image

            // Vérifications des saisies
            if (newNom.isEmpty() || newDescription.isEmpty() || newLogo.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Tous les champs doivent être remplis.");
                return;
            }

            // Vérification spécifique (Exemple : Nom doit contenir au moins 3 caractères)
            if (newNom.length() < 3) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom doit contenir au moins 3 caractères.");
                return;
            }

            // Mise à jour de l'objet catégorie
            categorie.setNom(newNom);
            categorie.setDescription(newDescription);
            categorie.setLogo(newLogo); // Mettre à jour le chemin de l’image

            try {
                ss.update(categorie); // Mettre à jour la base de données
                afficherCategories(); // Rafraîchir la liste principale
                showAlert(Alert.AlertType.INFORMATION, "Succès", "Catégorie mise à jour avec succès.");
                modificationStage.close(); // Fermer la fenêtre après enregistrement
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la mise à jour : " + e.getMessage());
            }
        });

        // Mise en page
        VBox vbox = new VBox(10,
                lblNom, txtNom,
                lblDescription, txtDescription,
                lblLogo, txtLogo,
                imageView, btnChoisirImage, // Aperçu et bouton de sélection d'image
                btnEnregistrer
        );
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 400, 450);
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