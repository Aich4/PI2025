package org.example;

import models.Reclamation;
import models.Reponse;
import services.ReponseServices;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Initialisation des services
        ReclamationService reclamationService = new ReclamationService();
        ReponseService reponseService = new ReponseService();

        try {
            // Création et ajout d'une réclamation
            Reclamation reclamation = new Reclamation(5, "F", typeR.Coach, 2, 2, new Date());

            //reclamationService.create(reclamation);
            // System.out.println("Réclamation ajoutée : " + reclamation);

            // System.out.println("\n=== MODIFICATION D'UNE RÉCLAMATION ===");
            //reclamation.setDescription("Hamza.");
            // reclamationService.update(reclamation);
            // System.out.println("Réclamation mise à jour avec succès !");

            // System.out.println("\n=== SUPPRESSION D'UNE RÉCLAMATION ===");
            // reclamationService.delete(reclamation.getIdReclamation());
            //  System.out.println("Réclamation supprimée avec succès !");


            // System.out.println("\n=== AJOUT DE RÉPONSES ===");
            Reponse reponse1 = new Reponse(1, reclamation.getIdReclamation(), new Date(), "Nous avons résolu votre problème de connexion.");
            Reponse reponse2 = new Reponse(2, reclamation.getIdReclamation(), new Date(), "Merci pour votre patience.");

            // reponseService.create(reponse1);
            // reponseService.create(reponse2);
            //  System.out.println("Réponses ajoutées avec succès !");

            System.out.println("\n=== SUPPRESSION D'UNE RÉPONSE ===");
            reponseService.delete(reponse1.getId());
            System.out.println("Réponse supprimée avec succès !");



            // Vous pouvez ajouter d'autres tests ici comme l'ajout de réponses ou la récupération des objets
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
