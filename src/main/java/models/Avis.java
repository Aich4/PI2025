package models;

public class Avis {
    int id,id_des;
    String description_av;
    private int rating;
    public Avis() {

    }
    public Avis(int id, int id_des, String description_av) {
        this.id = id;
        this.id_des = id_des;
        this.description_av = description_av;
    }
    public Avis(int id_des, String description_av) {
        this.id_des = id_des;
        this.description_av = description_av;
    }
    public Avis(int id, int id_des, String description_av, int rating) {
        this.id = id;
        this.id_des = id_des;
        this.description_av = description_av;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription_av() {
        return description_av;
    }

    public void setDescription_av(String description_av) {
        this.description_av = description_av;
    }

    public int getId_des() {
        return id_des;
    }

    public void setId_des(int id_des) {
        this.id_des = id_des;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "id=" + id +
                ", id_des=" + id_des +
                ", description_av='" + description_av + '\'' +
                '}';
    }
}
