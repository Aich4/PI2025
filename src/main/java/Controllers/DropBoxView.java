package Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DropBoxView {

    public void show() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dropbox_view.fxml")); // Le chemin vers ton FXML
        Stage stage = new Stage();
        stage.setTitle("Gestion des fichiers Dropbox");
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }


}
