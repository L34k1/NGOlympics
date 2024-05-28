package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultTableViewManager {

    public void initializeTable(TableView<Resultat> tableView,
                                TableColumn<Resultat, Integer> idCol,
                                TableColumn<Resultat, Character> medailleCol,
                                TableColumn<Resultat, Boolean> validationCol) {
        // Set up cell value factories for table columns
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        medailleCol.setCellValueFactory(new PropertyValueFactory<>("medaille"));
        validationCol.setCellValueFactory(new PropertyValueFactory<>("validation"));

        // Add columns to the table if they are not already added
        if (!tableView.getColumns().contains(idCol)) {
            tableView.getColumns().add(idCol);
        }
        if (!tableView.getColumns().contains(medailleCol)) {
            tableView.getColumns().add(medailleCol);
        }
        if (!tableView.getColumns().contains(validationCol)) {
            tableView.getColumns().add(validationCol);
        }

        // Load data into the table
        loadData(tableView);
    }

    // Method to load data into the table
    private void loadData(TableView<Resultat> tableView) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "SELECT * FROM \"Resultat\"";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            // Clear existing data in the table
            tableView.getItems().clear();

            // Populate table with data from the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                char medaille = rs.getString("Médaille").charAt(0); // Use "Médaille" here
                boolean validation = rs.getBoolean("Validation");

                Resultat resultat = new Resultat(id, medaille, validation);
                tableView.getItems().add(resultat);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
