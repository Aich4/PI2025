package Controllers;

import net.sourceforge.tess4j.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Mission;
import services.MissionService;
import services.OCRService;
import services.RecompenseService;
import services.WhatsAppSender;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class showmission{

    private MissionService missionService = new MissionService();
    private OCRService ocrService = new OCRService();

    MissionService ms;
    RecompenseService rs;

    public showmission() {
        this.ms = new MissionService();
        this.rs = new RecompenseService();
    }

    @FXML
    private ListView<Mission> listMission;

    private void afficherMissions() {
        try {
            // Récupérer toutes les missions depuis la base de données
            List<Mission> missions = ms.getAll();
            ObservableList<Mission> observableMissions = FXCollections.observableArrayList(missions);

            // Associer les missions à la ListView
            listMission.setItems(observableMissions);

            // Personnalisation de l'affichage avec une CellFactory
            listMission.setCellFactory(param -> new ListCell<Mission>() {
                @Override
                protected void updateItem(Mission mission, boolean empty) {
                    super.updateItem(mission, empty);

                    if (empty || mission == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // 🔹 Création des éléments graphiques
                        Label lblDescription = new Label("📌 Description : " + mission.getDescription());
                        Label lblPoints = new Label("🎯 Points : " + mission.getPoints_recompense());
                        Label lblStatut = new Label("📋 Statut : " + mission.getStatut());

                        // Récupérer la description de la récompense associée à la mission
                        String descriptionRecompense = null;
                        try {
                            descriptionRecompense = rs.getDescriptionById(mission.getIdRec());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        Label lblRecompense = new Label("🏆 Récompense : " + (descriptionRecompense != null ? descriptionRecompense : "Inconnue"));

                        // 🎨 Appliquer du style
                        lblDescription.setStyle("-fx-font-weight: bold;");
                        lblStatut.setStyle(mission.getStatut().equals("Validé") ? "-fx-text-fill: green;" : "-fx-text-fill: red;");

                        // 🔘 Création des boutons
                        Button btnModifier = new Button("✏ Modifier");
                        Button btnSupprimer = new Button("🗑 Supprimer");
                        Button btnValider = new Button("✅ Valider");

                        // 🛠 Actions des boutons
                        btnModifier.setOnAction(event -> modifierMission(mission, event));
                        btnSupprimer.setOnAction(event -> supprimerMission(mission));

                        // 📌 Désactiver le bouton "Valider" si déjà validé
                        if (mission.getStatut().equals("Validé")) {
                            btnValider.setDisable(true);
                        }

                        // 📌 Action pour valider la mission
                        btnValider.setOnAction(event -> ouvrirDialogueValidation(mission));


                        // 📌 Organisation des éléments
                        VBox detailsBox = new VBox(5, lblDescription, lblPoints, lblStatut, lblRecompense);
                        HBox buttonsBox = new HBox(10, btnModifier, btnSupprimer, btnValider);
                        VBox fullBox = new VBox(10, detailsBox, buttonsBox);

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
    private void ouvrirDialogueValidation(Mission mission) {
        OCRService ocrService = new OCRService(); // Instanciation du service OCR

        // Vérifier si la mission est déjà validée
        if ("Validé".equals(mission.getStatut())) {
            afficherAlerte("Information", "✅ Mission déjà validée !", Alert.AlertType.INFORMATION);
            return;
        }

        // Création du Stage du dialogue
        Stage validationStage = new Stage();
        validationStage.setTitle("Validation de la mission");

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        Label label = new Label("Veuillez télécharger une preuve pour valider la mission.");
        Button uploadButton = new Button("Téléverser une image");
        TextField filePathField = new TextField();
        filePathField.setEditable(false);
        Button validateButton = new Button("Valider la mission");
        validateButton.setDisable(true);

        vbox.getChildren().addAll(label, uploadButton, filePathField, validateButton);

        // Gestion du bouton de téléversement
        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Téléverser une preuve");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg"));

            File file = fileChooser.showOpenDialog(validationStage);
            if (file != null) {
                filePathField.setText(file.getAbsolutePath());
                validateButton.setDisable(false);
            }
        });

        // Gestion du bouton de validation
        validateButton.setOnAction(e -> {
            File file = new File(filePathField.getText());
            if (!file.exists()) {
                afficherAlerte("Erreur", "Le fichier n'existe pas, veuillez réessayer.", Alert.AlertType.ERROR);
                return;
            }

            try {
                // Extraire le texte avec OCR
                String texteExtrait = ocrService.extraireTexte(file).trim();
                if (texteExtrait.isEmpty()) {
                    afficherAlerte("Erreur", "Le texte extrait est vide, veuillez réessayer.", Alert.AlertType.ERROR);
                    return;
                }

                String texteAttendu = "Validation"; // Adapter selon besoin
                if (texteExtrait.contains(texteAttendu)) {
                    missionService.valider(mission);
                    afficherAlerte("Succès", "Mission validée avec succès !", Alert.AlertType.INFORMATION);
                    afficherMissions();
                    validationStage.close();

                    // Envoi du message WhatsApp
                    String messageText = "Bonjour, votre mission a été validée avec succès. Vous pouvez maintenant profiter de votre récompense.";
                    WhatsAppSender.envoyerMessageWhatsApp(messageText);
                } else {
                    afficherAlerte("Erreur", "Le texte extrait ne correspond pas à la validation attendue.", Alert.AlertType.ERROR);
                }
            } catch (TesseractException te) {
                afficherAlerte("Erreur OCR", "Erreur lors de l'extraction du texte : " + te.getMessage(), Alert.AlertType.ERROR);
            } catch (Exception ex) {
                afficherAlerte("Erreur", "Une erreur est survenue : " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        Scene scene = new Scene(vbox, 400, 200);
        validationStage.setScene(scene);
        validationStage.show();
    }

    // Méthode générique pour afficher des alertes
    private void afficherAlerte(String titre, String message, Alert.AlertType type) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.setTitle(titre);
        alert.show();
    }




    @FXML
public void initialize() {
    afficherMissions();
}

private void modifierMission(Mission mission, ActionEvent event) { // Ajouter ActionEvent event
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/css/UpdateMission.fxml"));
        Parent root = loader.load();

        // Accéder au contrôleur de la vue updateMission
        updateMission controller = loader.getController();

        // Passer la mission sélectionnée au contrôleur de UpdateMission
        controller.setMission(mission);

        // Obtenir la scène et la mettre à jour avec la nouvelle vue
        ((Button) event.getSource()).getScene().setRoot(root);
    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("Erreur de chargement de updateMission.fxml", e);
    }
}



// Méthode pour supprimer une mission
private void supprimerMission(Mission mission) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Supprimer la mission");
    alert.setContentText("Es-tu sûr de vouloir supprimer cette mission ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
            ms.delete(mission.getId()); // Suppression dans la base de données
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        afficherMissions(); // Rafraîchir la liste
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

