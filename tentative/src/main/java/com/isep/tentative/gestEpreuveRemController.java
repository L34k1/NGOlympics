package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.isep.tentative.HelloApplication.mainStage;

public class gestEpreuveRemController {

    @FXML
    private Button bGestEpreuveRemReturn;

    @FXML
    private Button bGestEpreuveRemRemove;

    @FXML
    private TableView<Epreuve> tableViewEp;

    @FXML
    private TableColumn<Epreuve, Integer> gestEpIDcol;

    @FXML
    private TableColumn<Epreuve, LocalDate> gestEpDateCol;

    @FXML
    private TableColumn<Epreuve, String> gestEpLocationCol;

    @FXML
    private TableColumn<Epreuve, Discipline> gestEpDisciplineCol;

    @FXML
    private TableColumn<Epreuve, String> gestEpNameCol;

    @FXML
    private TableColumn<Epreuve, Integer> gestEpDiscIDCol;

    @FXML
    private TableColumn<Epreuve, String> gestEpAthleteIDListCol;

    @FXML
    private TextField gestEpreuveRemIDfield;

    @FXML
    private Label gestEpreuveRemIDLabel;

    public void initialize() {
        EpreuveTableViewManager tableViewManager = new EpreuveTableViewManager();
        tableViewManager.initializeTable(tableViewEp, gestEpIDcol, gestEpDateCol, gestEpLocationCol, gestEpDisciplineCol, gestEpNameCol, gestEpDiscIDCol, gestEpAthleteIDListCol);
    }

    @FXML
    protected void onBGestEpreuveRemReturnButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestEp.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        mainStage.setTitle("Remove Epreuve");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onBGestEpreuveRemRemoveButtonClick() {
        // Implement action for remove button
        String idToRemove = gestEpreuveRemIDfield.getText();
        if (!idToRemove.isEmpty()) {
            removeEpreuve(Integer.parseInt(idToRemove));
        }
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private void removeEpreuve(int id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "DELETE FROM \"Epreuve\" WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Epreuve with ID " + id + " has been removed from the database.");
                EpreuveTableViewManager tableViewManager = new EpreuveTableViewManager();
                tableViewManager.initializeTable(tableViewEp, gestEpIDcol, gestEpDateCol, gestEpLocationCol, gestEpDisciplineCol, gestEpNameCol, gestEpDiscIDCol, gestEpAthleteIDListCol);
            } else {
                System.out.println("No Epreuve found with ID " + id);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}