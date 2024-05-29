package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

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
    private Button bMod;

    @FXML
    private Button bRem;

    @FXML
    public void initialize() {
        DisciplineTableViewManager tableViewManager = new DisciplineTableViewManager();
        tableViewManager.initializeTable(table, idCol, nameCol);
    }

    @FXML
    protected void onbRetourMainMenuButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 900);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbAddDisButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestDisAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Add Discipline");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbModDisButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestDisMod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Modify Discipline");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbRemDisButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestDisRem.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Remove Discipline");
        mainStage.setScene(scene);
    }
}
