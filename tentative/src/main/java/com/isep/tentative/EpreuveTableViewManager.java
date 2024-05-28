package com.isep.tentative;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;

public class EpreuveTableViewManager {

    public void initializeTable(TableView<Epreuve> tableView,
                                TableColumn<Epreuve, Integer> idCol,
                                TableColumn<Epreuve, LocalDate> dateCol,
                                TableColumn<Epreuve, String> locationCol,
                                TableColumn<Epreuve, Discipline> discipCol,
                                TableColumn<Epreuve, String> nameCol
                                ) {
        // Set up cell value factories for table columns
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date")); // Adjust property name if necessary
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location")); // Adjust property name if necessary
        discipCol.setCellValueFactory(new PropertyValueFactory<>("discipline")); // Adjust property name if necessary
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name")); // Adjust property name if necessary


        // Load data into the table
        loadData(tableView);
    }

    // Method to load data into the table
    private void loadData(TableView<Epreuve> tableView) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "SELECT * FROM \"Epreuve\"";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            // Clear existing data in the table
            tableView.getItems().clear();

            // Populate table with data from the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate date = rs.getDate("Date").toLocalDate();
                String location = rs.getString("Location");
                Discipline discipline = Discipline.fromString(rs.getString("Discipline"));
                Epreuve epreuve = new Epreuve(id, date, location, discipline);
                tableView.getItems().add(epreuve);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

