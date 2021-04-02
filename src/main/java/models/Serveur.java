package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
    private int id;
    private String prenom;
    private String nom;

    // définition d'un serveur = définir mon objet
    public Serveur(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public Serveur(int newId, String newPrenom, String newNom) {
        id = newId;
        prenom = newPrenom;
        nom = newNom;

    }

    @Override
    public String toString() {
        return id + "." + prenom + " " + nom;
    }

    // Méthode pour enregistrer un nouveau serveur
    public void nouveauServeur(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO Serveurs(prenom_serveur, nom_serveur) VALUES ('" + prenom + "','" + nom + "')");
        ordreSQL.close();
    }

    // insertion donnée = créer un nouveau serveur
    public static List<Serveur> getServeur(Connection connection) throws SQLException {
        //Permet d'afficher la liste des serveurs
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT id_serveurs, nom_serveur, prenom_serveur from Serveurs");

        List<Serveur> serveurList = new ArrayList<>();

        while (resultats.next()) {
            Serveur dbServeurs = new Serveur(resultats.getInt("id_serveurs"),
                    resultats.getString("prenom_serveur"),
                    resultats.getString("nom_serveur"));
            serveurList.add(dbServeurs);

            System.out.println(dbServeurs);
        }
        resultats.close();
        ordreSQL.close();
        return serveurList;

    }


    // modifier un serveur (update)

    // supprimer un serveur (delete)
}
