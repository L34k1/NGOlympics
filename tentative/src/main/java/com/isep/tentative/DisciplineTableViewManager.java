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

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name")); // Adjust property name if necessary

        loadData(tableView);
    }

    private void loadData(TableView<Discipline> tableView) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "SELECT * FROM \"Discipline\"";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            tableView.getItems().clear();

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