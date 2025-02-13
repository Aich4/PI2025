package services;

import models.Mission;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MissionService implements CrudInterface<Mission>{
    private Connection connection;
    public MissionService() {connection = MyDb.getInstance().getConnection();}

    @Override
    public void create(Mission obj) throws Exception {
        String sql = "insert into mission (description,points_recompense,statut) values (?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getDescription());
        ps.setInt(2,obj.getPoints_recompense());
        ps.setString(3,obj.getStatut());
        ps.executeUpdate();
    }

    @Override
    public void update(Mission obj) throws Exception {
        String sql = "update mission set description=?,points_recompense=?,statut=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getDescription());
        ps.setInt(2,obj.getPoints_recompense());
        ps.setString(3,obj.getStatut());
        ps.setInt(4,obj.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from mission where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    public List<Mission> getAll() throws Exception {
        String sql = "select * from mission";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Mission> missions = new ArrayList<Mission>();
        while (rs.next()) {
            Mission mission = new Mission();
            mission.setId(rs.getInt("id"));
            mission.setDescription(rs.getString("description"));
            mission.setPoints_recompense(rs.getInt("points_recompense"));
            mission.setStatut(rs.getString("statut"));
            missions.add(mission);
        }
        return missions;
    }
}
