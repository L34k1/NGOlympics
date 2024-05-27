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

import static com.isep.tentative.HelloApplication.mainStage;

public class gestAthAddController {

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldGender;

    @FXML
    private TextField fieldCountry;

    @FXML
    private TextField fieldBirthdate;

    @FXML
    private Button bConfAddAth;

    @FXML
    private Button bConfAddAth1;

    @FXML
    private Button bConfAddAth2;

    // Method to handle insertion of athlete data into the database
    @FXML
    protected void onbConfAddAthButtonClick() {
        // Get input values from text fields
        String name = fieldName.getText();
        boolean gender = Integer.parseInt(fieldGender.getText()) == 1; // Convert to boolean
        String country = fieldCountry.getText();
        LocalDate birthdate = LocalDate.parse(fieldBirthdate.getText()); // Parse birthdate string to LocalDate

        // Insert the data into the database
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "INSERT INTO \"Athlete\" (\"Nom\", \"Sexe\", \"Pays\", \"Date de naissance\") VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setBoolean(2, gender);
            statement.setString(3, country);
            statement.setObject(4, birthdate); // Use setObject to set LocalDate
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }

    // Method to handle cancel button click
    @FXML
    protected void onbConfAddAth1ButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestAth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 900);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    // Method to handle clear button click
    @FXML
    protected void onbConfAddAth2ButtonClick() {
        // Clear all text fields
        fieldName.clear();
        fieldGender.clear();
        fieldCountry.clear();
        fieldBirthdate.clear();
    }
}
