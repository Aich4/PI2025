package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import models.Mission;
import services.MissionService;
import services.RecompenseService;

import java.util.List;
import java.util.Optional;

public class showmission {

    MissionService ms;

    public showmission() {
        this.ms = new MissionService();
    }

    @FXML
    private ListView<Mission> listmission;


    @FXML
    private void afficherMissions() {
        try {
            // Récupérer toutes les missions depuis la base de données
            List<Mission> missions = ms.getAll();
            ObservableList<Mission> observableMissions = FXCollections.observableArrayList(missions);

            // Associer les missions à la ListView
            listmission.setItems(observableMissions);

            // Personnalisation de l'affichage avec une CellFactory
            listmission.setCellFactory(param -> new ListCell<Mission>() {
                @Override
                protected void updateItem(Mission mission, boolean empty) {
                    super.updateItem(mission, empty);

                    if (empty || mission == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        // Construire la chaîne d'affichage avec les attributs de Mission
                        setText("Description: " + mission.getDescription() + "\n" +
                                "Points de récompense: " + mission.getPoints_recompense() + "\n" +
                                "Statut: " + mission.getStatut() + "\n" +
                                "ID Récompense: " + mission.getIdRec());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @FXML
    public void initialize() {
        afficherMissions();
    }



}
