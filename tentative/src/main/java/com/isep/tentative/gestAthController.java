package com.isep.tentative;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.isep.tentative.HelloApplication.mainStage;

public class gestAthController {

    @FXML
    private Button retourMainMenu;

    @FXML
    private TableView<Athlete> tableViewAth;

    @FXML
    private TableColumn<Athlete, Integer> gestAthIDcol;

    @FXML
    private TableColumn<Athlete, String> gestAthNomCol;

    @FXML
    private TableColumn<Athlete, Boolean> gestAthSexeCol;

    @FXML
    private TableColumn<Athlete, String> gestAthPaysCol;

    @FXML
    private TableColumn<Athlete, String> gestAthBirthdateCol;

    @FXML
    public void initialize() {
        // Assuming you already have an established database connection
        try {
            // Prepare SQL query
            String query = "SELECT * FROM \"Athlete\"";
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Execute query and retrieve result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Clear existing items in the TableView
            tableViewAth.getItems().clear();

            // Iterate over result set and populate TableView
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Nom");
                boolean gender = resultSet.getBoolean("Sexe");
                String country = resultSet.getString("Pays");
                LocalDate dob = resultSet.getDate("Date de naissance").toLocalDate();

                // Add retrieved data to the TableView
                tableViewAth.getItems().add(new Athlete(id, name, gender, country, dob));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set cell value factories for each column
        gestAthIDcol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        gestAthNomCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        gestAthSexeCol.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        gestAthPaysCol.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        gestAthBirthdateCol.setCellValueFactory(cellData -> cellData.getValue().formattedDobProperty());
    }

    @FXML
    protected void onbRetourMainMenuButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 900);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }
    @FXML
    protected void onbAddAthButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestAthAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Add Athlete");
        mainStage.setScene(scene);
    }
    @FXML
    protected void onbModAthButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestAthMod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Add Athlete");
        mainStage.setScene(scene);
    }
    @FXML
    protected void onbRemAthButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestAthRem.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Remove Athlete");
        mainStage.setScene(scene);
    }
}
