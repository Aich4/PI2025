package models;
import java.util.Date;


public class Reclamation {
    private int id_rec;
    private String description_rec;  // Correction du nom du champ
    private String type_rec;
    private Date date_rec;

    private static final List<String> TYPES_VALIDES = Arrays.asList("site", "bug", "pack", "commercant", "guide");


    // Constructeur
    public Reclamation(int id_rec, String description_rec, String type_rec , Date date_rec) {
        this.id_rec = id_rec;
        this.description_rec = description_rec;
        this.type_rec = type_rec;
        this.date_rec = date_rec;
    }

    // Méthodes
    public void ajouterReclamation() {
        // Implémentation pour ajouter une réclamation
    }
    public void consulterReclamation() {
        // Implémentation pour consulter une réclamation
    }
    public void supprimerReclamation() {
        // Implémentation pour supprimer une réclamation
    }

    // Getters et Setters
    public int getIdReclamation() { return id_rec; }
    public void setIdReclamation(int id_rec) { this.id_rec = id_rec; }

    public String getDescription() { return description_rec; }
    public void setDescription(String description_rec) { this.description_rec = description_rec; }

    public String getType() { return type_rec; }
    public void setType(String type_rec) { this.type_rec = type_rec; }


    public Date getDate() { return date_rec; }
    public void setDate(Date date_rec) { this.date_rec = date_rec; }

    public void setType_rec(String type_rec) {
        if (TYPES_VALIDES.contains(type_rec.toLowerCase())) {
            this.type_rec = type_rec;
        } else {
            throw new IllegalArgumentException("Type de réclamation invalide. Doit être l'un de : " + TYPES_VALIDES);
        }
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id_rec +
                ", description='" + description_rec + '\'' +
                ", type=" + type_rec +
                ", date=" + date_rec +
                '}';
    }
}
