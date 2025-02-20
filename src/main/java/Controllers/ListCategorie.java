package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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

import javafx.scene.layout.GridPane;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Optional;

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
                        HBox rightHBox = new HBox();
                        rightHBox.setHgrow(rightHBox, Priority.ALWAYS);
                        rightHBox.getChildren().addAll(btnModifier, btnSupprimer);

                        // Ajouter les deux HBox dans un seul
                        HBox fullHBox = new HBox(leftHBox, rightHBox);
                        fullHBox.setSpacing(10);

                        // Gérer l'action du bouton Modifier
                        btnModifier.setOnAction(event -> afficherDialogueModificationCategorie(categorie));

                        // Événement du bouton "Supprimer"
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


    private void afficherDialogueModificationCategorie(Categorie categorie) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Modifier Catégorie");
        dialog.setHeaderText("Modifiez les informations de la catégorie");

        TextField nomField = new TextField(categorie.getNom());
        TextField descriptionField = new TextField(categorie.getDescription());

        Label logoLabel = new Label(categorie.getLogo() != null ? categorie.getLogo() : "Aucune image sélectionnée");
        Button btnUpload = new Button("Choisir une image");

        // Sélection d'une image
        btnUpload.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Sélectionner une image");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );

            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                logoLabel.setText(selectedFile.getAbsolutePath()); // Afficher le chemin du fichier
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Nom:"), 0, 0);
        grid.add(nomField, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Logo:"), 0, 2);
        grid.add(btnUpload, 1, 2);
        grid.add(logoLabel, 2, 2);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        boolean saisieCorrecte;
        do {
            saisieCorrecte = true; // On suppose que la saisie est correcte au départ

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                String nom = nomField.getText().trim();
                String description = descriptionField.getText().trim();
                String logo = logoLabel.getText();

                // Vérification du nom
                if (nom.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom ne peut pas être vide.");
                    saisieCorrecte = false;
                } else if (nom.matches("\\d+")) { // Vérifie si le nom contient uniquement des chiffres
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom ne peut pas contenir uniquement des chiffres.");
                    saisieCorrecte = false;
                }

                // Vérification de la description
                if (description.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "La description ne peut pas être vide.");
                    saisieCorrecte = false;
                } else if (isOnlyDigits(description)) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "La description ne peut pas contenir uniquement des chiffres.");
                    saisieCorrecte = false;
                }

                // Vérification de l'image
                if (logo.equals("Aucune image sélectionnée") || logo.trim().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez sélectionner une image.");
                    saisieCorrecte = false;
                }

                // Si toutes les saisies sont valides, on met à jour la catégorie
                if (saisieCorrecte) {
                    categorie.setNom(nom);
                    categorie.setDescription(description);
                    categorie.setLogo(logo);

                    try {
                        ss.update(categorie);  // Mettre à jour la base de données
                        afficherCategories();   // Rafraîchir la liste après modification
                        showAlert(Alert.AlertType.INFORMATION, "Succès", "Catégorie mise à jour avec succès.");
                    } catch (Exception e) {
                        showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors de la mise à jour : " + e.getMessage());
                    }
                }
            } else {
                break; // L'utilisateur a annulé, on sort de la boucle
            }
        } while (!saisieCorrecte); // Tant que la saisie est incorrecte, on redemande les informations
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
    void showPart(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichagePartenaire.fxml"));
        try {
            Parent root = loader.load();
            listcategorie.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
