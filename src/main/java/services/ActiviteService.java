package services;

import models.Activite;
import utils.MyDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActiviteService implements CrudInterface<Activite> {
    private Connection connection;
    public ActiviteService() {connection = MyDb.getInstance().getConnection();}

    @Override
    public void create(Activite obj) throws Exception {
        String sql = "insert into activite (nom_activite,date,heure,statut) values (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getNom_activite());
        ps.setString(2,obj.getDate());
        ps.setString(3,obj.getHeure());
        ps.setString(4,obj.getStatut());
        ps.executeUpdate();
    }

    @Override
    public void update(Activite obj) throws Exception {
        String sql = "update activite set nom_activite=?,date=?,heure=?,statut=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getNom_activite());
        ps.setString(2,obj.getDate());
        ps.setString(3,obj.getHeure());
        ps.setString(4,obj.getStatut());
        ps.setInt(5,obj.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "delete from activite where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    public List<Activite> getAll() throws Exception {
        String sql = "select * from activite";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Activite> activites = new ArrayList<Activite>();
        while (rs.next()) {
            Activite activite = new Activite();
            activite.setId(rs.getInt("id"));
            activite.setNom_activite(rs.getString("nom_activite"));
            activite.setDate(rs.getString("date"));
            activite.setHeure(rs.getString("heure"));
            activite.setStatut(rs.getString("statut"));
            activites.add(activite);
        }
        return activites;
    }
}
