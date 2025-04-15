package services;

import models.Mission;
import models.Recompense;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class RecompenseService implements CrudInterface<Recompense>{
    private Connection connection;
    public RecompenseService() {connection = MyDb.getInstance().getConnection();}

    @Override
    public void create(Recompense obj) throws Exception {
        String sql = "insert into recompense (description,cout_en_points,disponibilite) values (?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getDescription());
        ps.setInt(2,obj.getCout_en_points());
        ps.setString(3,obj.getDisponibilite());
        ps.executeUpdate();
    }

    @Override
    public void update(Recompense obj) throws Exception {
        String sql = "update recompense set description=?,cout_en_points=?,disponibilite=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getDescription());
        ps.setInt(2,obj.getCout_en_points());
        ps.setString(3,obj.getDisponibilite());
        ps.setInt(4,obj.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from recompense where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    public List<Recompense> getAll() throws Exception {
        String sql = "select * from recompense";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Recompense> recompenses = new ArrayList<Recompense>();
        while (rs.next()) {
            Recompense recompense = new Recompense();
            recompense.setId(rs.getInt("id"));
            recompense.setDescription(rs.getString("description"));
            recompense.setCout_en_points(rs.getInt("cout_en_points"));
            recompense.setDisponibilite(rs.getString("disponibilite"));
            recompenses.add(recompense);
        }
        return recompenses;
    }

    public List<String> getAllDescription() throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "select * from recompense";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            list.add(rs.getString("description"));
        }
        return list;
    }

    public int getIdByDescrption(String desc) throws Exception {
        String sql = "select id from recompense where description=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, desc);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");

        }
        return -1;
    }

    public String getDescriptionById(int idrecompense) throws Exception {
        String sql = "select description from recompense where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, idrecompense);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getString("description"); // ✅ Retourne la description
        }

        return null; // Retourne null si aucune description trouvée
    }

    public List<Recompense> searchRecompenses(String critere, String searchText) throws Exception {
        List<Recompense> recompenses = new ArrayList<>();
        String sql = "";

        switch (critere) {
            case "Description":
                sql = "SELECT * FROM recompense WHERE LOWER(description) LIKE LOWER(?)";
                break;
            case "Coût en points":
                // Vérifie si la saisie contient uniquement des chiffres
                if (!searchText.matches("\\d+")) {
                    return recompenses;  // Retourne une liste vide si ce n'est pas un nombre
                }
                sql = "SELECT * FROM recompense WHERE cout_en_points LIKE ?";
                break;
            case "Disponibilité":
                sql = "SELECT * FROM recompense WHERE LOWER(disponibilite) LIKE LOWER(?)";
                break;
            default:
                return recompenses;
        }

        PreparedStatement ps = connection.prepareStatement(sql);

        if (critere.equals("Coût en points")) {
            // Recherche par coût en points, où le coût commence par 'searchText'
            ps.setString(1, searchText + "%");
        } else {
            ps.setString(1, searchText.toLowerCase() + "%");
        }

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            recompenses.add(new Recompense(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getInt("cout_en_points"),
                    rs.getString("disponibilite")
            ));
        }

        return recompenses;
    }


    public List<Recompense> sortRecompenses(String critere) throws Exception {
        List<Recompense> result = new ArrayList<>();
        String sql = "SELECT * FROM recompense ORDER BY ";

        // Définition du critère de tri
        switch (critere) {
            case "Description":
                sql += "description";
                break;
            case "Coût en points":
                sql += "cout_en_points";
                break;
            case "Disponibilité":
                sql += "disponibilite";
                break;
            default:
                return getAll(); // Retourner toutes les récompenses si aucun critère valide
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new Recompense(
                        rs.getInt("id"),
                        rs.getString("description"),
                        rs.getInt("cout_en_points"),
                        rs.getString("disponibilite")
                ));
            }
        }
        return result;
    }



}
