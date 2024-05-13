package com.example.clickergameattempt2.ui;

import com.example.clickergameattempt2.controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class UIInitializer {

    private Pane gamePane;
    private GameController controller;

    public Scene initializeUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/weather_clicker.fxml"));
            gamePane = loader.load();
            controller = loader.getController();

            return new Scene(gamePane, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void startGameLoop() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            controller.spawnRocket();
            controller.passiveRainCollector();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}