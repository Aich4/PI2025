package Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Activite;
import models.Destination;
import models.Avis;
import netscape.javascript.JSObject;
import services.ActiviteService;
import services.DestinationService;
import services.AvisService;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListDestinationBack {

    DestinationService destinationService = new DestinationService();
    AvisService avisService = new AvisService();

    @FXML
    private ListView<Destination> ListView;
    @FXML
    private TextField recherche;

    @FXML
    private ComboBox<String> triComboBox;
    @FXML
    private ComboBox<String> typeTri;

    @FXML
    private ListView<String> reviewsListView;

    private ObservableList<Destination> allDestinations;

    private void filtrerDestinations(String searchText) {
        if (allDestinations == null) return;

        String selectedAttribute = triComboBox.getValue(); // Get selected filter
        if (searchText == null || searchText.isEmpty()) {
            ListView.setItems(allDestinations);
            return;
        }

        ObservableList<Destination> filteredList = FXCollections.observableArrayList();

        for (Destination dest : allDestinations) {
            boolean match = false;

            switch (selectedAttribute) {
                case "Nom":
                    match = dest.getNom_destination() != null && dest.getNom_destination().toLowerCase().contains(searchText.toLowerCase());
                    break;
                case "Température":
                    match = String.valueOf(dest.getTemperature()).contains(searchText);
                    break;
                case "Rating":
                    match = String.valueOf(dest.getRate()).contains(searchText);
                    break;
            }

            if (match) {
                filteredList.add(dest);
            }
        }

        ListView.setItems(filteredList);
    }

    @FXML
    void initialize() {
        afficherDestinations();

        // Populate attribute ComboBox (triComboBox)
        triComboBox.getItems().addAll("Nom", "Température", "Rating");
        triComboBox.getSelectionModel().selectFirst(); // Select first option by default

        // Populate sorting order ComboBox (typeTri)
        typeTri.getItems().addAll("Asc", "Desc");
        typeTri.getSelectionModel().selectFirst(); // Default to Ascending

        // Update filtering when search text changes
        recherche.textProperty().addListener((observable, oldValue, newValue) -> filtrerDestinations(newValue));

        // Reapply filtering when criteria change
        triComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> trierDestinations());
        typeTri.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> trierDestinations());
    }

    private void trierDestinations() {
        if (allDestinations == null) return;

        String critere = triComboBox.getValue(); // Get selected attribute
        String ordre = typeTri.getValue(); // Get sorting order

        List<Destination> sortedList = new ArrayList<>(allDestinations);

        Comparator<Destination> comparator = null;

        switch (critere) {
            case "Nom":
                comparator = Comparator.comparing(Destination::getNom_destination);
                break;
            case "Température":
                comparator = Comparator.comparingDouble(Destination::getTemperature);
                break;
            case "Rating":
                comparator = Comparator.comparingDouble(Destination::getRate);
                break;
        }

        if (comparator != null) {
            if ("Desc".equals(ordre)) {
                comparator = comparator.reversed();
            }
            sortedList.sort(comparator);
        }

        ListView.setItems(FXCollections.observableArrayList(sortedList));
    }


    private void afficherDestinations() {
        try {
            List<Destination> destinations = destinationService.getAll();
            allDestinations = FXCollections.observableArrayList(destinations); // Initialize allDestinations
            ListView.setItems(allDestinations); // Set ListView items

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

                                Label detailsLabel = new Label(
                                        "📍 " + destination.getNom_destination() + "\n" +
                                                "📖 " + destination.getDecription() + "\n" +
                                                "🌡️ Temp: " + destination.getTemperature() + "°C\n" +
                                                "⭐ Rating: " + destination.getRate() + "\n" +
                                                "📌 Coordinates: (" + destination.getLatitude() + ", " + destination.getLongitude() + ")"
                                );
                                detailsLabel.setStyle("-fx-text-fill: #1A211B;");

                                if (destination.getImage_destination() != null && !destination.getImage_destination().isEmpty()) {
                                    String imagePath = destination.getImage_destination();
                                    imagePath = imagePath.replace("file:/", "").replace("%20", " ");

                                    Image image = new Image(new File(imagePath).toURI().toString(), 100, 75, true, true);
                                    imageView.setImage(image);
                                    vbox.getChildren().add(imageView);
                                }

                                vbox.getChildren().add(detailsLabel);

                                // Load and display reviews
                                try {
                                    List<Avis> reviews = avisService.getAvisByDestination(destination.getId());
                                    Label reviewsTitle = new Label("📝 Reviews:");
                                    reviewsTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #B05A36;");
                                    reviewsBox.getChildren().add(reviewsTitle);

                                    if (!reviews.isEmpty()) {
                                        for (Avis review : reviews) {
                                            HBox reviewHBox = new HBox(10);
                                            Label reviewLabel = new Label("• " + review.getDescription_av());
                                            reviewLabel.setWrapText(true);
                                            reviewLabel.setMaxWidth(400);

                                            Button deleteReviewBtn = new Button("❌");
                                            deleteReviewBtn.setStyle("-fx-background-color: transparent;");

                                            deleteReviewBtn.setOnAction(e -> {
                                                try {
                                                    avisService.delete(review.getId());
                                                    afficherDestinations();
                                                } catch (Exception ex) {
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
                                    Label errorLabel = new Label("Error loading reviews");
                                    errorLabel.setStyle("-fx-text-fill: red;");
                                    reviewsBox.getChildren().add(errorLabel);
                                    vbox.getChildren().add(reviewsBox);
                                }

                                deleteButton.setOnAction(event -> handleDelete(destination));
                                updateButton.setOnAction(event -> handleUpdate(destination));
                                showActivitiesButton.setOnAction(event -> handleShowActivities(destination));

                                hbox.getChildren().addAll(deleteButton, updateButton, showActivitiesButton);
                                vbox.getChildren().add(hbox);

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
                }else {
                    showErrorAlert("Update Failed", "Failed to update destination.");
                }

            }
        });
    }
    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
    @FXML
    void openMapToAddDestination(ActionEvent event) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);

        // Load the actual map.html directly instead of loading a temporary page first
        webEngine.load(getClass().getResource("/map.html").toExternalForm());

        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");

                // Check if callback is already set
                if (window.getMember("javafxCallback") == null || !(window.getMember("javafxCallback") instanceof JavaBridge)) {
                    JavaBridge javaBridge = new JavaBridge();
                    window.setMember("javafxCallback", javaBridge);
                    System.out.println("JavaFX Callback injected successfully!");
                }
            }
        });

        Stage mapStage = new Stage();
        mapStage.setScene(new Scene(webView, 800, 600));
        mapStage.setTitle("Select Destination");
        mapStage.show();
    }


    // Java bridge class to handle click events from the map
    public class JavaBridge {
        DestinationService ds = new DestinationService();

        public void onMapClick(String placeName, double latitude, double longitude) {
            System.out.println("Callback executed with: " + placeName + " (" + latitude + ", " + longitude + ")");

            Platform.runLater(() -> {
                // Show an input dialog to get the description
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Destination");
                dialog.setHeaderText("Enter a description for " + placeName);
                dialog.setContentText("Description:");

                dialog.showAndWait().ifPresent(description -> {
                    // Save to database
                    Destination newDestination = new Destination();
                    newDestination.setNom_destination(placeName);
                    newDestination.setDecription(description);
                    newDestination.setLatitude(latitude);
                    newDestination.setLongitude(longitude);
                    newDestination.setTemperature(25.0); // Default value
                    newDestination.setRate(5.0); // Default rating
                    newDestination.setImage_destination(""); // No image

                    try {
                        ds.create(newDestination);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }

        public void addReview(String review, int rating) {
            String displayText = review + " - ⭐ " + rating;
            reviewsListView.getItems().add(displayText);
        }
    }

}
