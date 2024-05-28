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
    private Button bConfAddResult;

    @FXML
    private Button bConfAddResult1;

    @FXML
    private Button bConfAddResult2;

    // Method to handle insertion of result data into the database
    @FXML
    protected void onbConfAddResultButtonClick() {
        // Get input values from text fields
        String medaille = fieldMedaille.getText();
        String validation = fieldValidation.getText();

        // Insert the data into the database
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "INSERT INTO \"Resultat\" (\"Médaille\", \"Validation\") VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, medaille);
            statement.setBoolean(2, Boolean.parseBoolean(validation));
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }

    // Method to handle cancel button click
    @FXML
    protected void onbConfAddResult1ButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestResult.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 900);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    // Method to handle clear button click
    @FXML
    protected void onbConfAddResult2ButtonClick() {
        // Clear all text fields
        fieldMedaille.clear();
        fieldValidation.clear();
    }
}
