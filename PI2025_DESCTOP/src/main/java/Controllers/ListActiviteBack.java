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
import java.time.LocalDate;
import java.util.List;

public class ListActiviteBack {
    ActiviteService activiteService ;
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

        // Create input fields with current values
        TextField nameField = new TextField(activite.getNom_activite());

        DatePicker datePicker = new DatePicker(activite.getDate().toLocalDate()); // Convert SQL date to LocalDate
        TextField timeField = new TextField(activite.getHeure());

        // Create ComboBox for status selection
        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("Planned", "Ongoing", "Completed"); // Example statuses
        statusComboBox.setValue(activite.getStatut());

        // Add input fields to the form
        updateForm.getChildren().addAll(
                new Label("Activity Name: "), nameField,
                new Label("Date: "), datePicker,
                new Label("Time (HH:mm): "), timeField,
                new Label("Status: "), statusComboBox
        );

        // Create an alert to show the update form
        Alert updateAlert = new Alert(Alert.AlertType.CONFIRMATION);
        updateAlert.setTitle("Update Activity");
        updateAlert.setHeaderText("Update the details of activity: " + activite.getNom_activite());
        updateAlert.getDialogPane().setContent(updateForm);

        // Show the alert and handle user input
        updateAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Validate inputs
                if (nameField.getText().isEmpty() || datePicker.getValue() == null ||
                        timeField.getText().isEmpty() || statusComboBox.getValue() == null) {

                    showAlert(Alert.AlertType.WARNING, "Warning", "All fields must be filled!");
                    return;
                }

                // Validate date (should not be in the past)
                LocalDate selectedDate = datePicker.getValue();
                if (selectedDate.isBefore(LocalDate.now())) {
                    showAlert(Alert.AlertType.WARNING, "Warning", "Date cannot be in the past!");
                    return;
                }

                // Validate time format (HH:mm)
                if (!timeField.getText().matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                    showAlert(Alert.AlertType.WARNING, "Warning", "Invalid time format! Use HH:mm.");
                    return;
                }

                // Update the activite object with new values
                activite.setNom_activite(nameField.getText());
                activite.setDate(java.sql.Date.valueOf(selectedDate)); // Convert LocalDate to SQL Date
                activite.setHeure(timeField.getText());
                activite.setStatut(statusComboBox.getValue());

                // Save the updated activite
                boolean success = activiteService.updateActivite(activite);
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Activity updated successfully!");
                    afficherActivite(); // Refresh list
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update activity.");
                }
            }
        });
    }

    // Utility function to show alerts
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void showDash(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard.fxml"));
        try {
            Parent root = loader.load();
            ListView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
