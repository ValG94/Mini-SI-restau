package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Facture {
    private int idFacture;
    private int numTable;
    private int idServeur;
    private String nomPlat;
    private Double prixHT;

    //Création de mon objet Facture
    public Facture(int numTable, int idServeur, String nomPlat, Double prixHT) {
        this.numTable = numTable;
        this.idServeur = idServeur;
        this.nomPlat = nomPlat;
        this.prixHT = prixHT;
    }

    public Facture(int newIdFacture, int newTable, int newIdServeur, String newNomTable, Double newPrixHT) {
        this.idFacture = newIdFacture;
        this.numTable = newTable;
        this.idServeur = newIdServeur;
        this.nomPlat = newNomTable;
        this.prixHT = newPrixHT;

    }


    @Override
    public String toString() {
        return "Facture{" + idFacture + ", Table n°=" + numTable + ", Serveur=" + idServeur; //+ "\t"+ "Plat commandé" + menu;
    }


    // Méthode pour enregistrer une nouvelle facture
    public void newFacture(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO Facture(id_facture, id_table_restaurant, id_serveur) VALUES ('" + idFacture + "'\t'" + numTable + "'\t'" + idServeur + "')");
        ordreSQL.close();
    }

    // insertion d'une nouvelle facture
    public static List<Facture> getFacture(Connection connection) throws SQLException {
        //Permet d'afficher la liste des factures
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT ligne_facture.id_facture, \"Facture\".id_table_restaurant, \"Facture\".id_serveur, plats.nom_plat, plats.\"prixHT\" " +
                "from \"Facture\" " +
                "inner join ligne_facture on \"Facture\".id_facture = ligne_facture.id_facture " +
                "inner join plats on ligne_facture.id_plat = plats.id_plat ");

        List<Facture> factureList = new ArrayList<>();

        while (resultats.next()) {
            Facture dbFacture = new Facture(resultats.getInt("id_facture"),
                    resultats.getInt("id_table_restaurant"),
                    resultats.getInt("id_serveur"),
                    resultats.getString("nom_plat"),
                    resultats.getDouble("prixHT"));

            factureList.add(dbFacture);

            System.out.println(dbFacture);
        }
        resultats.close();
        ordreSQL.close();
        return factureList;
    }


    }

