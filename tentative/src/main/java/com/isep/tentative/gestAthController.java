package com.isep.tentative;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.time.LocalDate;

import static com.isep.tentative.HelloApplication.mainStage;

public class gestAthController {

    @FXML
    private Button retourMainMenu;

    @FXML
    private TableView<Athlete> tableViewAth;

    @FXML
    private TableColumn<Athlete, Integer> gestAthIDcol;

    @FXML
    private TableColumn<Athlete, String> gestAthNomCol;

    @FXML
    private TableColumn<Athlete, Boolean> gestAthSexeCol;

    @FXML
    private TableColumn<Athlete, String> gestAthPaysCol;

    @FXML
    private TableColumn<Athlete, LocalDate> gestAthBirthdateCol;

    @FXML
    private TableColumn<Athlete, Integer> gestAthDiscIDCol;

    @FXML
    public void initialize() {
        AthleteTableViewManager tableViewManager = new AthleteTableViewManager();
        tableViewManager.initializeTable(tableViewAth, gestAthIDcol, gestAthNomCol, gestAthSexeCol, gestAthPaysCol, gestAthBirthdateCol, gestAthDiscIDCol);
    }

    @FXML
    protected void onbRetourMainMenuButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 900);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbAddAthButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestAthAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Add Athlete");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbModAthButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestAthMod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Modify Athlete");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbRemAthButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestAthRem.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Remove Athlete");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbAthAddDisButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestAthAddDis.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Remove Athlete");
        mainStage.setScene(scene);
    }
}