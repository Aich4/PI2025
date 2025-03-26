package Tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class MainFx extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Get screen dimensions
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

            // Calculate window size (80% of screen size but not smaller than minimum)
            double width = 1200;
            double height =650 ;

            // Load the initial scene (Login)
            FXMLLoader fxmlLoader = new FXMLLoader(MainFx.class.getResource("/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), width, height);

            // Configure the stage
            stage.setTitle("Connexion");
            stage.setResizable(true);
            stage.setMinWidth(1200);
            stage.setMinHeight(650);

            // Center the window on screen
            stage.setX((screenBounds.getWidth() - width) / 2);
            stage.setY((screenBounds.getHeight() - height) / 2);

            stage.setScene(scene);
            stage.show();

            // Create uploads directory if it doesn't exist
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get("uploads"));

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
