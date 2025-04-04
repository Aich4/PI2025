package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import models.Abonnement;
import services.ServiceAbonnement;
import services.ServicePack; // Import ServicePack to use getIdByName
import java.sql.SQLException;
import java.util.List;

public class AfficheFront {

    @FXML
    private ListView<Abonnement> listview;
    private final ServiceAbonnement serviceAbonnement = new ServiceAbonnement();
    private final ServicePack servicePack = new ServicePack();  // Create instance of ServicePack

    @FXML
    public void initialize() {
        loadAbonnements();  // Charger automatiquement les abonnements au démarrage
    }

    @FXML
    void abonnements(ActionEvent event) {
        loadAbonnements(); // Rafraîchir les abonnements
    }

    private void loadAbonnements() {
        ObservableList<Abonnement> abonnementList = FXCollections.observableArrayList();

        try {
            List<Abonnement> abonnements = serviceAbonnement.getAll();

            if (abonnements == null || abonnements.isEmpty()) {
                System.out.println("⚠ Aucun abonnement trouvé !");
            } else {
                abonnementList.addAll(abonnements);
            }
        } catch (SQLException e) {
            System.err.println("⚠ Erreur lors de la récupération des abonnements : " + e.getMessage());
            e.printStackTrace();
        }

        listview.setItems(abonnementList);

        // Définir un affichage personnalisé pour chaque élément
        listview.setCellFactory(param -> new ListCell<Abonnement>() {
            @Override
            protected void updateItem(Abonnement item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Get the pack name by calling getIdByName function
                    String packName = servicePack.getPackNameById(item.getId_Pack());

                    // Set the text with the pack name instead of id_Pack
                    setText(
                            "  Pack: " + (packName != null ? packName : "Inconnu") +
                                    " | 📅 Début: " + item.getDate_Souscription() +
                                    " | 🔚 Fin: " + item.getDate_Expiration() +
                                    " | 🔄 Statut: " + item.getStatut());
                }
            }
        });
    }
}
