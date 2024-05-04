package com.example.clickergameattempt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WeatherClickerGame extends JFrame {
    private final int WIDTH = 800;
    private List<Explosion> explosions;

    private final int HEIGHT = 600;
    private JPanel gamePanel;
    private JButton fireButton;
    private List<Point> rainDrops;
    private List<Point> smokeParticles;
    private List<Point> rockets; // Lista do przechowywania wielu rakiet

    private boolean isExploding = false;
    private int explosionFrame = 0;
    private int score = 0;
    private JLabel scoreLabel;

    public WeatherClickerGame() {
        setTitle("Weather Modification Clicker Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        scoreLabel = new JLabel("Score: 0");
        explosions = new ArrayList<>();

        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rockets = new ArrayList<>();
        fireButton = new JButton("Fire Rocket");
        fireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchRocket();
            }
        });

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBackground(g);
                drawRocket(g);
                drawRain(g);
                drawSmoke(g);
                drawExplosion(g);
            }
        };
        gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT - 40)); // Adjust the size to see the grass and water

        add(scoreLabel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        add(fireButton, BorderLayout.SOUTH);

        rainDrops = new ArrayList<>();
        smokeParticles = new ArrayList<>();

        // Timer to update game state
        new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                gamePanel.repaint();
            }
        }).start();

        setVisible(true);
    }

    private void launchRocket() {
        rockets.add(new Point(400, 500)); // Dodaj nową rakietę
    }

    private void updateGame() {
        Iterator<Point> rocketIt = rockets.iterator();
        while (rocketIt.hasNext()) {
            Point rocket = rocketIt.next();
            int dx = (650 + 75 / 2) - rocket.x; // Kierunek do środka chmury (X)
            int dy = 25 - rocket.y;            // Kierunek do środka chmury (Y)
            double dist = Math.sqrt(dx * dx + dy * dy);

            rocket.x += (int)(10 * (dx / dist)); // Skalowanie prędkości lotu, zakładając stałą prędkość 10
            rocket.y += (int)(10 * (dy / dist));

            smokeParticles.add(new Point(rocket.x, rocket.y + 20)); // Dodaj dym

            // Sprawdź czy rakieta osiągnęła chmurę
            if (Math.abs(dx) < 10 && Math.abs(dy) < 10) {
                rocketIt.remove();
                explosions.add(new Explosion(new Point(rocket.x, rocket.y)));
            }
        }

        Iterator<Explosion> explosionIt = explosions.iterator();
        while (explosionIt.hasNext()) {
            Explosion explosion = explosionIt.next();
            if (!explosion.update()) {
                explosionIt.remove();
                createRain(explosion.location);
            }
        }

        updateSmoke();
        updateRain();
    }





    private void createRain(Point location) {
        for (int i = 0; i < 10; i++) {
            rainDrops.add(new Point(location.x, location.y));
        }
    }



    private void updateSmoke() {
        Iterator<Point> it = smokeParticles.iterator();
        while (it.hasNext()) {
            Point smoke = it.next();
            smoke.y += 1; // Smoke rises slowly
            if (Math.random() > 0.9) {
                it.remove(); // Randomly remove smoke particles
            }
        }
    }

    private void updateRain() {
        Iterator<Point> iterator = rainDrops.iterator();
        while (iterator.hasNext()) {
            Point drop = iterator.next();
            drop.y += 5; // Raindrop falls down
            if (drop.y > 510 && drop.x > 700) { // Check if raindrop is over the water area
                iterator.remove();
                score += 1; // Add score when raindrop hits the water
                scoreLabel.setText("Score: " + score);
            }
        }
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.GREEN);
        g.fillRect(0, 560, WIDTH, 40); // Grass area at the bottom now visible
        g.setColor(Color.BLUE);
        g.fillRect(700, 520, 100, 40); // Water area moved up
        g.setColor(Color.GRAY);
        g.fillOval(650, 0, 150, 50); // Cloud moved to the right
    }

    private void drawRocket(Graphics g) {
        for (Point rocket : rockets) {
            g.setColor(Color.RED);
            g.fillOval(rocket.x, rocket.y, 10, 20); // Narysuj każdą rakietę
        }
    }


    private void drawSmoke(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (Point smoke : smokeParticles) {
            g.fillOval(smoke.x, smoke.y, 10, 10); // Draw smoke particles
        }
    }

    private void drawExplosion(Graphics g) {
        for (Explosion explosion : explosions) {
            g.setColor(Color.ORANGE);
            g.fillOval(explosion.location.x - 75, explosion.location.y - 25, 150, 150); // Aby wyśrodkować animację wybuchu
        }
    }


    private void drawRain(Graphics g) {
        g.setColor(Color.BLUE);
        for (Point drop : rainDrops) {
            g.fillOval(drop.x, drop.y, 5, 10); // Draw raindrops
        }
    }

    public static void main(String[] args) {
        new WeatherClickerGame();
    }
}
