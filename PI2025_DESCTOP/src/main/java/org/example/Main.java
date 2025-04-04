/*package org.example;

import models.Partenaire;
import services.PartenaireService;

import java.text.ParseException;
import java.sql.Date;

public class Main {
    public static void main(String[] args) throws ParseException {


        Date date = new Date(125,5,14);
        Partenaire partenaire = new Partenaire("ghalia","aaa","aaa","aaaa",date);
        PartenaireService partenaireService = new PartenaireService();
        try {
            partenaireService.create(partenaire);
            System.out.println("succesfully created");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}*/