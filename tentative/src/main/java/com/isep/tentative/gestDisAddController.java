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

public class gestDisAddController {

    @FXML
    private TextField fieldName;

    @FXML
    private Button bConfAddAth;

    @FXML
    private Button bConfAddAth1;

    @FXML
    private Button bConfAddAth2;

    @FXML
    protected void onbConfAddAthButtonClick() {
        String name = fieldName.getText();

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "INSERT INTO \"Discipline\" (\"Nom\") VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onbConfAddAth1ButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestDis.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 900);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbConfAddAth2ButtonClick() {
        fieldName.clear();
    }
}
