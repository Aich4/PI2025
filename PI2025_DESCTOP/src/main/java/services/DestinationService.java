package services;

import models.Destination;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DestinationService implements CrudInterface<Destination> {
    private Connection connection;
    public DestinationService() {connection = MyDb.getInstance().getConnection();}
    @Override
    public void create(Destination obj) throws Exception {
        if (isNameExists(obj.getNom_destination())) {
            throw new Exception("A destination with this name already exists!");
        }

        String sql = "INSERT INTO destination(nom_destination,description,image_destination,latitude,longitude,temperature,rate) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, obj.getNom_destination());
        ps.setString(2, obj.getDecription());
        ps.setString(3, obj.getImage_destination());
        ps.setDouble(4, obj.getLatitude());
        ps.setDouble(5, obj.getLongitude());
        ps.setDouble(6, obj.getTemperature());
        ps.setDouble(7, obj.getRate());
        ps.executeUpdate();
    }


    @Override
    public void update(Destination obj) throws Exception {
        if (isNameExistsForOtherId(obj.getNom_destination(), obj.getId())) {
            throw new Exception("Another destination with this name already exists!");
        }

        String sql = "UPDATE destination SET nom_destination = ?, description = ?, image_destination = ?, latitude = ?, longitude = ?, temperature = ?, rate = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, obj.getNom_destination());
        ps.setString(2, obj.getDecription());
        ps.setString(3, obj.getImage_destination());
        ps.setDouble(4, obj.getLatitude());
        ps.setDouble(5, obj.getLongitude());
        ps.setDouble(6, obj.getTemperature());
        ps.setDouble(7, obj.getRate());
        ps.setInt(8, obj.getId());
        ps.executeUpdate();
    }


    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from destination where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Destination> getAll() throws Exception {
        String sql = "select * from destination";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Destination> list = new ArrayList<>();
        while (rs.next()) {
            Destination obj = new Destination();
            obj.setId(rs.getInt("id"));
            obj.setNom_destination(rs.getString("nom_destination"));
            obj.setDecription(rs.getString("description"));
            obj.setImage_destination(rs.getString("image_destination"));
            obj.setLatitude(rs.getDouble("latitude"));
            obj.setLongitude(rs.getDouble("longitude"));
            obj.setTemperature(rs.getDouble("temperature"));
            obj.setRate(rs.getDouble("rate"));
            list.add(obj);

        }
        return list;

    }
    public List<String> getAllNames() throws Exception {
        List<String> list = new ArrayList<>();
        String sql = "select * from destination";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("nom_destination"));

        }
        return list;
    }
    public int getIdByName(String name) throws Exception {
        String sql = "select id from destination where nom_destination = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        return -1;

    }
    public boolean updatee(Destination destination) {
        try {
            // Check if the name exists for another record
            if (isNameExistsForOtherId(destination.getNom_destination(), destination.getId())) {
                System.out.println("Another destination with this name already exists!");
                return false; // Prevent update if name is taken
            }

            // Update the destination if the name is unique
            String sql = "UPDATE destination SET nom_destination = ?, description = ?, image_destination = ?, latitude = ?, longitude = ?, temperature = ?, rate = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, destination.getNom_destination());
            ps.setString(2, destination.getDecription());
            ps.setString(3, destination.getImage_destination());
            ps.setDouble(4, destination.getLatitude());
            ps.setDouble(5, destination.getLongitude());
            ps.setDouble(6, destination.getTemperature());
            ps.setDouble(7, destination.getRate());
            ps.setInt(8, destination.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Return true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isNameExists(String name) throws Exception {
        String sql = "SELECT COUNT(*) FROM destination WHERE nom_destination = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0; // If count > 0, name exists
        }
        return false;
    }
    private boolean isNameExistsForOtherId(String name, int id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM destination WHERE nom_destination = ? AND id <> ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0; // Returns true if name exists in another record
        }
        return false;
    }




}
