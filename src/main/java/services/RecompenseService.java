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


}
