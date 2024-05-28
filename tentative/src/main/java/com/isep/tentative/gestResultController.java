package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

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
    private Button bAdd;

    @FXML
    private Button bMod;

    @FXML
    private Button bRem;

    @FXML
    public void initialize() {
        ResultTableViewManager tableViewManager = new ResultTableViewManager();
        tableViewManager.initializeTable(table, idCol, medailleCol, validationCol);
    }

    @FXML
    protected void onbRetourMainMenuButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 900);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbAddResultButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestResAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Add Result");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbModResultButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestResultMod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Modify Result");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbRemResultButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestResultRem.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Remove Result");
        mainStage.setScene(scene);
    }
}
