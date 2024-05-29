package com.isep.tentative;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EpreuveTableViewManager {

    private static final Logger LOGGER = Logger.getLogger(EpreuveTableViewManager.class.getName());

    public void initializeTable(TableView<Epreuve> tableView,
                                TableColumn<Epreuve, Integer> idCol,
                                TableColumn<Epreuve, LocalDate> dateCol,
                                TableColumn<Epreuve, String> locationCol,
                                TableColumn<Epreuve, Discipline> discipCol,
                                TableColumn<Epreuve, String> nameCol,
                                TableColumn<Epreuve, Integer> discIDCol,
                                TableColumn<Epreuve, String> athleteIDListCol) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        discipCol.setCellValueFactory(new PropertyValueFactory<>("discipline"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        discIDCol.setCellValueFactory(new PropertyValueFactory<>("discipline_ID")); // Corrected property name
        athleteIDListCol.setCellValueFactory(new PropertyValueFactory<>("athlete_ID_List"));

        loadData(tableView);
    }

    private void loadData(TableView<Epreuve> tableView) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root")) {
            String sql = "SELECT * FROM \"Epreuve\"";
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet rs = statement.executeQuery()) {

                tableView.getItems().clear();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    LocalDate date = rs.getDate("date").toLocalDate();
                    String location = rs.getString("location");
                    Discipline discipline = Discipline.fromString(rs.getString("discipline"));
                    String name = rs.getString("Nom");
                    int discipline_ID = rs.getInt("Discipline_ID");
                    String athlete_ID_List = rs.getString("Athlete_ID_List");

                    Epreuve epreuve = new Epreuve(id, date, location, discipline, name, discipline_ID, athlete_ID_List);
                    tableView.getItems().add(epreuve);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error loading data from database", e);
        }
    }
}
