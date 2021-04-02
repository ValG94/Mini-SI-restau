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
//private List<Plats> menu;

//Création de mon objet Facture
    public Facture( int numTable, int idServeur, List<Plats> menu) {
    this.numTable = numTable;
    this.idServeur = idServeur;
    //this.menu = menu;
    }

    public Facture(int newIdFacture, int newTable, int newIdServeur) {
        this.idFacture = newIdFacture;
        this.numTable = newTable;
        this.idServeur = newIdServeur;

    }


    @Override
    public String toString() {
        return "Facture{" + idFacture + ", Table n°=" + numTable + ", Serveur=" + idServeur; //+ "\t"+ "Plat commandé" + menu;
    }


    // Méthode pour enregistrer une nouvelle facture
    public void newFacture(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO Facture(id_facture, id_table_restaurant, id_serveur) VALUES ('" + idFacture + "'\t'" + numTable + "'\t'"+ idServeur+"')");
        ordreSQL.close();
    }

    // insertion d'une nouvelle facture
    public static List<Facture> getFacture(Connection connection) throws SQLException {
        //Permet d'afficher la liste des factures
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT id_facture, id_table_restaurant, id_serveur from Facture ");

        List<Facture> factureList = new ArrayList<>();

        while (resultats.next()) {
            Facture dbFacture = new Facture(resultats.getInt("id_facture"),
                    resultats.getInt("id_table_restaurant"),
                    resultats.getInt("id_serveur"));

            factureList.add(dbFacture);

            System.out.println(dbFacture);
        }
        resultats.close();
        ordreSQL.close();
        return factureList;
    }

}
