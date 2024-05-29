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
    private TableColumn<Athlete, LocalDate> gestAthRemBirthdateCol;

    @FXML
    private TableColumn<Athlete, Integer> gestAthRemDiscIDCol;  // Fixed name here

    @FXML
    private TextField gestAthRemIDfield;

    @FXML
    private Label gestAthRemIDLabel;

    public void initialize() {
        AthleteTableViewManager tableViewManager = new AthleteTableViewManager();
        tableViewManager.initializeTable(gestAthRemTableview, gestAthRemIDcol, gestAthRemNomCol, gestAthRemSexeCol, gestAthRemPaysCol, gestAthRemBirthdateCol, gestAthRemDiscIDCol);  // Fixed name here
    }


    @FXML
    protected void onBGestAthRemReturnButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestAth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Remove Athlete");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onBGestAthRemRemoveButtonClick() {
        // Implement action for remove button
        String idToRemove = gestAthRemIDfield.getText();
        if (!idToRemove.isEmpty()) {
            removeAthlete(Integer.parseInt(idToRemove));
        }
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private void removeAthlete(int id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "DELETE FROM \"Athlete\" WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Athlete with ID " + id + " has been removed from the database.");
                AthleteTableViewManager tableViewManager = new AthleteTableViewManager();
                tableViewManager.initializeTable(gestAthRemTableview, gestAthRemIDcol, gestAthRemNomCol, gestAthRemSexeCol, gestAthRemPaysCol, gestAthRemBirthdateCol, gestAthRemDiscIDCol);  // Fixed name here
            } else {
                System.out.println("No athlete found with ID " + id);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
