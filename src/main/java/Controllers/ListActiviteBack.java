package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Activite;
import services.ActiviteService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;

public class ListActiviteBack {
    ActiviteService activiteService = new ActiviteService();
    public ListActiviteBack() {
        this.activiteService = new ActiviteService();
    }

    @FXML
    private ListView<Activite> ListView;

    @FXML
    void ajout(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjoutActivite.fxml"));
        try {
            Parent root = loader.load();
            ListView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void showDestination(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListDestinationBack.fxml"));
        try {
            Parent root = loader.load();
            ListView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void initialize() {
        afficherActivite();
    }
    private void afficherActivite() {
        try {
            // Get all activities from the database
            List<Activite> activites = this.activiteService.getAll();

            // Create an ObservableList from the List of Activite objects
            ObservableList<Activite> activiteObservableList = FXCollections.observableArrayList(activites);

            // Set a custom cell factory to the ListView to display formatted information
            ListView.setCellFactory(param -> new ListCell<Activite>() {
                @Override
                protected void updateItem(Activite activite, boolean empty) {
                    super.updateItem(activite, empty);

                    if (empty || activite == null) {
                        setText(null); // Clear the cell if empty
                        setGraphic(null); // Clear the buttons if empty
                    } else {
                        // Create a container for displaying the activity details and buttons
                        VBox vbox = new VBox();

                        // Format and display the activity details
                        Label label = new Label(String.format("%s - %s - %s - %s",
                                activite.getNom_activite(),
                                activite.getDate(),
                                activite.getHeure(),
                                activite.getStatut()));
                        vbox.getChildren().add(label);

                        // Create Delete button
                        Button deleteButton = new Button("Delete");
                        deleteButton.setOnAction(e -> {
                            // Call the delete method
                            deleteActivite(activite);
                            activiteObservableList.remove(activite); // Remove from the list view
                        });

                        // Create Update button
                        Button updateButton = new Button("Update");
                        updateButton.setOnAction(e -> {
                            // Call the update method (You can open a dialog for updating)
                            updateActivite(activite);
                        });

                        // Add buttons to VBox
                        HBox buttonsBox = new HBox(10, deleteButton, updateButton);
                        vbox.getChildren().add(buttonsBox);

                        // Set the VBox with activity details and buttons as the cell graphic
                        setGraphic(vbox);
                    }
                }
            });

            // Set the ObservableList to the ListView
            ListView.setItems(activiteObservableList);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any errors, such as database connection issues
        }
    }
    private void deleteActivite(Activite activite) {
        try {
            activiteService.delete(activite.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        afficherActivite();
    }

    // Method for updating an Activite
    private void updateActivite(Activite activite) {
        // Create the update form
        VBox updateForm = new VBox(10);

// Create TextFields to display and edit the current data
        TextField nameField = new TextField(activite.getNom_activite());
        TextField dateField = new TextField(activite.getDate().toString()); // Assuming date is stored as java.sql.Date
        TextField timeField = new TextField(activite.getHeure());
        TextField statusField = new TextField(activite.getStatut());

// Add TextFields to the VBox
        updateForm.getChildren().addAll(
                new javafx.scene.control.Label("Activity Name: "), nameField,
                new javafx.scene.control.Label("Date: "), dateField,
                new javafx.scene.control.Label("Time: "), timeField,
                new javafx.scene.control.Label("Status: "), statusField
        );

// Create an alert to show the update form
        Alert updateAlert = new Alert(Alert.AlertType.CONFIRMATION);
        updateAlert.setTitle("Update Activity");
        updateAlert.getDialogPane().setContent(updateForm);

// Set the behavior when the user presses the "OK" button
        updateAlert.setHeaderText("Update the details of activity: " + activite.getNom_activite());
        updateAlert.showAndWait().ifPresent(response -> {
            if (response == javafx.scene.control.ButtonType.OK) {
                // Update the activite object with the new values from the form
                activite.setNom_activite(nameField.getText());
                activite.setDate(java.sql.Date.valueOf(dateField.getText())); // Convert the date string to java.sql.Date
                activite.setHeure(timeField.getText());
                activite.setStatut(statusField.getText());

                // Save the updated activite (Assuming you have a method for this in your service)
                boolean success = activiteService.updateActivite(activite);
                if (success) {
                    // If update was successful, refresh the ListView to reflect changes
                    afficherActivite();
                } else {
                    // Handle update failure (optional)
                    System.out.println("Failed to update activity.");
                }
            }
        });

    }
}
