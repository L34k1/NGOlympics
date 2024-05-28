import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TableViewManager {

    public void initializeTable(TableView<Athlete> tableView,
                                TableColumn<Athlete, Integer> idCol,
                                TableColumn<Athlete, String> nameCol,
                                TableColumn<Athlete, Boolean> genderCol,
                                TableColumn<Athlete, String> countryCol,
                                TableColumn<Athlete, LocalDate> birthdateCol) {
        // Set up cell value factories for table columns
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name")); // Adjust property name if necessary
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender")); // Adjust property name if necessary
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country")); // Adjust property name if necessary
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate")); // Adjust property name if necessary

        // Add columns to the table
        tableView.getColumns().add(idCol);
        tableView.getColumns().add(nameCol);
        tableView.getColumns().add(genderCol);
        tableView.getColumns().add(countryCol);
        tableView.getColumns().add(birthdateCol);

        // Load data into the table
        loadData(tableView);
    }

    // Method to load data into the table
    private void loadData(TableView<Athlete> tableView) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "SELECT * FROM \"Athlete\"";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            // Clear existing data in the table
            tableView.getItems().clear();

            // Populate table with data from the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Nom");
                boolean gender = rs.getBoolean("Sexe");
                String country = rs.getString("Pays");
                LocalDate birthdate = rs.getDate("Date de naissance").toLocalDate();

                Athlete athlete = new Athlete(id, name, gender, country, birthdate);
                tableView.getItems().add(athlete);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}