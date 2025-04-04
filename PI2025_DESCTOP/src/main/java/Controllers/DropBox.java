package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.DropboxUploader;
import models.DropboxLister;
import models.DropboxDownloader;
import models.DropboxDeleter;
import models.DropboxSharer;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DropBox {

    @FXML
    private Button uploadButton;
    @FXML
    private Button listButton;
    @FXML
    private Button downloadButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button shareButton;
    @FXML
    private Button Return;

    private DbxClientV2 client;

    // Initialize Dropbox client with the access token (replace "your-access-token" with actual token)
    public DropBox() {
        String accessToken = "sl.u.AFmaQOh4b0BN_SBwL14m7az6eY_OvRTOAvl7VofQN44n6oFCRMjwmGmOqO5gXR2XDvT80Oo3q5f1mctE3CUx_Giy1D1W4OqCXDANgz5-PJsUcUbjYqybMZFfQx51AfWzKaQXaX92QrILDP6cgJ5c1dhVvlv9y-KtIlwX-f19ftPw4-7erCV3SssqSU8NEzlpgEGhHlEEMProKayoD8QCsK6AQj0Ax9CJpQBQACP3OQFSZV_AGvm6i2zPQFo_A804vknthYVqUseQjlwRULuKORGml5jPls5JLPsF9NOxsyzVKvs5Hd41Lwb4gwYz5Y5nED_tQHg-HNlkVaAHd1vWz5y03RV1kFR02K5CDCHq1XJAlxjEXRrWIJvpf_i2Px8EMuGEhn4IYy_m3NS5dDeXRvt6TJS4LaUACEjEQKhlykApemMVO3WZVlzqGot9gpch2bA472nB4P4BoFcyBIPy_ONNv0-Hl3wTQ3LCVW1-P8Mp1WC2R59PyoCgwzljGXv0peT3Sth6KgBvO9djz7RPWw_aTS_w3r9HuUswJdkbX8D2T0LBVCMS2e1IdcL1oUW99i8EUeSFM7LFpbFAvQ2hTXdB4oX3NTLcy3DgIytUNDdUN5v8zyfPVJ_Sg3erYpRkrAj3Ny9nX8uOtzkLQ9dvguKXYZtHCHCsvVT-i3Dt9RP2NkLdIzeVis2TP_NMMGDet5Q3H-X0rw4m8XelcOXY_DM16Hwo3WtZ2oNQoZgxvkLn-lYW3XB5H2N5zbksSBFYjR3oBECxARFBbgOQ-FLqojAoGDlmduzJUzHb5kMQXYF_FYqOb9on2Tab2eut01Z_TdANzj4bZzUVMGE9ry4tyPVBTcz6WqNTbcaTjUAMO6W0xdSL3NmBTtqcdLb5Ys1ylvq7B-lB3Tx_CeMeAkVp7Blix6BznnNT-CpiPt5DQZBZMqy0Mp1Vh_JGFB0msRYLQV9SZuIN5PibwOqApadXGklOmSnsiLHal7JXxgpNaLxPBnBDP1CnkkZ2ndIgOiJGbgo7RKd3KvHbjPLxVqki5fxeR5qJrDPTyMJsi-jg58EdAzchNbfrO3WW0AJqvLRrMCHE8zLu6NjT0nm-T_OlcF2RWLa6wan4eDJ0byghcqLLCFtlYgcr5VDSAf_aBMD6RTZ9xedPjXixXo2lPtVvqJMVzANTGlpNnoYK5kUdEFwJ1vXmVlwSf4ik5R0lvC-svoOkfkDYo272mKB-e2eieJ-ZZLexKmDVa06-umdQdux0w8z89vPEXzfKf-ILkkTDY5I"; // Use OAuth2 token here
        DbxRequestConfig config = DbxRequestConfig.newBuilder("Ghalia").build();
        client = new DbxClientV2(config, accessToken);
    }

    @FXML
    void goToListPart(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichagePartenaire.fxml"));
        try {
            Parent root = loader.load();
            Return.getScene().setRoot(root);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Impossible de charger la page affichagePartenaire.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleUploadButton() {
        Stage stage = (Stage) uploadButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers", "*.*"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            DropboxUploader.uploadFile(file.getAbsolutePath(), "/" + file.getName());
            showAlert("Succès", "Le fichier a été téléchargé avec succès.");
        }
    }

    @FXML
    private void handleListButton() {
        DropboxLister.listFiles();
    }

    @FXML
    private ListView<String> fileListView; // Assuming you added this in the FXML

    @FXML
    private void handleDownloadButton(ActionEvent event) {
        try {
            // Get the list of files from Dropbox
            ListFolderResult result = client.files().listFolder("");
            List<Metadata> entries = result.getEntries();

            // Populate the ListView with the file names from Dropbox
            ObservableList<String> fileNames = FXCollections.observableArrayList();
            for (Metadata metadata : entries) {
                fileNames.add(metadata.getName());
            }
            fileListView.setItems(fileNames); // Set the list of file names in the ListView

            // Allow the user to select a file from the ListView
            fileListView.setOnMouseClicked(e -> {
                String selectedFile = fileListView.getSelectionModel().getSelectedItem();
                if (selectedFile != null) {
                    // Let the user choose a save location
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Files", "*.*"));
                    File file = fileChooser.showSaveDialog(null);

                    if (file != null) {
                        // Download the selected file from Dropbox
                        String dropboxPath = "/" + selectedFile;
                        DropboxDownloader.downloadFile(dropboxPath, file.getAbsolutePath());

                        // Show success alert
                        showAlert("Succès", "Le fichier a été téléchargé avec succès !");
                    }
                }
            });
        } catch (DbxException e) {
            showAlert("Erreur", "Une erreur s'est produite lors de la récupération des fichiers.");
        }
    }

    @FXML
    private void handleDeleteButton() {
        DropboxDeleter.deleteFile("/exemple.txt");
    }

    @FXML
    private void handleShareButton() {
        DropboxSharer.shareFile("/exemple.txt");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
