package models;

import java.sql.Date;

public class Partenaire {
    private int id;
    String nom;
    String email;
    String adresse;
    String description ;
    Date date_ajout;
    private int id_categorie;
    int num_tel;


    public Partenaire() {}
    public Partenaire(String nom, String email, String adresse, String description, java.sql.Date date_ajout, int id_categorie, int num_tel)
    {
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.description = description;
        this.date_ajout = date_ajout;
        this.id_categorie = id_categorie;
        this.num_tel = num_tel;

    }

    public Partenaire(int id, String nom, String email, String adresse, String description, Date date_ajout) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.description = description;
        this.date_ajout = date_ajout;


    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.sql.Date getDate_ajout() {
        return (java.sql.Date) date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Partenaire{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", adresse='" + adresse + '\'' +
                ", description='" + description + '\'' +
                ", date_ajout=" + date_ajout +
                '}';
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }



}
