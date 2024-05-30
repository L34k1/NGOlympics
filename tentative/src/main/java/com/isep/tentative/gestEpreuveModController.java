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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.isep.tentative.HelloApplication.mainStage;

public class gestEpreuveModController {

    @FXML
    private TableView<Epreuve> tableViewEp;

    @FXML
    private TableColumn<Epreuve, Integer> gestEpIDcol;

    @FXML
    private TableColumn<Epreuve, LocalDate> gestEpDateCol;

    @FXML
    private TableColumn<Epreuve, String> gestEpLocationCol;

    @FXML
    private TableColumn<Epreuve, Discipline> gestEpDisciplineCol;

    @FXML
    private TableColumn<Epreuve, String> gestEpNameCol;

    @FXML
    private TableColumn<Epreuve, Integer> gestEpDiscIDCol;

    @FXML
    private TableColumn<Epreuve, String> gestEpAthleteIDListCol;

    @FXML
    private Button bReturn;

    @FXML
    private Button bMod;

    @FXML
    private Button bClear;

    @FXML
    private TextField fieldNewDate;

    @FXML
    private TextField fieldNewLocation;

    @FXML
    private TextField fieldNewDiscipline;

    @FXML
    private TextField fieldNewName;

    @FXML
    private TextField fieldNewDisciplineID;

    @FXML
    private TextField fieldNewAthleteIDList;

    @FXML
    private TextField fieldIDselector;

    @FXML
    public void initialize() {
        EpreuveTableViewManager tableViewManager = new EpreuveTableViewManager();
        tableViewManager.initializeTable(tableViewEp, gestEpIDcol, gestEpDateCol, gestEpLocationCol, gestEpDisciplineCol, gestEpNameCol, gestEpDiscIDCol, gestEpAthleteIDListCol);
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    private void onBModClick() {
        String dateStr = fieldNewDate.getText();
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
        String location = fieldNewLocation.getText();
        String discipline = fieldNewDiscipline.getText();
        String name = fieldNewName.getText();
        String disciplineID = fieldNewDisciplineID.getText();
        String athleteIDList = fieldNewAthleteIDList.getText();
        String idEpreuve = fieldIDselector.getText();

        if (idEpreuve.isEmpty() || dateStr.isEmpty() || location.isEmpty() || discipline.isEmpty() || name.isEmpty() || disciplineID.isEmpty() || athleteIDList.isEmpty()) {
            System.out.println("Please fill all fields before modifying.");
            return;
        }

        try {
            int id = Integer.parseInt(idEpreuve);
            LocalDate newDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "UPDATE \"Epreuve\" SET \"Date\" = ?, \"Location\" = ?, \"Discipline\" = ?, \"nom\" = ?, \"Discipline_ID\" = ?,\"Athlete_ID_List\" = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, sqlDate);
            statement.setString(2, location);
            statement.setString(3, discipline);
            statement.setString(4, name);
            statement.setInt(5, Integer.parseInt(disciplineID));
            statement.setString(6, athleteIDList);
            statement.setObject(7, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Epreuve with ID " + id + " has been updated.");
                EpreuveTableViewManager tableViewManager = new EpreuveTableViewManager();
                tableViewManager.initializeTable(tableViewEp, gestEpIDcol, gestEpDateCol, gestEpLocationCol, gestEpDisciplineCol, gestEpNameCol, gestEpDiscIDCol, gestEpAthleteIDListCol);
            } else {
                System.out.println("No Epreuve found with ID " + id);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBClearClick() {
        fieldNewDate.clear();
        fieldNewLocation.clear();
        fieldNewDiscipline.clear();
        fieldNewName.clear();
        fieldNewDisciplineID.clear();
        fieldNewAthleteIDList.clear();
    }

    @FXML
    private void onBReturnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestEp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        mainStage.setTitle("Return");
        mainStage.setScene(scene);
    }
}