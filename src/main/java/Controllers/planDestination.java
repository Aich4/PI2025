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
    public static void openPlanDestinationPage(String geminiResponse) {
        try {
            // Load the planDestination.fxml
            FXMLLoader loader = new FXMLLoader(planDestination.class.getResource("/planDestination.fxml"));

            // Load the Parent node
            Parent root = loader.load();

            // Get the controller of the loaded FXML
            planDestination controller = loader.getController();

            // Set the response in the text area
            controller.setTextArea(geminiResponse);

            // Get the Stage (or create a new one if you need a new window)
            Stage stage = new Stage();

            // Set the Scene with the loaded FXML content
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Show the stage (open the window)
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception if the FXML file couldn't be loaded
        }
    }

}
