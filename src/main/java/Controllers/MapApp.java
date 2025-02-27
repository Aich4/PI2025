package Controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MapApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        // Load local HTML file
        webEngine.load(getClass().getResource("/map.html").toExternalForm());

        // Example: Update location dynamically after 5 seconds
        webEngine.documentProperty().addListener((obs, oldDoc, newDoc) -> {
            if (newDoc != null) {
                webEngine.executeScript("updateMap(35.6895, 10.7074)"); // Example: Sousse, Tunisia
            }
        });

        primaryStage.setScene(new Scene(webView, 800, 600));
        primaryStage.setTitle("Leaflet Map in JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
