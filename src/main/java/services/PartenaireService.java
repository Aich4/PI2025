package services;

import models.Partenaire;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartenaireService implements CrudInterface <Partenaire>{

    private Connection connection;
    public PartenaireService() {connection = MyDb.getInstance().getConnection();}


    @Override
    public void create(Partenaire obj) throws Exception {
        String sql = "insert into Partenaire(nom,email,adresse,date_ajout,description,id_categorie)values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, obj.getNom());
        preparedStatement.setString(2, obj.getEmail());
        preparedStatement.setString(3, obj.getAdresse());
        preparedStatement.setDate(4, obj.getDate_ajout());
        preparedStatement.setString(5, obj.getDescription());
        preparedStatement.setInt(6, obj.getId_categorie());
        preparedStatement.executeUpdate();

    }

    @Override
    public void update(Partenaire obj) throws Exception {
        String sql = "update Partenaire set nom=?,email=?,adresse=?,description=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, obj.getNom());
        preparedStatement.setString(2, obj.getEmail());
        preparedStatement.setString(3, obj.getAdresse());
        preparedStatement.setString(4, obj.getDescription());
        preparedStatement.setDate(5, obj.getDate_ajout());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from Partenaire where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();


    }

    @Override
    public List<Partenaire> getAll() throws Exception {
        String sql = "select * from Partenaire";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Partenaire> list = new ArrayList<>();
        while (rs.next()) {
            Partenaire obj = new Partenaire();
            obj.setId(rs.getInt("id"));
            obj.setNom(rs.getString("nom"));
            obj.setEmail(rs.getString("email"));
            obj.setAdresse(rs.getString("adresse"));
            obj.setDescription(rs.getString("description"));
            obj.setDate_ajout(rs.getDate("date_ajout"));
            obj.setId_categorie(rs.getInt("id_categorie"));
            list.add(obj);

        }
        return list;
    }

    public String getCategorieNameById(int id_categorie) {
        String categorieName = "";
        try {
            String query = "SELECT nom FROM categorie WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id_categorie);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                categorieName = rs.getString("nom");
                System.out.println("Nom de la catégorie trouvé : " + categorieName);  // Ajout d'un log de débogage
            } else {
                System.out.println("Aucune catégorie trouvée pour l'ID : " + id_categorie);  // Log si la catégorie n'est pas trouvée
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorieName;
    }

}
