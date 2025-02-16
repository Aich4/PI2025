package models;

public class Categorie {
    int id;
    String nom;
    String description;
    String logo;
    int nbr_partenaire;

    public Categorie() {}
    public Categorie( int id,String nom, String description, String logo, int nbr_partenaire) {
        this.id=id;
        this.nom = nom;
        this.description = description;
        this.logo = logo;
        this.nbr_partenaire = nbr_partenaire;

    }
    public Categorie( String nom, String description,String logo) {

        this.nom = nom;
        this.description = description;
        this.logo = logo;
    }

    public Categorie( String nom, String description, String logo, int nbr_partenaire) {
        this.nom = nom;
        this.description = description;
        this.logo = logo;
        this.nbr_partenaire = nbr_partenaire;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getNbr_partenaire() {
        return nbr_partenaire;
    }

    public void setNbr_partenaire(int nbr_partenaire) {
        this.nbr_partenaire = nbr_partenaire;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", nbr_partenaire=" + nbr_partenaire +
                '}';
    }

}
