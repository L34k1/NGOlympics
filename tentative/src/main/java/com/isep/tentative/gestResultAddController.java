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

import static com.isep.tentative.HelloApplication.mainStage;

public class gestResultAddController {

    @FXML
    private TextField fieldMedaille;

    @FXML
    private TextField fieldValidation;

    @FXML
    private TextField fieldAthleteID;

    @FXML
    private TextField fieldScore;

    @FXML
    private TextField fieldEpreuveID;

    @FXML
    private Button bConfAddResult;

    @FXML
    private Button bConfAddResult1;

    @FXML
    private Button bConfAddResult2;

    @FXML
    protected void onbConfAddResultButtonClick() {
        String medaille = fieldMedaille.getText();
        String validation = fieldValidation.getText();
        Integer athlete_id = Integer.valueOf(fieldAthleteID.getText());
        String score = fieldScore.getText();
        Integer epreuve_id = Integer.valueOf(fieldEpreuveID.getText());
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "INSERT INTO \"Resultat\" (\"Médaille\", \"Validation\", \"Athlete_ID\", \"Score\", \"Epreuve_ID\") VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, medaille);
            statement.setBoolean(2, Boolean.parseBoolean(validation));
            statement.setInt(3, athlete_id);
            statement.setString(4, score);
            statement.setInt(5, epreuve_id);
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onbConfAddResult1ButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestRes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 900);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbConfAddResult2ButtonClick() {
        // Clear all text fields
        fieldMedaille.clear();
        fieldValidation.clear();
    }
}
