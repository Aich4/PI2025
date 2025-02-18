package models;

import javafx.scene.control.ListCell;

public class Pack {
    private int id_Pack, duree;
    private String nom_Pack, description, avantages, statut;
    private float prix;

    // Constructeur avec paramètres
    public Pack(int id_Pack, String nom_Pack, String description, float prix, int duree, String avantages, String statut) {
        this.id_Pack = id_Pack;
        this.nom_Pack = nom_Pack;
        this.description = description;
        this.prix = prix;
        this.duree = duree;
        this.avantages = avantages;
        this.statut = statut;
    }

    public Pack( String nom_Pack, String description, float prix, int duree, String avantages, String statut) {
        this.nom_Pack = nom_Pack;
        this.description = description;
        this.prix = prix;
        this.duree = duree;
        this.avantages = avantages;
        this.statut = statut;
    }

    // Constructeur sans paramètres
    public Pack() {
    }

    // Getters et Setters
    public int getId_Pack() {
        return id_Pack;
    }

    public void setId_Pack(int id_Pack) {
        this.id_Pack = id_Pack;
    }

    public String getNom_Pack() {
        return nom_Pack;
    }

    public void setNom_Pack(String nom_Pack) {
        this.nom_Pack = nom_Pack;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getAvantages() {
        return avantages;
    }

    public void setAvantages(String avantages) {
        this.avantages = avantages;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return getId_Pack() + " | " + "Nom: " + nom_Pack + " | Description: " + description +
                " | Prix: " + prix + " | Durée: " + duree + " | Avantages: " + avantages +
                " | Statut: " + statut;
    }
    public static class PackListCell extends ListCell<Pack> {
        @Override
        protected void updateItem(Pack pack, boolean empty) {
            super.updateItem(pack, empty);

            if (empty || pack == null) {
                setText(null);
            } else {
                setText(pack.getNom_Pack() + " - " + pack.getPrix() + " TND");
            }
        }


}}
