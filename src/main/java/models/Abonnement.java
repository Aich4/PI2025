package models;

import java.sql.Date;

public class Abonnement {
    private int id_Abonnement, id_Utilisateur, id_Pack;
    private String  statut;
    private Date date_Souscription, date_Expiration;

    // Constructeur avec paramètres
    public Abonnement(int id_Abonnement, int id_Utilisateur, int id_Pack, Date date_Souscription, Date date_Expiration, String statut) {
        this.id_Abonnement = id_Abonnement;
        this.id_Utilisateur = id_Utilisateur;
        this.id_Pack = id_Pack;
        this.date_Souscription = date_Souscription;
        this.date_Expiration = date_Expiration;
        this.statut = statut;
    }

    // Constructeur sans paramètres
    public Abonnement() {
    }

    // Getters et Setters
    public int getId_Abonnement() {
        return id_Abonnement;
    }

    public void setId_Abonnement(int id_Abonnement) {
        this.id_Abonnement = id_Abonnement;
    }

    public int getId_Utilisateur() {
        return id_Utilisateur;
    }

    public void setId_Utilisateur(int id_Utilisateur) {
        this.id_Utilisateur = id_Utilisateur;
    }

    public int getId_Pack() {
        return id_Pack;
    }

    public void setId_Pack(int id_Pack) {
        this.id_Pack = id_Pack;
    }

    public Date getDate_Souscription() {
        return date_Souscription;
    }

    public void setDate_Souscription(Date date_Souscription) {
        this.date_Souscription = date_Souscription;
    }

    public Date getDate_Expiration() {
        return date_Expiration;
    }

    public void setDate_Expiration(Date date_Expiration) {
        this.date_Expiration = date_Expiration;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Abonnement{" +
                "id_Abonnement=" + id_Abonnement +
                ", id_Utilisateur=" + id_Utilisateur +
                ", id_Pack=" + id_Pack +
                ", date_Souscription='" + date_Souscription + '\'' +
                ", date_Expiration='" + date_Expiration + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}
