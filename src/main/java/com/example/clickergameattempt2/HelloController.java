package com.example.clickergameattempt2;

import com.example.clickergameattempt2.model.Droplet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import java.util.Random;

public class HelloController {

    @FXML
    private Pane gamePane;
    @FXML
    private Text scoreText;
    @FXML
    private Button upgradeButton;

    private int score = 0;
    private int explosionUpgradeLevel = 1;
    private int rainCollectorUpgradeLevel = 0;
    private Random random = new Random();

    @FXML
    public void initialize() {
        // Initial setup
        updateScoreText();
        upgradeButton.setOnAction(e -> showUpgradeMenu());
    }

    private void updateScoreText() {
        scoreText.setText("Score: " + score);
    }

    public void spawnDroplets(double x, double y) {
        int dropletCount = 10 * explosionUpgradeLevel;
        for (int i = 0; i < dropletCount; i++) {
            double dropletX = random.nextDouble() * gamePane.getWidth();
            double dropletY = y + random.nextDouble() * 50; // Slightly below the explosion point

            Droplet droplet = new Droplet(dropletX, dropletY);
            droplet.setOnMouseClicked(this::collectDroplet);
            gamePane.getChildren().add(droplet);
        }
    }

    private void collectDroplet(MouseEvent event) {
        Droplet droplet = (Droplet) event.getSource();
        gamePane.getChildren().remove(droplet);
        score++;
        updateScoreText();
    }

    private void showUpgradeMenu() {
        // Display the upgrade menu (simplified for demonstration)
        System.out.println("Upgrade Menu");
        System.out.println("1. Explosion Upgrade (Level " + explosionUpgradeLevel + ")");
        System.out.println("2. Rain Collector Upgrade (Level " + rainCollectorUpgradeLevel + ")");
    }

    public void upgradeExplosion() {
        explosionUpgradeLevel++;
    }

    public void upgradeRainCollector() {
        if (rainCollectorUpgradeLevel < 100) {
            rainCollectorUpgradeLevel++;
        }
    }

    public void passiveRainCollector() {
        int droplets = gamePane.getChildren().filtered(node -> node instanceof Droplet).size();
        int collected = droplets * rainCollectorUpgradeLevel / 100;
        score += collected;
        updateScoreText();
    }
}