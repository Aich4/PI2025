package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
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

    @FXML
    private Slider ratingSlider;

    @FXML
    private FlowPane reviewsContainer;

    public void setDestinationData(Destination destination, List<Avis> avisList) {
        nameLabel.setText(destination.getNom_destination());
        descriptionLabel.setText(destination.getDecription());
        ratingLabel.setText("⭐ " + destination.getRate());
        this.destination = destination;
        // Load image if available
        if (destination.getImage_destination() != null) {
            Image image = new Image(destination.getImage_destination());
            destinationImage.setImage(image);
        }

        // Display reviews
        for (Avis avis : avisList) {
            // Create a label for each review
            Label reviewLabel = new Label(avis.getDescription_av() + " - ⭐ " + avis.getRating());
            // Add the review label to the UI (you may need a VBox or similar)
            // Assuming you have a VBox named reviewsContainer to hold the reviews
            reviewsContainer.getChildren().add(reviewLabel);
        }

        ratingSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            ratingLabel.setText("⭐ " + Math.round(newValue.doubleValue()));
        });
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
                // Get the rating from the slider
                int rating = (int) Math.round(ratingSlider.getValue());
                if (rating == 0) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a rating before submitting your review.");
                    alert.showAndWait();
                    return;
                }
                // Create a new Avis instance with the rating
                Avis avis = new Avis(destination.getId(), destination.getId(), description, rating);

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
    @FXML
    void showMap(ActionEvent event) {
        if (destination == null) {
            System.out.println("No destination selected.");
            return;
        }

        // Create WebView
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load local map.html file
        webEngine.load(getClass().getResource("/map.html").toExternalForm());

        // Inject destination coordinates dynamically
        webEngine.documentProperty().addListener((obs, oldDoc, newDoc) -> {
            if (newDoc != null) {
                double lat = destination.getLatitude();
                double lon = destination.getLongitude();
                webEngine.executeScript("updateMap(" + lat + ", " + lon + ");");
            }
        });

        // Create and show new stage
        Stage mapStage = new Stage();
        mapStage.setScene(new Scene(webView, 800, 600));
        mapStage.setTitle("Map - " + destination.getNom_destination());
        mapStage.show();
    }




}
