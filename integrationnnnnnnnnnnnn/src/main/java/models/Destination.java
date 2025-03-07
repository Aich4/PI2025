package models;

import java.util.Objects;

public class Destination {
    private int id;
    private String nom_destination,decription,image_destination;
    private double latitude,longitude,temperature,rate;
    public Destination() {

    }
    public Destination(int id,String nom_destination, String decription, String image_destination, double latitude, double longitude, double temperature, double rate)
    {
        this.id = id;
        this.nom_destination = nom_destination;
        this.decription = decription;
        this.image_destination = image_destination;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.rate = rate;

    }
    public Destination(String nom_destination, String decription, String image_destination, double latitude, double longitude, double temperature, double rate)
    {

        this.nom_destination = nom_destination;
        this.decription = decription;
        this.image_destination = image_destination;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.rate = rate;

    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getImage_destination() {
        return image_destination;
    }

    public void setImage_destination(String image_destination) {
        this.image_destination = image_destination;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getNom_destination() {
        return nom_destination;
    }

    public void setNom_destination(String nom_destination) {
        this.nom_destination = nom_destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", nom_destination='" + nom_destination + '\'' +
                ", decription='" + decription + '\'' +
                ", image_destination='" + image_destination + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", temperature=" + temperature +
                ", rate=" + rate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destination that)) return false;
        return id == that.id && Double.compare(latitude, that.latitude) == 0 && Double.compare(longitude, that.longitude) == 0 && Double.compare(temperature, that.temperature) == 0 && Double.compare(rate, that.rate) == 0 && Objects.equals(nom_destination, that.nom_destination) && Objects.equals(decription, that.decription) && Objects.equals(image_destination, that.image_destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom_destination, decription, image_destination, latitude, longitude, temperature, rate);
    }
}
