package services;

import models.Categorie;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieService implements CrudInterface <Categorie> {

    private Connection connection;
    public CategorieService() {connection = MyDb.getInstance().getConnection();}

    @Override
    public void create(Categorie obj) throws Exception {

        if (categoryExists(obj.getNom())) {
            throw new Exception("A category with this name already exists.");
        }
        String sql="insert into categorie(nom,description,logo,nbr_partenaire)values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, obj.getNom());
        preparedStatement.setString(2, obj.getDescription());
        preparedStatement.setString(3, obj.getLogo());
        preparedStatement.setInt(4, obj.getNbr_partenaire());
        preparedStatement.executeUpdate();

    }

    @Override
    public void update(Categorie obj) throws Exception {
        String sql="update categorie set  nom=?,description=?,logo=?,nbr_partenaire=? where id=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, obj.getNom());
        preparedStatement.setString(2, obj.getDescription());
        preparedStatement.setString(3, obj.getLogo());
        preparedStatement.setInt(4, obj.getNbr_partenaire());
        preparedStatement.setInt(5, obj.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws Exception {
        String sql="delete from categorie where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public List<Categorie> getAll() throws Exception {
        String sql = "select * from Categorie";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Categorie> list = new ArrayList<>();
        while (rs.next()) {
            Categorie obj = new Categorie();
            obj.setId(rs.getInt("id"));
            obj.setNom(rs.getString("nom"));
            obj.setDescription(rs.getString("description"));
            obj.setLogo(rs.getString("logo"));
            obj.setNbr_partenaire(rs.getInt("nbr_partenaire"));
            list.add(obj);

        }
        return list;
    }

    public List<String> getAllNames() throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "select * from Categorie";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            list.add(rs.getString("nom"));
        }
        return list;
    }

    public int getIdByNom(String nom) throws Exception {
        String sql = "select id from categorie where nom=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nom);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");

        }
        return -1;
    }

    public String getNomById(int idCategorie) throws Exception {
        String sql = "select nom from categorie where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idCategorie);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getString("nom");

        }
        return null;

    }

    public void incrementNbrCategorie(int idCategorie) throws SQLException {
        String sql = "UPDATE categorie SET nbr_partenaire = nbr_partenaire + 1 WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idCategorie);
        preparedStatement.executeUpdate();
    }
    public void decrementNbrCategorie(int categoryId) throws SQLException {
        String sql = "UPDATE categorie SET nbr_partenaire = nbr_partenaire - 1 WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, categoryId);
        preparedStatement.executeUpdate();
    }
    public boolean categoryExists(String nom) {
        // Logic to check if a category with the same name already exists in the database
        // Assuming you have a method to query the database, e.g., `findCategoryByName`
        try {
            Categorie existingCategorie = findCategoryByName(nom);
            return existingCategorie != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Categorie findCategoryByName(String nom) {
        Categorie categorie = null;
        String query = "SELECT * FROM categorie WHERE nom = ?"; // Use "categorie" instead of "categories"

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nom);  // Set the category name parameter in the query

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // If a category with the same name is found, create a Categorie object
                    String description = rs.getString("description");
                    String imagePath = rs.getString("logo"); // Assuming "logo" column holds the image path

                    // Create the Categorie object with the fetched data
                    categorie = new Categorie(nom, description, imagePath);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorie; // Return the found category or null if not found
    }



}
