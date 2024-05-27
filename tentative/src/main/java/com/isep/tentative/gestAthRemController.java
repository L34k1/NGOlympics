package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.isep.tentative.HelloApplication.mainStage;

public class gestAthRemController {

    @FXML
    private Button bGestAthRemReturn;

    @FXML
    private Button bGestAthRemRemove;

    @FXML
    private TableView<Athlete> gestAthRemTableview;

    @FXML
    private TableColumn<Athlete, Integer> gestAthRemIDcol;

    @FXML
    private TableColumn<Athlete, String> gestAthRemNomCol;

    @FXML
    private TableColumn<Athlete, Boolean> gestAthRemSexeCol;

    @FXML
    private TableColumn<Athlete, String> gestAthRemPaysCol;

    @FXML
    private TableColumn<Athlete, String> gestAthRemBirthdateCol;

    @FXML
    private TextField gestAthRemIDfield;

    @FXML
    private Label gestAthRemIDLabel;

    // Method to initialize the controller
    public void initialize() {
        // Set up cell value factories for table columns
        gestAthRemIDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        gestAthRemNomCol.setCellValueFactory(new PropertyValueFactory<>("name")); // Check if the property name is "nom" instead of "Nom"
        gestAthRemSexeCol.setCellValueFactory(new PropertyValueFactory<>("gender")); // Check if the property name is "sexe" instead of "Sexe"
        gestAthRemPaysCol.setCellValueFactory(new PropertyValueFactory<>("country")); // Check if the property name is "pays" instead of "Pays"
        gestAthRemBirthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate")); // Check if the property name is "dateOfBirth" instead of "Date de naissance"

        // Load data into the table
        loadData();
    }

    // Method to load data into the table
    private void loadData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "SELECT * FROM \"Athlete\"";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            // Clear existing data in the table
            gestAthRemTableview.getItems().clear();

            // Populate table with data from the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Nom");
                boolean gender = rs.getBoolean("Sexe");
                String country = rs.getString("Pays");
                LocalDate birthdate = rs.getDate("Date de naissance").toLocalDate();

                Athlete athlete = new Athlete(id, name, gender, country, birthdate);
                gestAthRemTableview.getItems().add(athlete);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }

    // Method to handle return button click
    @FXML
    protected void onBGestAthRemReturnButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestAth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Remove Athlete");
        mainStage.setScene(scene);
    }

    // Method to handle remove button click
    @FXML
    protected void onBGestAthRemRemoveButtonClick() {
        // Implement action for remove button
        String idToRemove = gestAthRemIDfield.getText();
        if (!idToRemove.isEmpty()) {
            removeAthlete(Integer.parseInt(idToRemove));
        }
    }
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    // Method to load data into the table

    // Method to remove an athlete from the database
    private void removeAthlete(int id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "DELETE FROM \"Athlete\" WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Athlete with ID " + id + " has been removed from the database.");
                // Optionally, update the table view after deletion
                loadData();
            } else {
                System.out.println("No athlete found with ID " + id);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
    }
}
