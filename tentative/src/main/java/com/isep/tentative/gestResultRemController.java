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
import java.time.format.DateTimeFormatter;

import static com.isep.tentative.HelloApplication.mainStage;

public class gestResultRemController {

    @FXML
    private Button bGestResultRemReturn;

    @FXML
    private Button bGestResultRemRemove;

    @FXML
    private TableView<Resultat> gestResultRemTableview;

    @FXML
    private TableColumn<Resultat, Integer> gestResultRemIDcol;

    @FXML
    private TableColumn<Resultat, Character> gestResultRemMedailleCol;

    @FXML
    private TableColumn<Resultat, Boolean> gestResultRemValidationCol;

    @FXML
    private TextField gestResultRemIDfield;

    @FXML
    private Label gestResultRemIDLabel;

    public void initialize() {
        ResultTableViewManager tableViewManager = new ResultTableViewManager();
        tableViewManager.initializeTable(gestResultRemTableview, gestResultRemIDcol, gestResultRemMedailleCol, gestResultRemValidationCol);
    }

    @FXML
    protected void onBGestResultRemReturnButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestResult.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Remove Result");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onBGestResultRemRemoveButtonClick() {
        String idToRemove = gestResultRemIDfield.getText();
        if (!idToRemove.isEmpty()) {
            removeResult(Integer.parseInt(idToRemove));
        }
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
                tableViewManager.initializeTable(gestResultRemTableview, gestResultRemIDcol, gestResultRemMedailleCol, gestResultRemValidationCol);
            } else {
                System.out.println("No Result found with ID " + id);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
