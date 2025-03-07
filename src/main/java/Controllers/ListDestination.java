package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Destination;
import services.AvisService;
import services.DestinationService;
import models.Avis;

import java.io.IOException;
import java.util.List;


public class ListDestination {

    @FXML
    private GridPane gridContainer;

    DestinationService ds = new DestinationService();
    public ListDestination() {
        this.ds = new DestinationService();
    }

    @FXML
    void initialize() {
        System.out.println("Initialize method is running...");
        List<Destination> DestinationList = null;
        try {
            DestinationList = this.ds.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int column = 0, row = 0;
            for(Destination destination : DestinationList) {

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/DestinationCard.fxml"));
                    AnchorPane card = loader.load();

                    try {
                        AvisService avisService = new AvisService();
                        List<Avis> avisList = avisService.getAvisByDestination(destination.getId());
                        DestinationCard cardController = loader.getController();
                        cardController.setDestinationData(destination, avisList);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Failed to fetch reviews for destination ID: " + destination.getId());
                    }

                    gridContainer.add(card, column, row);
                    column++;
                    if (column == 3) { // 3 cards per row
                        column = 0;
                        row++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }


    }

}
