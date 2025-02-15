package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import utils.MyDb;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.net.URL;
import java.util.ResourceBundle;


public class ShowPack {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idrecher;

    @FXML
    private ListView<String> listview;

    public void ShowPack() {
        System.out.println("Méthode ShowPack() exécutée !");

        ObservableList<String> packList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM pack";

        try (Connection conn = MyDb.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (conn == null) {
                System.out.println("⚠ Erreur : Connexion à la base de données échouée !");
                return;
            } else {
                System.out.println("✅ Connexion réussie !");
            }

            if (!rs.isBeforeFirst()) {
                System.out.println("⚠ Aucun pack trouvé dans la base de données !");
                return;
            }

            while (rs.next()) {
                String nomPack = rs.getString("nom_Pack");
                String description = rs.getString("description");
                float prix = rs.getFloat("prix");
                int duree = rs.getInt("duree");
                String avantages = rs.getString("avantages");
                String statut = rs.getString("statut");

                String packInfo = "Nom: " + nomPack + " | Description: " + description +
                        " | Prix: " + prix + " | Durée: " + duree +
                        " | Avantages: " + avantages + " | Statut: " + statut;

                packList.add(packInfo);
                System.out.println("📦 Pack trouvé : " + nomPack);
            }

            if (listview == null) {
                System.out.println("⚠ Erreur : listview est null !");
                return;
            }

            listview.setItems(packList);
            listview.refresh();
            System.out.println("✅ Liste mise à jour avec " + packList.size() + " packs.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void actualiser(ActionEvent event) {
        ShowPack();
    }
    @FXML
    public void initialize() {
        ShowPack();
    }
    @FXML
    void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddPack.fxml"));
            Parent root = loader.load();
            listview.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}



