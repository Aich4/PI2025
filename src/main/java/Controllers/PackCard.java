package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Destination;
import models.Pack;
import services.ActiviteService;

public class PackCard {
    ActiviteService activiteService = new ActiviteService();
    Pack pack = new Pack();

    @FXML
    private Label avantageField;

    @FXML
    private Label descriptionLabel;



    @FXML
    private Label dureeField;

    @FXML
    private Label nameLabel;

    @FXML
    private Label prixLabel;

    @FXML
    private Label statutField;


    public void setPackData(Pack pack) {
        nameLabel.setText("nom : "+pack.getNom_Pack());
        descriptionLabel.setText(pack.getDescription());
        prixLabel.setText("prix : " + pack.getPrix());
        dureeField.setText("duree : " + pack.getDuree());
        statutField.setText("statut: " + pack.getStatut());

        this.pack = pack;


    }
}
