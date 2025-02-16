package services;

import models.Reponse;
import Utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReponseService implements Crud<Reponse> {
    private Connection conn;

    public ReponseService() {
        this.conn = MyDb.getInstance().getConn();
    }

    @Override
    public boolean create(Reponse obj) throws Exception {
        String sql = "INSERT INTO reponse (id_rec, date_rep, contenu_rep) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, obj.getId_rec());
            stmt.setDate(2, new Date(obj.getDate_rep().getTime()));
            stmt.setString(3, obj.getContenu_rep());

            int res = stmt.executeUpdate();
            if (res > 0) {
                System.out.println("Réponse ajoutée avec succès !");
                return true;
            } else {
                System.out.println("Aucune réponse ajoutée.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void update(Reponse obj) throws Exception {
        String sql = "UPDATE reponse SET id_rec = ?, date_rep = ?, contenu_rep = ? WHERE id_rep = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, obj.getId_rec());
            stmt.setDate(2, new Date(obj.getDate_rep().getTime()));
            stmt.setString(3, obj.getContenu_rep());
            stmt.setInt(4, obj.getId_rep());

            stmt.executeUpdate();
            System.out.println("Réponse mise à jour avec succès !");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id_rep) throws Exception {
        String sql = "DELETE FROM reponse WHERE id_rep = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_rep);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Réponse supprimée avec succès !");
            } else {
                System.out.println("Aucune réponse trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Reponse> getAll() throws Exception {
        String sql = "SELECT * FROM reponse";
        List<Reponse> reponses = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Reponse obj = new Reponse(
                        rs.getInt("id_rep"),
                        rs.getInt("id_rec"),
                        rs.getDate("date_rep"),
                        rs.getString("contenu_rep")
                );
                reponses.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return reponses;
    }

    @Override
    public Reponse getById(int id_rep) throws Exception {
        String sql = "SELECT * FROM reponse WHERE id_rep = ?";
        Reponse obj = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_rep);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                obj = new Reponse(
                        rs.getInt("id_rep"),
                        rs.getInt("id_rec"),
                        rs.getDate("date_rep"),
                        rs.getString("contenu_rep")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
}
