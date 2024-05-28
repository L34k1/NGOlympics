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

public class gestResultModController {

    @FXML
    private TableView<Resultat> table;

    @FXML
    private TableColumn<Resultat, Integer> IDcol;

    @FXML
    private TableColumn<Resultat, Character> MedailleCol;

    @FXML
    private TableColumn<Resultat, Boolean> ValidationCol;

    @FXML
    private Button bReturn;

    @FXML
    private Button bMod;

    @FXML
    private Button bClear;

    @FXML
    private TextField fieldNewMedaille;

    @FXML
    private TextField fieldNewValidation;

    @FXML
    private TextField fieldIDselector;

    // Initialize the controller
    @FXML
    public void initialize() {
        ResultTableViewManager tableViewManager = new ResultTableViewManager();
        tableViewManager.initializeTable(table, IDcol, MedailleCol, ValidationCol);
    }

    @FXML
    private void onBModClick() {
        String idText = fieldIDselector.getText();
        String newMedaille = fieldNewMedaille.getText();
        String newValidation = fieldNewValidation.getText();

        if (idText.isEmpty() || newMedaille.isEmpty() || newValidation.isEmpty()) {
            System.out.println("Please fill all fields before modifying.");
            return;
        }

        if (newMedaille.length() != 1) {
            System.out.println("Please enter a single character for the Medaille.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);
            char medailleChar = newMedaille.charAt(0);
            boolean validation = Boolean.parseBoolean(newValidation);

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "UPDATE \"Resultat\" SET \"Medaille\" = ?, \"Validation\" = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, String.valueOf(medailleChar)); // Store as String but only one character
            statement.setBoolean(2, validation);
            statement.setInt(3, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Result with ID " + id + " has been updated.");
                ResultTableViewManager tableViewManager = new ResultTableViewManager();
                tableViewManager.initializeTable(table, IDcol, MedailleCol, ValidationCol);
            } else {
                System.out.println("No Result found with ID " + id);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBClearClick() {
        fieldNewMedaille.clear();
        fieldNewValidation.clear();
        fieldIDselector.clear();
    }

    @FXML
    private void onBReturnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestResult.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Return");
        mainStage.setScene(scene);
    }
}
