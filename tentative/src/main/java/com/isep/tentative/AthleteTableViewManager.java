package com.isep.tentative;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AthleteTableViewManager {

    public void initializeTable(TableView<Athlete> tableView,
                                TableColumn<Athlete, Integer> idCol,
                                TableColumn<Athlete, String> nameCol,
                                TableColumn<Athlete, Boolean> genderCol,
                                TableColumn<Athlete, String> countryCol,
                                TableColumn<Athlete, LocalDate> birthdateCol,
                                TableColumn<Athlete, Integer> gestAthDiscIDCol) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        gestAthDiscIDCol.setCellValueFactory(new PropertyValueFactory<>("discipline_ID"));

        loadData(tableView);
    }


    private void loadData(TableView<Athlete> tableView) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "SELECT id, \"Nom\", \"Sexe\", \"Pays\", \"Date de naissance\", \"Discipline_ID\" FROM \"Athlete\"";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            tableView.getItems().clear();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Nom");
                boolean gender = rs.getBoolean("Sexe");
                String country = rs.getString("Pays");
                LocalDate birthdate = rs.getDate("Date de naissance").toLocalDate();
                int discipline_ID = rs.getInt("Discipline_ID");

                Athlete athlete = new Athlete(id, name, gender, country, birthdate, discipline_ID);
                tableView.getItems().add(athlete);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}