package tn.esprit.services;

import tn.esprit.entities.Pack;
import tn.esprit.utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePack implements Ipack<Pack> {
    private Connection connection;

    public ServicePack() {
        connection = MyDb.getInstance().getConnection();
    }

    @Override
    public void create(Pack obj) throws SQLException {
        String sql = "INSERT INTO pack(nom_Pack, description, prix, duree, avantages, statut) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, obj.getNom_Pack());
        pstmt.setString(2, obj.getDescription());
        pstmt.setFloat(3, obj.getPrix());
        pstmt.setInt(4, obj.getDuree());
        pstmt.setString(5, obj.getAvantages());
        pstmt.setString(6, obj.getStatut());
        pstmt.executeUpdate();
    }

    @Override
    public void update(Pack obj) throws SQLException {
        String sql = "UPDATE pack SET nom_Pack = ?, description = ?, prix = ?, duree = ?, avantages = ?, statut = ? WHERE id_Pack = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, obj.getNom_Pack());
        pstmt.setString(2, obj.getDescription());
        pstmt.setFloat(3, obj.getPrix());
        pstmt.setInt(4, obj.getDuree());
        pstmt.setString(5, obj.getAvantages());
        pstmt.setString(6, obj.getStatut());
        pstmt.setInt(7, obj.getId_Pack());
        pstmt.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pack WHERE id_Pack = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    @Override
    public List<Pack> getAll() throws SQLException {
        String sql = "SELECT * FROM pack";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Pack> list = new ArrayList<>();
        while (rs.next()) {
            Pack p = new Pack();
            p.setId_Pack(rs.getInt("id_Pack"));
            p.setNom_Pack(rs.getString("nom_Pack"));
            p.setDescription(rs.getString("description"));
            p.setPrix(rs.getFloat("prix"));
            p.setDuree(rs.getInt("duree"));
            p.setAvantages(rs.getString("avantages"));
        }
        return list;
    }
}

