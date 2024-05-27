package com.isep.tentative;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSql {

    public static void writeToDatabase(String IDUtil, String NomUtil, String SexeUtil, String PaysUtil, String DateUtil) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "root";

        String ID = IDUtil;
        String Nom = NomUtil;
        String Sexe = SexeUtil;
        String Pays = PaysUtil;
        String Date_De_Naissance = DateUtil;


        // query
        String query = "INSERT INTO Athlète(ID, Nom, Sexe, Pays, Date_De_Naissance) VALUES(?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, Integer.parseInt(ID));
            pst.setString(2, Nom);
            pst.setBoolean(3, Boolean.parseBoolean(Sexe));
            pst.setString(4, Pays);
            pst.setDate(5, Date.valueOf(Date_De_Naissance));
            pst.executeUpdate();
            System.out.println("Sucessfully created.");

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JavaPostgreSql.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }
}
