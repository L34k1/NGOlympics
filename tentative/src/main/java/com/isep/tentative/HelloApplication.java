package com.isep.tentative;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloApplication extends Application {

    public static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        Connection connection = null;
        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");

            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connection failed");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setScene(scene);
        mainStage=stage;
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
