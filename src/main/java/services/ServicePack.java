package services;

import models.Pack;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePack implements Ipack<Pack> {
    private Connection connection;

    public ServicePack() {
        connection = MyDb.getInstance().getConnection();
    }

    public double getPackPriceById(int idPack) throws SQLException {
        double price = 0.0;  // Default price if not found
        String sql = "SELECT prix FROM pack WHERE id_Pack = ?";

        // Use try-with-resources to ensure proper resource management
        try (PreparedStatement ps = MyDb.getInstance().getConnection().prepareStatement(sql)) {
            // Set the pack ID parameter in the query
            ps.setInt(1, idPack);

            // Execute the query and retrieve the result
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Get the price from the result set
                price = rs.getDouble("prix");  // Assuming the price column is 'prix'
            }
        }

        // Return the price found or 0.0 if not found
        return price;
    }

    public List<String> getAllPackID() {
        List<String> packNames = new ArrayList<>();
        try {
            Connection conn =MyDb.getInstance().getConnection();
            String query = "SELECT id_Pack FROM pack"; // Adjust table/column names if necessary
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                packNames.add(rs.getString("id_Pack"));
            }
            System.out.println("Pack id from DB: " + packNames);

        } catch (SQLException e) {
            System.out.println("Error fetching pack names: " + e.getMessage());
        }
        return packNames;
    }
    public String getPackNameById(int idPack) {
        String nomPack = null;
        try {
            // Establish connection
            Connection conn = MyDb.getInstance().getConnection();

            // SQL query to retrieve nom_Pack by id_Pack
            String query = "SELECT nom_Pack FROM pack WHERE id_Pack = ?"; // Adjust table/column names if necessary

            // Prepare statement and set the parameter
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, idPack);  // Set the id_Pack parameter

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Check if the result exists
            if (rs.next()) {
                nomPack = rs.getString("nom_Pack");  // Get the nom_Pack
            }

            System.out.println("Pack Name for id " + idPack + ": " + nomPack);

        } catch (SQLException e) {
            System.out.println("Error fetching pack name: " + e.getMessage());
        }
        return nomPack;
    }




    @Override
    public void create(Pack obj) throws SQLException {
        String sql = "INSERT INTO pack(nom_Pack, description, prix, duree, avantages, statut, id_utilisateur) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, obj.getNom_Pack());
        pstmt.setString(2, obj.getDescription());
        pstmt.setFloat(3, obj.getPrix());
        pstmt.setInt(4, obj.getDuree());
        pstmt.setString(5, obj.getAvantages());
        pstmt.setString(6, obj.getStatut());
        pstmt.setInt(7, 0);  // Always set id_utilisateur to 0
        pstmt.executeUpdate();
    }


    @Override
    public void update(Pack pack) throws SQLException {

        String sql = "UPDATE pack SET nom_Pack = ?, description = ?, prix = ?, duree = ?, avantages = ?, statut = ? WHERE id_Pack = ?";
        PreparedStatement ps = connection.prepareStatement(sql) ;

        ps.setString(1, pack.getNom_Pack());
        ps.setString(2, pack.getDescription());
        ps.setFloat(3, pack.getPrix());
        ps.setInt(4, pack.getDuree());
        ps.setString(5, pack.getAvantages());
        ps.setString(6, pack.getStatut());
        ps.setInt(7, pack.getId_Pack());

        int rowsUpdated = ps.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("✅ Pack updated in the database successfully!");
        } else {
            System.out.println("❌ No rows updated. Check if the ID exists.");
        }
    }


    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM pack WHERE id_Pack = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate(); // Check the number of rows deleted

            return rowsAffected > 0; // Return true if at least one row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if deletion fails
        }
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
            p.setStatut(rs.getString("statut"));
            p.setId_Utilisateur(rs.getInt("id_utilisateur"));  // Add this line to fetch id_utilisateur

            list.add(p);
        }
        return list;
    }

}

