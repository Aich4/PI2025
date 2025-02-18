package models;

public class Mission {
    int id;
    String description;
    int points_recompense;
    String statut;

    public Mission() {};

    public Mission(int id, String description, int points_recompense, String statut) {
        this.id = id;
        this.description = description;
        this.points_recompense = points_recompense;
        this.statut = statut;
    }
    public Mission(String description, int points_recompense, String statut) {
        this.id = id;
        this.description = description;
        this.points_recompense = points_recompense;
        this.statut = statut;
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

    public int getPoints_recompense() {
        return points_recompense;
    }

    public void setPoints_recompense(int points_recompense) {
        this.points_recompense = points_recompense;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", points_recompense=" + points_recompense +
                ", statut='" + statut + '\'' +
                '}';
    }
}
