package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import services.GeminiClient;

import java.io.IOException;

public class MainController {
    @FXML private TextArea inputField;
    @FXML private TextArea outputField;
    @FXML private Button generateButton;

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
}
