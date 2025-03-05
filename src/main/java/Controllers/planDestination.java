package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class planDestination {

    @FXML
    private TextArea textArea;
    // Setter method for the text area content
    public void setTextArea(String text) {
        textArea.setText(text);
    }
    @FXML
    void goBack(ActionEvent event) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Wishlist.fxml"));
            try {
                Parent root = loader.load();
                textArea.getScene().setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

    }
    public static void openPlanDestinationPage(String geminiResponse, Stage currentStage) {
        try {
            // Load the FXML
            FXMLLoader loader = new FXMLLoader(planDestination.class.getResource("/planDestination.fxml"));
            Parent root = loader.load();

            // Get the controller and set the response
            planDestination controller = loader.getController();
            controller.setTextArea(geminiResponse);

            // Replace the current stage's scene
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
