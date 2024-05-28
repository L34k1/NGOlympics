package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class gestAthModController {

    @FXML
    private TableView<Athlete> table;

    @FXML
    private TableColumn<Athlete, Integer> IDcol;

    @FXML
    private TableColumn<Athlete, String> nomCol;

    @FXML
    private TableColumn<Athlete, Boolean> sexeCol;

    @FXML
    private TableColumn<Athlete, String> paysCol;

    @FXML
    private TableColumn<Athlete, String> dobCol;

    @FXML
    private Button bReturn;

    @FXML
    private Button bMod;

    @FXML
    private Button bClear;

    @FXML
    private TextField fieldNewName;

    @FXML
    private TextField fieldNewSex;

    @FXML
    private TextField fieldNewCountry;

    @FXML
    private TextField fieldNewDoB;

    @FXML
    private TextField fieldIDselector;

    @FXML
    public void initialize() {
        IDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sexeCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        paysCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        dobCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));

        loadData();
    }

    private void loadData() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "SELECT * FROM \"Athlete\"";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            table.getItems().clear();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("Nom");
                boolean sexe = rs.getBoolean("Sexe");
                String pays = rs.getString("Pays");
                String dob = rs.getString("Date de naissance");

                Athlete athlete = new Athlete(id, nom, sexe, pays, LocalDate.parse(dob));
                table.getItems().add(athlete);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBModClick() {
        String idText = fieldIDselector.getText();
        String newName = fieldNewName.getText();
        String newSex = fieldNewSex.getText();
        String newCountry = fieldNewCountry.getText();
        String newDob = fieldNewDoB.getText();

        if (idText.isEmpty() || newName.isEmpty() || newSex.isEmpty() || newCountry.isEmpty() || newDob.isEmpty()) {
            System.out.println("Please fill all fields before modifying.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            boolean gender = Boolean.parseBoolean(newSex);
            LocalDate dob = LocalDate.parse(newDob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "UPDATE \"Athlete\" SET \"Nom\" = ?, \"Sexe\" = ?, \"Pays\" = ?, \"Date de naissance\" = ?::date WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setBoolean(2, gender);
            statement.setString(3, newCountry);
            statement.setDate(4, java.sql.Date.valueOf(dob));
            statement.setInt(5, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Athlete with ID " + id + " has been updated.");
                loadData();
            } else {
                System.out.println("No athlete found with ID " + id);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBClearClick() {
        fieldNewName.clear();
        fieldNewSex.clear();
        fieldNewCountry.clear();
        fieldNewDoB.clear();
        fieldIDselector.clear();
    }

    @FXML
    private void onBReturnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestAth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Return");
        mainStage.setScene(scene);
    }
}
