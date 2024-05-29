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

public class gestEpController {

    @FXML
    private Button retourMainMenu;

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
    public void initialize() {
        EpreuveTableViewManager tableViewManager = new EpreuveTableViewManager();
        tableViewManager.initializeTable(tableViewEp, gestEpIDcol, gestEpDateCol, gestEpLocationCol, gestEpDisciplineCol, gestEpNameCol, gestEpDiscIDCol, gestEpAthleteIDListCol);
    }

    @FXML
    protected void onbRetourMainMenuButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 900);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbAddEpButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestEpAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Add Epreuve");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbModEpButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestEpMod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Modify Epreuve");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbRemEpButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestEpRem.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Remove Epreuve");
        mainStage.setScene(scene);
    }

    @FXML
    protected void onbEpAddDisButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gestEpAddDis.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        mainStage.setTitle("Remove Epreuve");
        mainStage.setScene(scene);
    }
}
