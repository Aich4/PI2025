package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import models.Partenaire;
import services.PartenaireService;

import java.util.List;

public class ListPartenaire {

    PartenaireService ss;

    public ListPartenaire() {
        this.ss = new PartenaireService();
    }

    @FXML
    private ListView<Partenaire> listepartenaire;

    @FXML
    void initialize() {
        afficherPartenaire();
    }

    private void afficherPartenaire() {
        try {
            List<Partenaire> partenaires = ss.getAll();
            listepartenaire.getItems().setAll(partenaires);

            // Personnaliser l'affichage des éléments dans la ListView
            listepartenaire.setCellFactory(new Callback<>() {
                @Override
                public ListCell<Partenaire> call(ListView<Partenaire> param) {
                    return new ListCell<>() {
                        @Override
                        protected void updateItem(Partenaire partenaire, boolean empty) {
                            super.updateItem(partenaire, empty);

                            if (empty || partenaire == null) {
                                setText(null);
                                setGraphic(null);
                            } else {
                                String categorieName = ss.getCategorieNameById(partenaire.getId_categorie());
                                // Affichage des informations du partenaire
                                String partenaireInfo = "Nom: " + partenaire.getNom() + "\n"
                                        + "Email: " + partenaire.getEmail() + "\n"
                                        + "Adresse: " + partenaire.getAdresse() + "\n"
                                        + "Date: " + partenaire.getDate_ajout() + "\n"
                                        + "Description: " + partenaire.getDescription() + "\n"
                                        + "Catégorie: " + (categorieName != null ? categorieName : "Inconnue");

                              /*  // Ajouter l'information de catégorie (si nécessaire)
                                if (partenaire.getCategorie() != null) {
                                    partenaireInfo += "Catégorie: " + partenaire.getCategorie().getNom();
                                }*/

                                setText(partenaireInfo);
                                setGraphic(null); // Aucune image à afficher, donc on met graphic à null
                            }
                        }
                    };
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
