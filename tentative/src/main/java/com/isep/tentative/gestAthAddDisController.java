package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.isep.tentative.HelloApplication.mainStage;

public class gestAthAddDisController {

    @FXML
    private TextField fieldAthID;

    @FXML
    private TextField fieldDisID;

    @FXML
    protected void onbConfAddAth1ButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestAth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbConfAddAthButtonClick() {
        String athID = fieldAthID.getText();
        String disID = fieldDisID.getText();

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "root";

        String updateQuery = "UPDATE \"Athlete\" SET \"Discipline_ID\" = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setInt(1, Integer.parseInt(disID));
            pstmt.setInt(2, Integer.parseInt(athID));
            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("An existing athlete's discipline was updated successfully.");
            } else {
                System.out.println("No athlete found with the provided ID.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onbConfAddAth2ButtonClick() {
        // Clear all text fields
        fieldAthID.clear();
        fieldDisID.clear();
    }
}
