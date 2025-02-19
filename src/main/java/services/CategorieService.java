package services;

import models.Categorie;
import models.Partenaire;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategorieService implements CrudInterface <Categorie> {

    private Connection connection;
    public CategorieService() {connection = MyDb.getInstance().getConnection();}

    @Override
    public void create(Categorie obj) throws Exception {
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

}
