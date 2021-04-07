package models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Plats {
private int idPlat;
private String nomPlat;
private Double prixHT;

    //Définir mes objets plats
    public Plats(String nomPlat, Double prixHT) {
        this.nomPlat = nomPlat;
        this.prixHT = prixHT;
    }

    public Plats(int newIdPlat, String newNomPlat, Double newPrixHT) {
        idPlat = newIdPlat;
        nomPlat = newNomPlat;
        prixHT = newPrixHT;
    }

    @Override
    public String toString() {
        return idPlat + "-" + nomPlat + "\t" + prixHT + "€";
    }

    // Méthode pour enregistrer un nouveau plat
    public void newPlat(Connection connection) throws SQLException {
        Statement ordreSQL = connection.createStatement();
        ordreSQL.execute("INSERT INTO plats(nom_plat, \"prixHT\") VALUES ('" + nomPlat + "','" + prixHT + "')");
        ordreSQL.close();
    }

    // insertion d'un nouveau plat (insert into)
    public static List<Plats> getPlats(Connection connection) throws SQLException {
        //Permet d'afficher la liste des plats
        Statement ordreSQL = connection.createStatement();
        ResultSet resultats = ordreSQL.executeQuery("SELECT id_plat, nom_plat, \"prixHT\" from plats");

        List<Plats> platsList = new ArrayList<>();

        while (resultats.next()) {
            Plats dbplats = new Plats(resultats.getInt("id_plat"),
                    resultats.getString("nom_plat"),
                    resultats.getDouble("prixHT"));
            platsList.add(dbplats);

            System.out.println(dbplats);
        }
        resultats.close();
        ordreSQL.close();
        return platsList;
    }
}
