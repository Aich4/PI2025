package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import services.WeatherService;
import services.WishlistService;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static services.WeatherService.getWeatherIcon;

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
    private VBox weatherContainer;

    @FXML
    private Button weatherButton;

    @FXML
    private ImageView wishlistIcon;

    private boolean isInWishlist = false; // Track if the destination is in the wishlist
    private static final String HEART_EMPTY_PATH = "/heart_empty.png";
    private static final String HEART_FILLED_PATH = "/heart_filled.png";

    @FXML
    public void initialize() {
        // Load the default heart image (empty)
        wishlistIcon.setImage(new Image(getClass().getResource(HEART_EMPTY_PATH).toExternalForm()));
        wishlistIcon.setOnMouseClicked(event -> toggleWishlist());
    }
    private void toggleWishlist() {
        isInWishlist = !isInWishlist;

        if (isInWishlist) {
            // Set the heart to filled when added to the wishlist
            wishlistIcon.setImage(new Image(getClass().getResource(HEART_FILLED_PATH).toExternalForm()));
            addToWishlist(destination);
        } else {
            // Set the heart to empty when removed from the wishlist
            wishlistIcon.setImage(new Image(getClass().getResource(HEART_EMPTY_PATH).toExternalForm()));
            removeFromWishlist(destination);
        }
    }

    private void addToWishlist(Destination destination) {
        WishlistService.getInstance().add(destination);
        System.out.println(destination.getNom_destination() + " added to wishlist.");
    }

    private void removeFromWishlist(Destination destination) {
        WishlistService.getInstance().remove(destination);
        System.out.println(destination.getNom_destination() + " removed from wishlist.");
    }

    public void setDestinationData(Destination destination) {
        nameLabel.setText(destination.getNom_destination());
        descriptionLabel.setText(destination.getDecription());
        ratingLabel.setText("⭐ " + destination.getRate());
        this.destination = destination;
        weatherContainer.setVisible(false);

        if (destination.getImage_destination() != null && !destination.getImage_destination().isEmpty()) {
            try {
                String basePath = System.getProperty("user.dir");
                File imageFile = new File("../ProjetWebTrekSwap/public" + destination.getImage_destination());
                if (imageFile.exists()) {
                    Image image = new Image(imageFile.toURI().toString());
                    destinationImage.setImage(image);
                } else {
                    destinationImage.setImage(null);
                }
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
                destinationImage.setImage(null);
            }
        } else {
            destinationImage.setImage(null);
        }


    }
    @FXML
    void showWeather(ActionEvent event) {
        if (weatherContainer.isVisible()) {
            weatherContainer.setVisible(false);
            weatherContainer.setManaged(false);
        } else {
            if (destination == null) {
                weatherContainer.getChildren().clear();
                weatherContainer.getChildren().add(new Label("No destination selected."));
                return;
            }

            weatherContainer.getChildren().clear();

            // Fetch weather data
            String forecast = WeatherService.getWeeklyForecast(destination.getLatitude(), destination.getLongitude());

            // Create a container for weather display
            VBox weatherBox = new VBox(10);
            weatherBox.setPadding(new Insets(10));
            weatherBox.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 8px; -fx-padding: 10px; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0.5, 0, 2);");

            // Split forecast into lines and add styled labels
            String[] forecastLines = forecast.split("\n");
            for (String line : forecastLines) {
                Label weatherLabel = new Label(getWeatherIcon(line) + " " + line);
                weatherLabel.setStyle("-fx-font-size: 14px; -fx-padding: 5px; -fx-text-fill: #333;");
                weatherBox.getChildren().add(weatherLabel);
            }

            weatherContainer.getChildren().add(weatherBox);
            weatherContainer.setVisible(true);
            weatherContainer.setManaged(true);
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
