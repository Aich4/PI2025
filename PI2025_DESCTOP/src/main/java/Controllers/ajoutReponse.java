package Controllers;

import javafx.scene.Scene;
import models.Reclamation;
import services.ReponseService;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import java.sql.Timestamp;

import models.Reponse;

public class ajoutReponse {
    ReponseService reponseService ;
    public ajoutReponse() {
        reponseService = new ReponseService();
    }


    public void ajoutReponse(Reclamation rec) {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));

        // Title
        Label titleLabel = new Label("Ajouter une réponse à la réclamation");
        titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Display Reclamation Details
        Label detailsLabel = new Label("Réclamation: " + rec.getType() + "\nDescription: " + rec.getDescription());
        detailsLabel.setWrapText(true);

        // Response Text Area
        TextArea responseField = new TextArea();
        responseField.setPromptText("Écrire votre réponse ici...");
        responseField.setPrefRowCount(4);

        // Submit Button
        Button submitButton = new Button("Ajouter Réponse");
        submitButton.setOnAction(event -> {
            String responseText = responseField.getText().trim();
            if (responseText.isEmpty()) {
                showAlert("Erreur", "Le champ de réponse est vide !", Alert.AlertType.ERROR);
                return;
            }

            // Create Reponse Object
            Reponse newResponse = new Reponse(
                    rec.getIdReclamation(),
                    new Timestamp(System.currentTimeMillis()),
                    responseText
            );

            // Save Response using ReponseService
            ReponseService reponseService = new ReponseService();
            try {
                boolean success = reponseService.create(newResponse);
                if (success) {
                    // Mettre à jour l'état de la réclamation à "Résolue"
                    try {
                        rec.setEtat("Résolue");
                        new services.ReclamationService().update(rec);
                    } catch (Exception e) {
                        showAlert("Erreur", "La réponse a été ajoutée, mais l'état de la réclamation n'a pas pu être mis à jour: " + e.getMessage(), Alert.AlertType.WARNING);
                    }

                    showAlert("Succès", "Réponse ajoutée avec succès !", Alert.AlertType.INFORMATION);
                    stage.close();
                } else {
                    showAlert("Erreur", "La réponse n'a pas été ajoutée.", Alert.AlertType.ERROR);
                }

            } catch (Exception e) {
                showAlert("Erreur", "Impossible d'ajouter la réponse: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        });

        // Add Components to Layout
        layout.getChildren().addAll(titleLabel, detailsLabel, responseField, submitButton);
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Répondre à une Réclamation");
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.show();
    }

    // Utility method to show alerts
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
