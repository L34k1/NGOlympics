package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.isep.tentative.HelloApplication.mainStage;

public class gestResultController {

    @FXML
    private Button retourMainMenu;

    @FXML
    private TableView<Resultat> table;

    @FXML
    private TableColumn<Resultat, Integer> idCol;

    @FXML
    private TableColumn<Resultat, Character> medailleCol;

    @FXML
    private TableColumn<Resultat, Boolean> validationCol;

    @FXML
    private TableColumn<Resultat, Integer> athleteIDcol;

    @FXML
    private TableColumn<Resultat, Integer> epreuveIDcol;

    @FXML
    private TableColumn<Resultat, String> scoreCol;

    @FXML
    private Button bAdd;

    @FXML
    private Button bMod;

    @FXML
    private Button bRem;

    @FXML
    private TextField fieldRemSelector;

    @FXML
    public void initialize() {
        ResultTableViewManager tableViewManager = new ResultTableViewManager();
        tableViewManager.initializeTable(table, idCol, medailleCol, validationCol, athleteIDcol, epreuveIDcol, scoreCol);
    }

    @FXML
    protected void onbRetourMainMenuButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbAddResultButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestResAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        mainStage.setTitle("Add Result");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbModResultButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestResMod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        mainStage.setTitle("Modify Result");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbRemResultButtonClick() {
        String idToRemove = fieldRemSelector.getText();
        if (!idToRemove.isEmpty()) {
            removeResult(Integer.parseInt(idToRemove));
        }
    }

    private void removeResult(int id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "DELETE FROM \"Resultat\" WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Result with ID " + id + " has been removed from the database.");
                ResultTableViewManager tableViewManager = new ResultTableViewManager();
                tableViewManager.initializeTable(table,idCol,medailleCol, validationCol, athleteIDcol, epreuveIDcol, scoreCol);  // Fixed name here
            } else {
                System.out.println("No Result found with ID " + id);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
