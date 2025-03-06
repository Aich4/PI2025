package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Abonnement;
import services.Payment;
import services.ServicePDF;
import services.ServicePack;

import java.io.File;

import static services.QRCodeGenerator.createQR;


public class PackCard {
    Abonnement Abonn = new Abonnement();
    private ServicePack servicePack = new ServicePack();
    @FXML
    private Label date1;

    @FXML
    private Label date2;

    @FXML
    private Label nom;

    @FXML
    private Label statut;

    @FXML
    private ImageView qrCodeImageView;


    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




    @FXML
    void Choisir(ActionEvent event) {
        try {
            // Retrieve the ID from Abonnement
            int idPack = Abonn.getId_Pack();

            // Fetch the price of the pack
            float prixPack = (float) servicePack.getPackPriceById(idPack);

            Payment paymentService = new Payment();
            long priceLong = (long) (prixPack * 0.32) * 100; // Convert to cents
            String checkoutUrl = paymentService.createCheckoutSession(priceLong);

            String data = checkoutUrl;

            try {
                // Path where the QR Code will be saved
                String path = "C:\\Users\\MSI\\Desktop\\integration user+aicha+dest\\src\\main\\resources\\qrcode.png";
                createQR(data, path, "UTF-8", null, 200, 200);

                // Display confirmation
                showInfo("QR Code généré", "Le QR Code a été généré avec succès et sauvegardé dans :\n" + path);

                // Load the saved QR code image into the ImageView
                File qrCodeFile = new File(path);
                if (qrCodeFile.exists()) {
                    qrCodeImageView.setImage(new Image(qrCodeFile.toURI().toString())); // Display in Scene Builder
                } else {
                    showAlert("Erreur", "Le fichier QR Code n'a pas pu être trouvé !");
                }
            } catch (Exception e) {
                showAlert("Erreur", "Une erreur s'est produite lors de la génération du QR Code.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            showAlert("Erreur", "Une erreur s'est produite lors de la récupération du prix du pack.");
            e.printStackTrace();
        }
    }




    public void setPackData(Abonnement A) {
        String packName = "Inconnu"; // Valeur par défaut si non trouvé
        try {
            packName = servicePack.getPackNameById(A.getId_Pack()); // Récupérer le nom du pack
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération du nom du pack: " + e.getMessage());
        }

        nom.setText( packName);
        date1.setText(A.getDate_Souscription().toString());
        date2.setText( A.getDate_Expiration().toString());
        statut.setText( A.getStatut());

        this.Abonn = A;
    }
}
