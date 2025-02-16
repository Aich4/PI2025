package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Destination;

public class DestinationCard {

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView destinationImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ratingLabel;

    public void setDestinationData(Destination destination) {
        nameLabel.setText(destination.getNom_destination());
        descriptionLabel.setText(destination.getDecription());
        ratingLabel.setText("⭐ " + destination.getRate());

        // Load image if available
        if (destination.getImage_destination() != null) {
            Image image = new Image(destination.getImage_destination());
            destinationImage.setImage(image);
        }

    }

}
