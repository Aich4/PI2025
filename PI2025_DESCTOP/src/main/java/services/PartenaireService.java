package services;

import models.Partenaire;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartenaireService implements CrudInterface <Partenaire>{

    private Connection connection;
    public PartenaireService() {connection = MyDb.getInstance().getConnection();}


    @Override
    public void create(Partenaire obj) throws Exception {
        if (partenaireExists(obj.getNom())) {
            throw new Exception("A partner with this name already exists.");
        }

        String sql = "INSERT INTO Partenaire(nom, email, adresse, date_ajout, description, id_categorie, num_tel, montant, logo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, obj.getNom());
        preparedStatement.setString(2, obj.getEmail());
        preparedStatement.setString(3, obj.getAdresse());
        preparedStatement.setDate(4, obj.getDate_ajout());
        preparedStatement.setString(5, obj.getDescription());
        preparedStatement.setInt(6, obj.getId_categorie());
        preparedStatement.setInt(7, obj.getNum_tel());
        preparedStatement.setDouble(8, obj.getMontant());
        preparedStatement.setString(9, obj.getLogo());
        preparedStatement.executeUpdate();
    }


    public void update(Partenaire obj) throws Exception {
        String sql = "UPDATE Partenaire SET nom=?, email=?, adresse=?, description=?, date_ajout=?, num_tel=?, id_categorie=?, montant=?, logo=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, obj.getNom());
        preparedStatement.setString(2, obj.getEmail());
        preparedStatement.setString(3, obj.getAdresse());
        preparedStatement.setString(4, obj.getDescription());
        preparedStatement.setDate(5, obj.getDate_ajout());
        preparedStatement.setInt(6, obj.getNum_tel());
        preparedStatement.setInt(7, obj.getId_categorie());
        preparedStatement.setDouble(8, obj.getMontant());
        preparedStatement.setString(9, obj.getLogo());
        preparedStatement.setInt(10, obj.getId());
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
        String sql = "SELECT * FROM Partenaire";
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
            obj.setNum_tel(rs.getInt("num_tel"));
            obj.setMontant(rs.getDouble("montant")); // new
            obj.setLogo(rs.getString("logo"));       // new
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

    public boolean partenaireExists(String nom) {
        String sql = "SELECT COUNT(*) FROM Partenaire WHERE nom = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nom);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return true; // Partner already exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Partner does not exist
    }
    public List<Partenaire> searchByCriteria(String criterion, String searchText) throws Exception {
        // Assuming you have a database connection or in-memory data
        List<Partenaire> partenaires = getAll(); // Get all partners

        // Filter based on the selected criterion
        switch (criterion) {
            case "Nom":
                return partenaires.stream()
                        .filter(p -> p.getNom().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
            case "Email":
                return partenaires.stream()
                        .filter(p -> p.getEmail().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
            case "Adresse":
                return partenaires.stream()
                        .filter(p -> p.getAdresse().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
            case "Description":
                return partenaires.stream()
                        .filter(p -> p.getDescription().toLowerCase().contains(searchText.toLowerCase()))
                        .collect(Collectors.toList());
            default:
                return partenaires; // Return all if no criterion matched
        }
    }


}
