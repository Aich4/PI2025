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
        String sql = "insert into mission (description,points_recompense,statut,id_recompense) values (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getDescription());
        ps.setInt(2,obj.getPoints_recompense());
        ps.setString(3,obj.getStatut());
        ps.setInt(4,obj.getIdRec());
        ps.executeUpdate();
    }

    @Override
    public  void update(Mission obj) throws Exception {
        String sql = "update mission set description=?,points_recompense=?,statut=?,id_recompense=?  where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getDescription());
        ps.setInt(2,obj.getPoints_recompense());
        ps.setString(3,obj.getStatut());
        ps.setInt(4,obj.getIdRec());
        ps.setInt(5,obj.getId());
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
            mission.setIdRec(rs.getInt("id_recompense"));
            missions.add(mission);
        }
        return missions;
    }


    public void valider(Mission obj) throws Exception {
        String sql = "UPDATE mission SET statut=? WHERE id=?";
        PreparedStatement ps = connection.prepareStatement(sql);

        obj.setStatut("Validé"); // 🔥 Assure-toi de mettre à jour l'objet aussi

        ps.setString(1, obj.getStatut()); // Utiliser "Validé"
        ps.setInt(2, obj.getId());

        ps.executeUpdate();
    }


}
