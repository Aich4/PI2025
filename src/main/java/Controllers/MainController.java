package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import services.GeminiClient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Reclamation;
import services.ReclamationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import java.io.IOException;

public class MainController {
    @FXML private TextArea inputField;
    @FXML private TextArea outputField;
    @FXML private Button generateButton;
    @FXML private Button retourReclamation;

    private final GeminiClient geminiClient = new GeminiClient();

    @FXML
    public void initialize() {
        generateButton.setOnAction(event -> generateResponse());
    }

    private void generateResponse() {
        String prompt = inputField.getText();
        if (prompt.isEmpty()) {
            outputField.setText("Please enter a prompt.");
            return;
        }

        try {
            String response = geminiClient.generateText(prompt);
            outputField.setText(response);
        } catch (IOException e) {
            outputField.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    void retourReclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/test.fxml"));
            Parent root = loader.load();
            Scene scene = ((Button) event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de la page: " + e.getMessage());
        }
    }

}
