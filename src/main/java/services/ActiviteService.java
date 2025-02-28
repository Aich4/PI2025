package services;

import models.Activite;
import utils.MyDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActiviteService implements CrudInterface<Activite> {
    private Connection connection;
    public ActiviteService() {connection = MyDb.getInstance().getConnection();}

    @Override
    public void create(Activite obj) throws Exception {
        String sql = "insert into activite (nom_activite,date,heure,statut,id_destination) values (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getNom_activite());
        ps.setDate(2, obj.getDate());
        ps.setString(3,obj.getHeure());
        ps.setString(4,obj.getStatut());
        ps.setInt(5,obj.getId_destination());
        ps.executeUpdate();
    }

    @Override
    public void update(Activite obj) throws Exception {
        String sql = "update activite set nom_activite=?,date=?,heure=?,statut=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getNom_activite());
        ps.setDate(2, obj.getDate());
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
            activite.setDate(java.sql.Date.valueOf(rs.getString("date")));
            activite.setHeure(rs.getString("heure"));
            activite.setStatut(rs.getString("statut"));
            activites.add(activite);
        }
        return activites;
    }

    public List<Activite> getActivitiesByDestination(int id) throws Exception {
        String sql = "select * from activite where id_destination=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        List<Activite> activites = new ArrayList<Activite>();
        while (rs.next()) {
            Activite activite = new Activite();
            activite.setId(rs.getInt("id"));
            activite.setNom_activite(rs.getString("nom_activite"));
            activite.setDate(java.sql.Date.valueOf(rs.getString("date")));
            activite.setHeure(rs.getString("heure"));
            activite.setStatut(rs.getString("statut"));
            activites.add(activite);

        }
        return activites;
    }


    public boolean updateActivite(Activite obj)  {
    try{
        String sql = "update activite set nom_activite=?,date=?,heure=?,statut=? where id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,obj.getNom_activite());
        ps.setDate(2, obj.getDate());
        ps.setString(3,obj.getHeure());
        ps.setString(4,obj.getStatut());
        ps.setInt(5,obj.getId());
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0; // Return true if update was successful
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
        }
    }

}
