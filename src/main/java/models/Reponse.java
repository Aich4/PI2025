package models;
import java.sql.Date;
import java.sql.Timestamp;

public class Reponse {
    private int id_rep;
    private int id_rec;
    private Timestamp date_rep;
    private String contenu_rep;

    // Constructeurs
    public Reponse(int id_rep, int id_rec, Timestamp date_rep, String contenu_rep) {
        this.id_rep = id_rep;
        this.id_rec = id_rec;
        setDate(date_rep);
        this.contenu_rep = contenu_rep;
    }

    public Reponse(int id_rec, Timestamp date_rep, String contenu_rep) {
        this.id_rec = id_rec;
        setDate(date_rep);
        this.contenu_rep = contenu_rep;
    }

    public Reponse(int idReclamation, Date date, String contenu) {
    }

    public Reponse() {

    }

    // Méthodes
    public void afficher_rep() {
        // Implémentation pour afficher une réponse
    }

    public void ajouter_rep() {
        // Implémentation pour ajouter une réponse
    }

    public void supprimer_rep() {
        // Implémentation pour supprimer une réponse
    }

    // Getters et Setters
    public int getId_rep() { return id_rep; }
    public void setId_rep(int id_rep) { this.id_rep = id_rep; }

    public int getId_rec() { return id_rec; }
    public void setId_rec(int id_rec) { this.id_rec = id_rec; }

    public Timestamp getDate_rep() { return date_rep; }
    public void setDate(Timestamp date_rep) {
        if (date_rep == null) {
            throw new IllegalArgumentException("La date ne peut pas être nulle");
        }
        // Vérifier si la date n'est pas dans le futur
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (date_rep.after(now)) {
            throw new IllegalArgumentException("La date ne peut pas être dans le futur");
        }
        this.date_rep = date_rep;
    }

    public String getContenu_rep() { return contenu_rep; }
    public void setContenu_rep(String contenu_rep) { this.contenu_rep = contenu_rep; }

    // Méthode toString
    @Override
    public String toString() {
        return "Reponse{" +
                "id reponse=" + id_rep +
                ", id reclamation=" + id_rec +
                ", date reponse=" + date_rep +
                ", contenu='" + contenu_rep + '\'' +
                '}';
    }
}
