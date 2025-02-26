package Controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import models.Activite;
import models.Destination;
import models.Avis;
import services.ActiviteService;
import services.DestinationService;
import services.AvisService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListDestinationBack {

    DestinationService destinationService = new DestinationService();
    AvisService avisService = new AvisService();

    @FXML
    private ListView<Destination> ListView;

    @FXML
    void initialize() {
        afficherDestinations();
    }

    private void afficherDestinations() {
        try {
            List<Destination> destinations = destinationService.getAll();
            ListView.setItems(FXCollections.observableArrayList(destinations));

            ListView.setCellFactory(new Callback<>() {
                @Override
                public ListCell<Destination> call(ListView<Destination> param) {
                    return new ListCell<>() {
                        private final VBox vbox = new VBox(10);
                        private final HBox hbox = new HBox(10);
                        private final ImageView imageView = new ImageView();
                        private final Button deleteButton = new Button("Delete");
                        private final Button updateButton = new Button("Update");
                        private final Button showActivitiesButton = new Button("Show Activities");
                        private final VBox reviewsBox = new VBox(5);

                        @Override
                        protected void updateItem(Destination destination, boolean empty) {
                            super.updateItem(destination, empty);

                            if (empty || destination == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                vbox.getChildren().clear();
                                hbox.getChildren().clear();
                                reviewsBox.getChildren().clear();

                                // Display destination details
                                Label detailsLabel = new Label(
                                    "📍 " + destination.getNom_destination() + "\n" +
                                    "📖 " + destination.getDecription() + "\n" +
                                    "🌡️ Temp: " + destination.getTemperature() + "°C\n" +
                                    "⭐ Rating: " + destination.getRate() + "\n" +
                                    "📌 Coordinates: (" + destination.getLatitude() + ", " + destination.getLongitude() + ")"
                                );
                                detailsLabel.setStyle("-fx-text-fill: #1A211B;");

                                // Load destination image if available
                                if (destination.getImage_destination() != null && !destination.getImage_destination().isEmpty()) {
                                    String imagePath = destination.getImage_destination();
                                    imagePath = imagePath.replace("file:/", "");
                                    imagePath = imagePath.replace("%20", " ");

                                    Image image = new Image(new File(imagePath).toURI().toString(), 100, 75, true, true);
                                    imageView.setImage(image);
                                    vbox.getChildren().add(imageView);
                                }

                                vbox.getChildren().add(detailsLabel);

                                // Load and display reviews
                                try {
                                    System.out.println("Loading reviews for destination: " + destination.getNom_destination() + " (ID: " + destination.getId() + ")");
                                    List<Avis> reviews = avisService.getAvisByDestination(destination.getId());
                                    System.out.println("Retrieved " + reviews.size() + " reviews");
                                    
                                    // Always show the reviews section, even if empty
                                    Label reviewsTitle = new Label("📝 Reviews:");
                                    reviewsTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #B05A36;");
                                    reviewsBox.getChildren().add(reviewsTitle);

                                    if (!reviews.isEmpty()) {
                                        for (Avis review : reviews) {
                                            System.out.println("Adding review to UI: " + review.getDescription_av());
                                            HBox reviewHBox = new HBox(10);
                                            Label reviewLabel = new Label("• " + review.getDescription_av());
                                            reviewLabel.setWrapText(true); // Allow text wrapping
                                            reviewLabel.setMaxWidth(400); // Set maximum width
                                            
                                            Button deleteReviewBtn = new Button("❌");
                                            deleteReviewBtn.setStyle("-fx-background-color: transparent;");
                                            
                                            deleteReviewBtn.setOnAction(e -> {
                                                try {
                                                    System.out.println("Deleting review ID: " + review.getId());
                                                    avisService.delete(review.getId());
                                                    afficherDestinations(); // Refresh the list
                                                } catch (Exception ex) {
                                                    System.err.println("Error deleting review: " + ex.getMessage());
                                                    ex.printStackTrace();
                                                    
                                                    // Show error alert
                                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                                    alert.setTitle("Error");
                                                    alert.setHeaderText("Failed to delete review");
                                                    alert.setContentText("An error occurred while deleting the review.");
                                                    alert.showAndWait();
                                                }
                                            });

                                            reviewHBox.getChildren().addAll(reviewLabel, deleteReviewBtn);
                                            reviewsBox.getChildren().add(reviewHBox);
                                        }
                                    } else {
                                        Label noReviewsLabel = new Label("No reviews yet");
                                        noReviewsLabel.setStyle("-fx-text-fill: #666666; -fx-font-style: italic;");
                                        reviewsBox.getChildren().add(noReviewsLabel);
                                    }
                                    vbox.getChildren().add(reviewsBox);
                                } catch (Exception e) {
                                    System.err.println("Error loading reviews: " + e.getMessage());
                                    e.printStackTrace();
                                    
                                    // Show error in UI
                                    Label errorLabel = new Label("Error loading reviews");
                                    errorLabel.setStyle("-fx-text-fill: red;");
                                    reviewsBox.getChildren().add(errorLabel);
                                    vbox.getChildren().add(reviewsBox);
                                }

                                // Add buttons
                                deleteButton.setOnAction(event -> handleDelete(destination));
                                updateButton.setOnAction(event -> handleUpdate(destination));
                                showActivitiesButton.setOnAction(event -> handleShowActivities(destination));

                                hbox.getChildren().addAll(deleteButton, updateButton, showActivitiesButton);
                                vbox.getChildren().add(hbox);

                                // Style the container
                                vbox.setStyle("-fx-background-color: rgba(198, 185, 171, 0.9); -fx-padding: 10; -fx-spacing: 10;");
                                setGraphic(vbox);
                            }
                        }
                    };
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle delete button click
    private void handleDelete(Destination destination) {
        try {
            destinationService.delete(destination.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        afficherDestinations(); // Refresh the ListView after deletion
    }

    private void handleUpdate(Destination destination) {
        // Create a new dialog (VBox) for updating the destination details
        VBox updateForm = new VBox(10);

        // Create TextFields to display and edit the current data
        TextField nameField = new TextField(destination.getNom_destination());
        TextField descriptionField = new TextField(destination.getDecription());
        TextField temperatureField = new TextField(String.valueOf(destination.getTemperature()));
        TextField rateField = new TextField(String.valueOf(destination.getRate()));
        TextField latitudeField = new TextField(String.valueOf(destination.getLatitude()));
        TextField longitudeField = new TextField(String.valueOf(destination.getLongitude()));

        // Add TextFields to the VBox
        updateForm.getChildren().addAll(
                new javafx.scene.control.Label("Name: "), nameField,
                new javafx.scene.control.Label("Description: "), descriptionField,
                new javafx.scene.control.Label("Temperature (°C): "), temperatureField,
                new javafx.scene.control.Label("Rating: "), rateField,
                new javafx.scene.control.Label("Latitude: "), latitudeField,
                new javafx.scene.control.Label("Longitude: "), longitudeField
        );

        // Create an alert to show the update form
        Alert updateAlert = new Alert(Alert.AlertType.CONFIRMATION);
        updateAlert.setTitle("Update Destination");
        updateAlert.getDialogPane().setContent(updateForm);

        // Set the behavior when the user presses the "OK" button
        updateAlert.setHeaderText("Update the details of destination: " + destination.getNom_destination());
        updateAlert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                // Update the destination object with the new values from the form
                destination.setNom_destination(nameField.getText());
                destination.setDecription(descriptionField.getText());
                destination.setTemperature(Double.parseDouble(temperatureField.getText()));
                destination.setRate(Double.parseDouble(rateField.getText()));
                destination.setLatitude(Double.parseDouble(latitudeField.getText()));
                destination.setLongitude(Double.parseDouble(longitudeField.getText()));

                // Save the updated destination
                boolean success = destinationService.updatee(destination);
                if (success) {
                    // If update was successful, refresh the ListView to reflect changes
                    afficherDestinations();
                } else {
                    // Handle update failure (optional)
                    System.out.println("Failed to update destination.");
                }
            }
        });
    }

    private void handleShowActivities(Destination destination) {
        // Create a new window or dialog to show activities related to this destination
        ActiviteService activiteService = new ActiviteService();
        List<Activite> activities = null;
        try {
            activities = activiteService.getActivitiesByDestination(destination.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Create a FlowPane to display the activities
        FlowPane activitiesFlow = new FlowPane();
        activitiesFlow.setHgap(15); // Horizontal gap between items
        activitiesFlow.setVgap(10); // Vertical gap between items
        activitiesFlow.setStyle("-fx-padding: 10;");

        // Loop through the activities and display them in the FlowPane
        for (Activite activite : activities) {
            VBox activityBox = new VBox(5);
            activityBox.setStyle("-fx-border-color: lightgray; -fx-padding: 10;");

            // Add labels with activity details
            activityBox.getChildren().add(new Label("Activity: " + activite.getNom_activite()));
            activityBox.getChildren().add(new Label("Date: " + activite.getDate().toString()));
            activityBox.getChildren().add(new Label("Hour: " + activite.getHeure()));
            activityBox.getChildren().add(new Label("Status: " + activite.getStatut()));

            activitiesFlow.getChildren().add(activityBox);
        }

        // Create a new dialog to display the activities
        Alert activitiesAlert = new Alert(Alert.AlertType.INFORMATION);
        activitiesAlert.setTitle("Activities for " + destination.getNom_destination());
        activitiesAlert.getDialogPane().setContent(activitiesFlow);
        activitiesAlert.showAndWait();
    }

    @FXML
    void ajout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GestionDestination.fxml"));
        try {
            Parent root = loader.load();
            ListView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void showActivite(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListActiviteBack.fxml"));
        try {
            Parent root = loader.load();
            ListView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showDash(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
        try {
            Parent root = loader.load();
            ListView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void logOut(ActionEvent event)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        try {
            Parent root = loader.load();
            ListView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
