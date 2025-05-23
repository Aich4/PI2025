package services;

import models.Avis;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AvisService implements CrudInterface<Avis> {

    private Connection connection;
    public AvisService() {
        connection = MyDb.getInstance().getConnection();
    }
    @Override
    public void create(Avis obj) throws Exception {
        String sql = "insert into AVIS (description_av,id_des)values(?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getDescription_av());
        ps.setInt(2,obj.getId_des());
        ps.executeUpdate();
    }

    @Override
    public void update(Avis obj) throws Exception {
        String sql = "update AVIS set description_av=?,id_des=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getDescription_av());
        ps.setInt(2,obj.getId_des());
        ps.setInt(3,obj.getId());
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from AVIS where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    public List<Avis> getAll() throws Exception {
        List<Avis> avisList = new ArrayList<>();
        String sql = "select * from AVIS";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Avis a = new Avis();
            a.setId(rs.getInt("id"));
            a.setDescription_av(rs.getString("description_av"));
            a.setId_des(rs.getInt("id_des"));
            avisList.add(a);
        }
        return avisList;
    }

    public List<Avis> getAvisByDestination(int destinationId) throws Exception {
        List<Avis> avisList = new ArrayList<>();
        String sql = "SELECT id, description_av, id_des FROM AVIS WHERE id_des = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, destinationId);
            System.out.println("Fetching reviews for destination ID: " + destinationId); // Debug log
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Avis a = new Avis();
                a.setId(rs.getInt("id"));
                a.setDescription_av(rs.getString("description_av"));
                a.setId_des(rs.getInt("id_des"));
                avisList.add(a);
                // Debug log
                System.out.println("Found review: ID=" + a.getId() + ", Description=" + a.getDescription_av());
            }
            // Debug log
            System.out.println("Total reviews found: " + avisList.size());
        }
        return avisList;
    }
}
