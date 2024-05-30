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

public class gestDisController {

    @FXML
    private Button retourMainMenu;

    @FXML
    private TableView<Discipline> table;

    @FXML
    private TableColumn<Discipline, Integer> idCol;

    @FXML
    private TableColumn<Discipline, String> nameCol;

    @FXML
    private Button bAdd;

    @FXML
    private Button bRem;

    @FXML
    private TextField fieldRemSelector;

    @FXML
    public void initialize() {
        DisciplineTableViewManager tableViewManager = new DisciplineTableViewManager();
        tableViewManager.initializeTable(table, idCol, nameCol);
    }

    @FXML
    protected void onbRetourMainMenuButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbAddDisButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestDisAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        mainStage.setTitle("Add Discipline");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbRemDiscipline() {
        // Implement action for remove button
        String idToRemove = fieldRemSelector.getText();
        if (!idToRemove.isEmpty()) {
            removeDiscipline(Integer.parseInt(idToRemove));
        }
    }
    private void removeDiscipline(int id) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "root");
            String sql = "DELETE FROM \"Discipline\" WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Discipline with ID " + id + " has been removed from the database.");
                DisciplineTableViewManager tableViewManager = new DisciplineTableViewManager();
                tableViewManager.initializeTable(table,idCol,nameCol);  // Fixed name here
            } else {
                System.out.println("No Discipline found with ID " + id);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
