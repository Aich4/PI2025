package services;

import javafx.scene.control.Button;
import models.Reclamation;
import Utils.MyDb;
import java.util.concurrent.TimeUnit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class ReclamationService implements Crud<Reclamation> {
    private  Connection conn;

    public ReclamationService() {
        MyDb database = MyDb.getInstance();
        this.conn = database.getConn();
        if (this.conn == null) {
            System.err.println("Erreur : Connexion à la base de données non établie !");
        }

    }


    @Override
    public boolean create(Reclamation obj) throws Exception {
        // Vérification de la date avant l'insertion
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime());

        if (obj.getDate().after(now)) {
            throw new IllegalArgumentException("La date ne peut pas être dans le futur");
        }

        String sql = "INSERT INTO reclamation (description_rec, type_rec, date_rec, etat_rec) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getDescription());
            stmt.setString(2, obj.getType());
            stmt.setTimestamp(3, obj.getDate());
            stmt.setString(4, obj.getEtat());

            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("Ajout de la réclamation avec succès !");
                return true;
            } else {
                System.out.println("Aucune réclamation ajoutée.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void update(Reclamation obj) throws Exception {
        // Vérification de la date avant la mise à jour
        Timestamp now = new Timestamp(System.currentTimeMillis());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(now.getTime());
        if (obj.getDate().after(now)) {
            throw new IllegalArgumentException("La date ne peut pas être dans le futur");
        }

        String sql = "UPDATE reclamation SET description_rec = ?, type_rec = ?, date_rec = ?, etat_rec = ? WHERE id_rec = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, obj.getDescription());
            stmt.setString(2, obj.getType());
            stmt.setTimestamp(3, obj.getDate());
            stmt.setString(4, obj.getEtat());
            stmt.setInt(5, obj.getIdReclamation());

            stmt.executeUpdate();
            System.out.println("Réclamation mise à jour avec succès !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM reclamation WHERE id_rec = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Suppression de la réclamation réussie !");
            } else {
                System.out.println("Aucune réclamation trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Reclamation> getAll() throws Exception {
        String sql = "SELECT * FROM reclamation";
        List<Reclamation> reclamations = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reclamation obj = new Reclamation(
                        rs.getInt("id_rec"),
                        rs.getString("description_rec"),
                        rs.getString("type_rec"),
                        rs.getTimestamp("date_rec")
                );
                obj.setEtat(rs.getString("etat_rec"));
                reclamations.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reclamations;
    }

    @Override
    public Reclamation getById(int id_rec) throws Exception {
        String sql = "SELECT * FROM reclamation WHERE id_rec = ?";
        Reclamation obj = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_rec);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                obj = new Reclamation(
                        rs.getInt("id_rec"),
                        rs.getString("description_rec"),
                        rs.getString("type_rec"),
                        rs.getTimestamp("date_rec")
                );
                obj.setEtat(rs.getString("etat_rec"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @FXML
    void retourTest(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/test.fxml"));
            Parent root = loader.load();
            Scene scene = ((Button) event.getSource()).getScene();
            scene.setRoot(root);
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de la page Test: " + e.getMessage());
        }
    }
}
