package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
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
import java.nio.file.StandardCopyOption;
import java.util.List;

import javafx.scene.layout.GridPane;
import services.CategorieService;
import models.Categorie;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.util.List;

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
                            try {
                                ss.delete(categorie.getId());  // Supprimer le partenaire de la base de données
                                afficherCategories();  // Rafraîchir la liste après suppression

                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Succès");
                                alert.setHeaderText(null);
                                alert.setContentText("La categorie a été supprimée avec succès !");
                                alert.showAndWait();  // Afficher la notification



                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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
        TextField nbrPartenaireField = new TextField(String.valueOf(categorie.getNbr_partenaire()));

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
                try {
                    // Copier l'image dans un dossier local
                    File destination = new File(selectedFile.getName());
                    Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    logoLabel.setText(destination.getAbsolutePath()); // Afficher le chemin
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
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
        grid.add(new Label("Nombre de partenaires:"), 0, 3);
        grid.add(nbrPartenaireField, 1, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                categorie.setNom(nomField.getText());
                categorie.setDescription(descriptionField.getText());
                categorie.setLogo(logoLabel.getText()); // Enregistrer le chemin du logo
                categorie.setNbr_partenaire(Integer.parseInt(nbrPartenaireField.getText()));

                try {
                    ss.update(categorie);  // Mettre à jour la base de données
                    afficherCategories();   // Rafraîchir la liste après modification
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        });

        dialog.showAndWait();
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
}
