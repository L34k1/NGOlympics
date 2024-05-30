package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.isep.tentative.HelloApplication.mainStage;

public class gestEpreuveAddController {

    @FXML
    private TextField fieldDate;

    @FXML
    private TextField fieldLocation;

    @FXML
    private TextField fieldDiscipline;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldDisciplineID;

    @FXML
    private TextField fieldAthleteIDList;


    @FXML
    private Button bConfAddEpreuve;

    @FXML
    private Button bConfAddEpreuve1;

    @FXML
    private Button bConfAddEpreuve2;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    protected void onbConfAddEpreuveButtonClick() {

        String dateStr = fieldDate.getText();
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        String location = fieldLocation.getText();
        String discipline = fieldDiscipline.getText();
        String name = fieldName.getText();
        String disciplineID = fieldDisciplineID.getText();
        String athleteIDList = fieldAthleteIDList.getText();

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "INSERT INTO \"Epreuve\" (\"Date\", \"Location\", \"Discipline\", \"nom\", \"Discipline_ID\",\"Athlete_ID_List\") VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, sqlDate);
            statement.setString(2, location);
            statement.setString(3, discipline);
            statement.setString(4, name);
            statement.setInt(5, Integer.parseInt(disciplineID));
            statement.setString(6, athleteIDList);
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }

    @FXML
    protected void onbConfAddEpreuve1ButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestEp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbConfAddEpreuve2ButtonClick() {
        // Clear all text fields
        fieldDate.clear();
        fieldLocation.clear();
        fieldDiscipline.clear();
        fieldName.clear();
        fieldDisciplineID.clear();
        fieldAthleteIDList.clear();
    }
}