package services;
import  models.Abonnement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.MyDb;

public class ServiceAbonnement implements Iabonnement <Abonnement> {
    private Connection connection;

    public ServiceAbonnement() {
        connection = MyDb.getInstance().getConnection();
    }

    @Override
    public void create(Abonnement obj) throws SQLException {
        String sql = "INSERT INTO abonnement (id_Utilisateur, id_Pack, date_Souscription, date_Expiration, statut) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, obj.getId_Utilisateur());
        pstmt.setInt(2, obj.getId_Pack());
        pstmt.setDate(3, obj.getDate_Souscription());
        pstmt.setDate(4, obj.getDate_Expiration());
        pstmt.setString(5, obj.getStatut());
        pstmt.executeUpdate();
    }

    @Override
    public void update(Abonnement obj) throws SQLException {
        String sql = "UPDATE abonnement SET id_Utilisateur = ?, id_Pack = ?, date_Souscription = ?, date_Expiration = ?, statut = ? WHERE id_Abonnement = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, obj.getId_Utilisateur());
        pstmt.setInt(2, obj.getId_Pack());
        pstmt.setDate(3, obj.getDate_Souscription());
        pstmt.setDate(4, obj.getDate_Expiration());
        pstmt.setString(5, obj.getStatut());
        pstmt.setInt(6, obj.getId_Abonnement());
        pstmt.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM abonnement WHERE id_Abonnement = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    @Override
    public List<Abonnement> getAll() throws SQLException {
        String sql = "SELECT * FROM abonnement";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Abonnement> list = new ArrayList<>();
        while (rs.next()) {
            Abonnement a = new Abonnement();
            a.setId_Abonnement(rs.getInt("id_Abonnement"));
            a.setId_Utilisateur(rs.getInt("id_Utilisateur"));
            a.setId_Pack(rs.getInt("id_Pack"));
            a.setDate_Souscription(rs.getDate("date_Souscription"));
            a.setDate_Expiration(rs.getDate("date_Expiration"));
            a.setStatut(rs.getString("statut"));
            list.add(a);
        }
        return list;
    }
}
