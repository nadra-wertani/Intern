package test;

import Models.sujet_stage;
import Services.Sujetstageservices;

import java.sql.SQLException;

public class teting {
    public static void main(String[] args) {
       // sujet_stage p = new sujet_stage(2,1,1,"update22","tecup22","forup22","22h");

        Sujetstageservices sp = new Sujetstageservices();
       // try {
        // Appeler la méthode update avec l'instance de sujet_stage et l'identifiant
       // sp.update(p, 2);
         //System.out.println("Update réussie.");
        // }
        //catch (SQLException e) {
       // System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
        // }
        sp.getAll();
        }
    }

