package models;
import java.util.Arrays;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;


public class Reclamation {
    private int id_rec;
    private String description_rec;  // Correction du nom du champ
    private String type_rec;
    private Date date_rec;
    //private Boolean etat_rec;


    // Constructeur
    public Reclamation(int id_rec, String description_rec, String type_rec , Date date_rec) {
        this.id_rec = id_rec;
        this.description_rec = description_rec;
        this.type_rec = type_rec;
        this.date_rec = date_rec;
        //this.etat_rec = false;
    }

    public Reclamation(String description_rec, String type_rec , Date date_rec) {

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

 /*public Boolean getEtat() { return etat_rec; }
    public void setEtat(Boolean etat_rec) { this.etat_rec = etat_rec; }*/


    //ctrl saisie
    private static final List<String> TYPES_VALIDES = Arrays.asList("site", "bug", "pack", "commercant", "guide", "autre");
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


    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id_rec +
                ", description='" + description_rec + '\'' +
                ", type=" + type_rec +
                ", date=" + date_rec +
                /*", etat=" + etat_rec +*/
                '}';
    }
}
