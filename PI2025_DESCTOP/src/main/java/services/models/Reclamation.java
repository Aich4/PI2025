package models;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;


public class Reclamation {
    private int id_rec;
    private String description_rec;  // Correction du nom du champ
    private String type_rec;
    private Timestamp date_rec;
    private String etat_rec;


    // Constructeur
    public Reclamation(int id_rec, String description_rec, String type_rec, Timestamp date_rec) {
        this.id_rec = id_rec;
        this.description_rec = description_rec;
        this.type_rec = type_rec;
        this.date_rec = date_rec;
        this.etat_rec = "En cours"; // Default state is "En cours"
    }

    public Reclamation(String description_rec, String type_rec, Timestamp date_rec) {
        this.description_rec = description_rec;
        this.type_rec = type_rec;
        this.date_rec = date_rec;
        this.etat_rec = "0"; // Default state is "non traité"
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


    public Timestamp getDate() { return date_rec; }
    public void setDate(Timestamp date) {
        if (date == null) {
            throw new IllegalArgumentException("La date ne peut pas être nulle");
        }
        // Vérifier si la date n'est pas dans le futur
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (date.after(now)) {
            throw new IllegalArgumentException("La date ne peut pas être dans le futur");
        }
        this.date_rec = date;
    }

    public String getEtat() { return etat_rec; }
    public void setEtat(String etat_rec) {
        if (!Arrays.asList("En cours", "Résolue", "Rejetée").contains(etat_rec)) {
            throw new IllegalArgumentException("État invalide. Les valeurs possibles sont: En cours, Résolue, Rejetée");
        }
        this.etat_rec = etat_rec;
    }

    public String getEtatDescription() {
        return etat_rec;
    }

    //ctrl saisie
    private static final List<String> TYPES_VALIDES = Arrays.asList(
            "Problème technique",
            "Problème de paiement",
            "Problème de réservation",
            "Autre"
    );

    public void setType_rec(String type_rec) {
        if (TYPES_VALIDES.contains(type_rec.toLowerCase())) {
            this.type_rec = type_rec;
        } else {
            throw new IllegalArgumentException("Type de réclamation invalide. Doit être l'un de : " + TYPES_VALIDES);
        }
    }

    public class DescriptionControl {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            String description_rec;

            while (true) {
                System.out.print("Entrez la description (max 800 caractères) : ");
                description_rec = scanner.nextLine();

                if (description_rec.length() <= 800) {
                    break; // Sortir de la boucle si la description est valide
                } else {
                    System.out.println("Erreur : La description ne doit pas dépasser 800 caractères. Veuillez réessayer.");
                }
            }

            System.out.println("Description valide : " + description_rec);
            scanner.close();
        }
    }

    public static class DateControl {
        public static boolean isValidTimestamp(Timestamp timestamp) {
            if (timestamp == null) return false;

            // Vérifier si la date n'est pas dans le futur
            Timestamp now = new Timestamp(System.currentTimeMillis());
            return !timestamp.after(now);
        }
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id_rec +
                ", description='" + description_rec + '\'' +
                ", type=" + type_rec +
                ", date=" + date_rec +
                ", etat=" + getEtatDescription() +
                '}';
    }
}
