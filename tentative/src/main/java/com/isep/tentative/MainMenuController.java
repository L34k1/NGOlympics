package com.isep.tentative;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import static com.isep.tentative.HelloApplication.mainStage;

public class MainMenuController {

    @FXML
    private Button bGestAth;
    @FXML
    protected void onbGestAthButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gestAth.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 900);
        mainStage.setTitle("Hello!");
        mainStage.setScene(scene);
    }
}
