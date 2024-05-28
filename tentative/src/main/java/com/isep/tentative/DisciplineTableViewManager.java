package com.isep.tentative;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;

public class DisciplineTableViewManager {

    public void initializeTable(TableView<Discipline> tableView,
                                TableColumn<Discipline, Integer> idCol,
                                TableColumn<Discipline, String> nameCol) {
        // Set up cell value factories for table columns
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name")); // Adjust property name if necessary

        // Load data into the table
        loadData(tableView);
    }

    // Method to load data into the table
    private void loadData(TableView<Discipline> tableView) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "SELECT * FROM \"Discipline\"";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            // Clear existing data in the table
            tableView.getItems().clear();

            // Populate table with data from the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Nom");
                Discipline discipline = new Discipline(id, name);
                tableView.getItems().add(discipline);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}