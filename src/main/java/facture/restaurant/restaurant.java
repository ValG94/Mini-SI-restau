package facture.restaurant;

import models.Facture;
import models.Plats;
import models.Serveur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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


        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Plats.getPlats(connection);
            Serveur.getServeur(connection);
            Facture.getFacture(connection);

            //Utilisation d'un Switch pour afficher le menu
            System.out.println("Afficher le menu des plats :");
            int m = sc.nextInt();
            //pour pouvoir appeler le menu de mes plats
            switch (m) {
                case 1: {
                    Plats.getPlats(connection);
                    break;
                }

                default:
                    System.out.println("Erreur du menu");
            }

            //Ajouter un nouveau plat et son prix
            System.out.println("Saisir un nouveau plat");
            String newNomPlat = sc.nextLine();
            System.out.print("Quel est le prix du plat ? ");
            double prixHT = sc.nextDouble();

            Plats nouveauPlat = new Plats(newNomPlat, prixHT);
            nouveauPlat.newPlat(connection);

            // Ajouter un nouveau Serveur
            System.out.println("Saisir un nouveau Serveur");
            int newIdServeur = sc.nextInt();
            System.out.println("Quel est le prenom du nouveau serveur ?");
            String prenom = sc.nextLine();
            System.out.println("Quel est le Nom du nouveau serveur ? ");
            String nom = sc.nextLine();

            Serveur nouveauServeur = new Serveur(newIdServeur, prenom, nom);
            nouveauServeur.nouveauServeur(connection);

            // Ajouter une nouvelle facture
            System.out.println("Nouvelle facture");
            int newIdFacture = sc.nextInt();
            System.out.println("Quel est le numéro de la table ?");
            int newTable = sc.nextInt();
            System.out.println("Quel est le prenom du serveur ?");
            int IdServeur = sc.nextInt();
            System.out.println("Quels sont les plats commandés ?");
            //List<Plats> platCommande=new ArrayList<Plats>();// A TERMINER

            Facture newFacture = new Facture(newIdFacture, newTable, IdServeur);
            newFacture.newFacture(connection);


            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }


    }
}