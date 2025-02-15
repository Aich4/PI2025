package models;
import java.util.Date;

public class Reclamation {
    private int idReclamation;
    private String description;  // Correction du nom du champ
    private int id_coach;
    private int id_adherent;
    private Date date;

    public Reclamation(int idReclamation, String description, typeR type, int id_coach, int id_adherent, Date date) {
        this.idReclamation = idReclamation;
        this.description = description;
        this.id_coach = id_coach;
        this.id_adherent = id_adherent;
        this.date = date;
    }

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
    public int getIdReclamation() { return idReclamation; }
    public void setIdReclamation(int idReclamation) { this.idReclamation = idReclamation; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getId_coach() { return id_coach; }
    public void setId_coach(int id_coach) { this.id_coach = id_coach; }

    public int getId_adherent() { return id_adherent; }
    public void setId_adherent(int id_adherent) { this.id_adherent = id_adherent; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    @Override
    public String toString() {
        return "Reclamation{" +
                "idReclamation=" + idReclamation +
                ", description='" + description + '\'' +
                ", id_coach=" + id_coach +
                ", id_adherent=" + id_adherent +
                ", date=" + date +
                '}';
    }
}
