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
            double width = Math.max(screenBounds.getWidth() * 0.8, 700);
            double height = Math.max(screenBounds.getHeight() * 0.8, 800);

            // Load the initial scene (Login)
            FXMLLoader fxmlLoader = new FXMLLoader(MainFx.class.getResource("/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), width, height);

            // Configure the stage
            stage.setTitle("Connexion");
            stage.setResizable(true);
            stage.setMinWidth(700);
            stage.setMinHeight(800);

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
