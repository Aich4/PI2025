package models;

import java.sql.Date;

public class Activite {
    int id,id_destination;
    String nom_activite,heure,statut;
    Date date;
    public Activite() {}
    public Activite(int id, String nom_activite, Date date, String heure, String statut, int id_destination) {
        this.id = id;
        this.nom_activite = nom_activite;
        this.date = date;
        this.heure = heure;
        this.statut = statut;
        this.id_destination = id_destination;

    }
    public Activite(int id_destination,String nom_activite, Date date, String heure, String statut) {
        this.id_destination = id_destination;
        this.nom_activite = nom_activite;
        this.date = date;
        this.heure = heure;
        this.statut = statut;

    }
    public Activite(String nom_activite, Date date, String heure, String statut) {

        this.nom_activite = nom_activite;
        this.date = date;
        this.heure = heure;
        this.statut = statut;

    }

    public int getId_destination() {
        return id_destination;
    }

    public void setId_destination(int id_destination) {
        this.id_destination = id_destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNom_activite() {
        return nom_activite;
    }

    public void setNom_activite(String nom_activite) {
        this.nom_activite = nom_activite;
    }

    @Override
    public String toString() {
        return "Activite{" +
                "id=" + id +
                ", nom_activite='" + nom_activite + '\'' +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}
