package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import models.Activite;
import models.Avis;
import models.Destination;
import services.ActiviteService;
import services.AvisService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class DestinationCard {
    ActiviteService activiteService = new ActiviteService();
    private Destination destination;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView destinationImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ratingLabel;


    public void setDestinationData(Destination destination) {
        nameLabel.setText(destination.getNom_destination());
        descriptionLabel.setText(destination.getDecription());
        ratingLabel.setText("⭐ " + destination.getRate());
        this.destination = destination;
        // Load image if available
        if (destination.getImage_destination() != null) {
            Image image = new Image(destination.getImage_destination());
            destinationImage.setImage(image);
        }

    }

    @FXML
    private void handleAvis() {
        if (destination != null) {
            // Prompt for avis description
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add a Review");
            dialog.setHeaderText("Enter your review for " + destination.getNom_destination());
            dialog.setContentText("Review:");

            // Show the dialog and wait for the user response
            Optional<String> result = dialog.showAndWait();

            result.ifPresent(description -> {
                // Create a new Avis instance
                Avis avis = new Avis(destination.getId(), description);

                // Save the avis using AvisService
                try {
                    AvisService avisService = new AvisService();
                    avisService.create(avis);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Your review has been added successfully.");
                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to add your review.");
                    alert.showAndWait();
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("No destination selected.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleShowActivities() {
        if (destination != null) {
            System.out.println("Showing activities for: " + destination.getNom_destination());

            try {
                // Fetch activities from the service
                List<Activite> activities = activiteService.getActivitiesByDestination(destination.getId());

                // Create a FlowPane to display activities
                FlowPane activitiesFlow = new FlowPane();
                activitiesFlow.setHgap(15);
                activitiesFlow.setVgap(10);
                activitiesFlow.setStyle("-fx-padding: 10;");

                // Format date nicely
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

                // Loop through activities and create display cards
                for (Activite activity : activities) {
                    VBox activityBox = new VBox(5);
                    activityBox.setStyle("-fx-border-color: #CCCCCC; -fx-border-radius: 5; -fx-padding: 10; -fx-background-color: #F9F9F9;");

                    activityBox.getChildren().add(new Label("Activity: " + activity.getNom_activite()));
                    activityBox.getChildren().add(new Label("Date: " + activity.getDate().toString()));
                    activityBox.getChildren().add(new Label("Hour: " + activity.getHeure()));
                    activityBox.getChildren().add(new Label("Status: " + activity.getStatut()));

                    activitiesFlow.getChildren().add(activityBox);
                }

                // Create a ScrollPane to handle many activities
                ScrollPane scrollPane = new ScrollPane(activitiesFlow);
                scrollPane.setFitToWidth(true);
                scrollPane.setPrefHeight(300); // Adjust height as needed

                // Create a dialog to show activities
                Alert activitiesAlert = new Alert(Alert.AlertType.INFORMATION);
                activitiesAlert.setTitle("Activities for " + destination.getNom_destination());
                activitiesAlert.setHeaderText(null);
                activitiesAlert.getDialogPane().setContent(scrollPane);

                // Display dialog
                activitiesAlert.showAndWait();

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }




}
