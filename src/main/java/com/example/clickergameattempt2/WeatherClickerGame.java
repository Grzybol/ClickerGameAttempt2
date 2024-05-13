package com.example.clickergameattempt2;

import com.example.clickergameattempt2.ui.UIInitializer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WeatherClickerGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        UIInitializer uiInitializer = new UIInitializer();
        Scene scene = uiInitializer.initializeUI();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Weather Clicker Game");
        primaryStage.show();

        uiInitializer.startGameLoop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}