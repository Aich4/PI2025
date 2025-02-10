package tn.esprit.test;

import tn.esprit.entities.Pack;
import tn.esprit.services.ServicePack;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ServicePack sp = new ServicePack();

        try {
            // Ajouter un pack
             //sp.create(new Pack(1, "Pack Premium", "Accès exclusif à toutes les activités", 150.0f, 30, "Réductions, Guides privés", "actif"));
            // System.out.println("Pack ajouté !");

            // Modifier un pack
             //sp.update(new Pack(1, "Pack Gold", "Accès VIP", 200.0f, 60, "Réductions spéciales, Accès premium", "actif"));
           //System.out.println("Pack modifié !");

            // Supprimer un pack
             sp.delete(1);
            System.out.println("Pack supprimé !");

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
        }
    }
}
