package services;

import models.Destination;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DestinationService implements CrudInterface<Destination> {
    private Connection connection;
    public DestinationService() {connection = MyDb.getInstance().getConnection();}
    @Override
    public void create(Destination obj) throws Exception {
        String sql = "insert into destination(nom_destination,description,image_destination,latitude,longitude,temperature,rate) values(?,?,?,?,?,?,?)";
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
        String sql = "update destination set nom_destination = ?,description=?,image_destination=?,latitude=?,longitude=?,temperature=?,rate=? where id=?";
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
}
