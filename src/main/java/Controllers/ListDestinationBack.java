package Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import models.Destination;
import services.DestinationService;

import java.io.File;
import java.util.List;

public class ListDestinationBack {

    DestinationService destinationService = new DestinationService();

    @FXML
    private ListView<Destination> ListView;

    @FXML
    void initialize() {
        afficherDestinations();
    }

    private void afficherDestinations() {
        try {
            List<Destination> destinations = destinationService.getAll(); // Fetch destinations
            ListView.setItems(FXCollections.observableArrayList(destinations));

            // Customize the display of elements in the ListView
            ListView.setCellFactory(new Callback<>() {
                @Override
                public ListCell<Destination> call(ListView<Destination> param) {
                    return new ListCell<>() {
                        private final VBox vbox = new VBox();
                        private final HBox hbox = new HBox();
                        private final ImageView imageView = new ImageView();
                        private final Button deleteButton = new Button("Delete");
                        private final Button updateButton = new Button("Update");

                        @Override
                        protected void updateItem(Destination destination, boolean empty) {
                            super.updateItem(destination, empty);

                            if (empty || destination == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                // Clear previous content
                                vbox.getChildren().clear();
                                hbox.getChildren().clear();

                                // Display destination details
                                setText("📍 " + destination.getNom_destination() + "\n" +
                                        "📖 " + destination.getDecription() + "\n" +
                                        "🌡️ Temp: " + destination.getTemperature() + "°C\n" +
                                        "⭐ Rating: " + destination.getRate() + "\n" +
                                        "📌 Coordinates: (" + destination.getLatitude() + ", " + destination.getLongitude() + ")");

                                // Load destination image if available
                                if (destination.getImage_destination() != null && !destination.getImage_destination().isEmpty()) {
                                    String imagePath = destination.getImage_destination();
                                    imagePath = imagePath.replace("file:/", ""); // Fix path issues
                                    imagePath = imagePath.replace("%20", " "); // Handle spaces

                                    Image image = new Image(new File(imagePath).toURI().toString(), 100, 75, true, true);
                                    imageView.setImage(image);
                                    vbox.getChildren().add(imageView);
                                }

                                // Add buttons
                                deleteButton.setOnAction(event -> handleDelete(destination));
                                updateButton.setOnAction(event -> handleUpdate(destination));

                                hbox.getChildren().addAll(deleteButton, updateButton);
                                vbox.getChildren().add(hbox);

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
        // Implement deletion logic here
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
}
