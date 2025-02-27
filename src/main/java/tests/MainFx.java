package Tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

import java.io.IOException;

public class MainFx extends Application {
    @Override

    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FrontOffice.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("GestionActivite");
        stage.setWidth(1920);
        stage.setHeight(1080);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
