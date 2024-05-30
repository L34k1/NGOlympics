package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.isep.tentative.HelloApplication.mainStage;

public class gestResultModController {

    @FXML
    private TableView<Resultat> table;

    @FXML
    private TableColumn<Resultat, Integer> IDcol;

    @FXML
    private TableColumn<Resultat, Character> MedailleCol;

    @FXML
    private TableColumn<Resultat, Boolean> ValidationCol;

    @FXML
    private TableColumn<Resultat, Integer> athleteIDcol;

    @FXML
    private TableColumn<Resultat, Integer> epreuveIDcol;

    @FXML
    private TableColumn<Resultat, String> scoreCol;

    @FXML
    private Button bReturn;

    @FXML
    private Button bMod;

    @FXML
    private Button bClear;

    @FXML
    private TextField fieldNewMedaille;

    @FXML
    private TextField fieldNewValidation;

    @FXML
    private TextField fieldIDselector;

    @FXML
    private TextField fieldNewAthleteID;

    @FXML
    private TextField fieldNewEpreuveID;

    @FXML
    private TextField fieldNewScore;

    @FXML
    public void initialize() {
        ResultTableViewManager tableViewManager = new ResultTableViewManager();
        tableViewManager.initializeTable(table, IDcol, MedailleCol, ValidationCol, athleteIDcol, epreuveIDcol, scoreCol);
    }

    @FXML
    private void onBModClick() {
        String idText = fieldIDselector.getText();
        String newMedaille = fieldNewMedaille.getText();
        String newValidation = fieldNewValidation.getText();
        String idAth = fieldNewAthleteID.getText();
        String idEp = fieldNewEpreuveID.getText();
        String Score = fieldNewScore.getText();

        if (idText.isEmpty() || newMedaille.isEmpty() || newValidation.isEmpty()) {
            System.out.println("Please fill all fields before modifying.");
            return;
        }

        if (newMedaille.length() != 1) {
            System.out.println("Please enter a single character for the Medaille.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            char medailleChar = newMedaille.charAt(0);
            boolean validation = Boolean.parseBoolean(newValidation);
            int idNewAth = Integer.parseInt(idAth);
            int idNewEp = Integer.parseInt(idEp);
            String score = String.valueOf(Score);

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "UPDATE \"Resultat\" SET \"Médaille\" = ?, \"Validation\" = ?, \"Athlete_ID\" = ?, \"Score\" = ?, \"Epreuve_ID\" = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, String.valueOf(medailleChar)); // Set the value for the first parameter (Medaille)
            statement.setBoolean(2, validation); // Set the value for the second parameter (Validation)
            statement.setInt(3, idNewAth); // Set the value for the third parameter (Athlete ID)
            statement.setString(4, score); // Set the value for the fifth parameter (Score)
            statement.setInt(5, idNewEp); // Set the value for the fourth parameter (Epreuve ID)
            statement.setInt(6, id); // Set the value for the sixth parameter (ID)

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Result with ID " + id + " has been updated.");
                ResultTableViewManager tableViewManager = new ResultTableViewManager();
                tableViewManager.initializeTable(table, IDcol, MedailleCol, ValidationCol, athleteIDcol, epreuveIDcol, scoreCol);
            } else {
                System.out.println("No Result found with ID " + id);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBClearClick() {
        fieldNewMedaille.clear();
        fieldNewValidation.clear();
        fieldIDselector.clear();
    }

    @FXML
    private void onBReturnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestRes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        mainStage.setTitle("Return");
        mainStage.setScene(scene);
    }
}
