package org.example;

import models.Mission;
import models.Recompense;
import services.MissionService;
import services.RecompenseService;

public class Main {
    public static void main(String[] args) {
        //entite mission
        MissionService m1 = new MissionService();
        Mission mis = new Mission(1,"mission1000",300,"dispo");
        try {
            m1.create(mis);
            m1.update(mis);
            System.out.println(m1.getAll());
            m1.delete(2);
            System.out.println(m1.getAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //entite recompense
        RecompenseService r1 = new RecompenseService();
        Recompense rec = new Recompense(2,"mission2000",300,"dispo");
        try {
            r1.create(rec);
            r1.update(rec);
            System.out.println(r1.getAll());
            r1.delete(2);
            System.out.println(r1.getAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}