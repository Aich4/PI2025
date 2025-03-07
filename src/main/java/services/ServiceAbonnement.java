package services;

import models.Abonnement;
import utils.MyDb;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAbonnement implements Iabonnement<Abonnement> {
    private Connection connection;

    public ServiceAbonnement() {
        connection = MyDb.getInstance().getConnection();
    }

    @Override
    public void create(Abonnement obj) throws SQLException {
        String sql = "INSERT INTO abonnement (id_utilisateur, id_Pack, date_Souscription, date_Expiration, statut) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setInt(1, 0); // Always set id_utilisateur to 0
        pstmt.setInt(2, obj.getId_Pack());
        pstmt.setDate(3, obj.getDate_Souscription());
        pstmt.setDate(4, obj.getDate_Expiration());
        pstmt.setString(5, obj.getStatut());

        pstmt.executeUpdate();
        System.out.println("✅ Abonnement inserted successfully!");
    }

    @Override
    public void update(Abonnement abonnement) throws SQLException {
        String query = "UPDATE abonnement SET id_pack = ?, id_utilisateur = 0, date_souscription = ?, date_expiration = ?, statut = ? WHERE id_abonnement = ?";
        PreparedStatement ps = connection.prepareStatement(query);

        ps.setInt(1, abonnement.getId_Pack());
        ps.setDate(2, abonnement.getDate_Souscription());
        ps.setDate(3, abonnement.getDate_Expiration());
        ps.setString(4, abonnement.getStatut());
        ps.setInt(5, abonnement.getId_Abonnement());

        int rowsUpdated = ps.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("✅ Abonnement updated successfully!");
        } else {
            System.out.println("❌ No rows updated. Check if the ID exists.");
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM abonnement WHERE id_Abonnement = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("✅ Abonnement deleted successfully!");
    }

    public List<Abonnement> getAll() throws SQLException {
        String sql = "SELECT * FROM abonnement";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Abonnement> list = new ArrayList<>();

        while (rs.next()) {
            Abonnement abonnement = new Abonnement();

            abonnement.setId_Abonnement(rs.getInt("id_abonnement"));
            abonnement.setId_utilisateur(0); // Ensure it remains 0
            abonnement.setId_Pack(rs.getInt("id_Pack"));
            abonnement.setDate_Souscription(rs.getDate("date_Souscription"));
            abonnement.setDate_Expiration(rs.getDate("date_Expiration"));
            abonnement.setStatut(rs.getString("statut"));

            list.add(abonnement);
        }

        return list;
    }
}
