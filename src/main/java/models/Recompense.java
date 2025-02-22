package models;

public class Recompense {
    int id;
    String description;
    int cout_en_points;
    String disponibilite;

    public Recompense() {};
    public Recompense(int id, String description, int cout_en_points, String disponibilite) {
        this.id = id;
        this.description = description;
        this.cout_en_points = cout_en_points;
        this.disponibilite = disponibilite;
    }
    public Recompense(String description, int cout_en_points, String disponibilite) {
        this.description = description;
        this.cout_en_points = cout_en_points;
        this.disponibilite = disponibilite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCout_en_points() {
        return cout_en_points;
    }

    public void setCout_en_points(int cout_en_points) {
        this.cout_en_points = cout_en_points;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "Recompense{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", cout_en_points=" + cout_en_points +
                ", disponibilite='" + disponibilite + '\'' +
                '}';
    }
}
