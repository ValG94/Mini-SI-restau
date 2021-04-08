package facture.restaurant;

import models.Facture;
import models.Plats;
import models.Serveur;
import java.sql.*;
import java.util.Scanner;

public class restaurant {

    private static Connection connection;


    public static void main(String[] menu) {

        //le lancement de mon programme se fera ici
        // Attention, ce code est un exemple pour aller vite, mais il convient de noter qu'il n'est pas sécurisé de laisser un mot de passe en clair dans un fichier Java. Nous verrons ensemble comment gérer ça plus proprement dans la suite de la formation.
        String url = "jdbc:postgresql://localhost:5432/restaurant";
        String user = "postgres";
        String password = "postgres";
        Scanner sc = new Scanner(System.in);
        boolean sortir = false;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            while (sortir==false){



                System.out.println("__________________Menu du Programme_______________");
                System.out.println("1. Menu des plats" + '\n' + "2. Les serveurs" + '\n' + "3. Les factures"+ '\n' + "4. Exit");
                System.out.println("__________________________________________________");
                System.out.print("Faites votre choix : " );
                int m = sc.nextInt();
                sc.nextLine();

                //Utilisation d'un Switch afin de pouvoir appeler le menu de mes plats
                switch (m) {
                    case 1: {
                        System.out.println("Menu");
                        Plats.getPlats(connection);

                        break;}
                    case 2: {
                        System.out.println("Les serveurs");
                        Serveur.getServeur(connection);

                        break;
                    }
                    case 3:{
                        System.out.println("Les factures");
                        Facture.getFacture(connection);
                        break;
                    }
                    case 4:{
                        System.out.println("Exit");
                        sortir = true;
                        break;
                    }
                    default:
                        System.out.println("Erreur du menu");
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}