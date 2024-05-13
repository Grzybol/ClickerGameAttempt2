package com.example.clickergameattempt2.model;
import com.example.clickergameattempt2.util.ExplosionUtil;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Explosion {

    public static void createExplosion(Pane pane, double x, double y, ExplosionUtil.ExplosionListener listener) {
        // Create explosion visual effect
        Circle explosion = new Circle(x, y, 30, Color.ORANGE);
        pane.getChildren().add(explosion);

        // Notify listener for droplet spawn
        listener.onExplode(x, y);

        // Remove explosion after a short time
        explosion.setOpacity(0.5);
        pane.getChildren().remove(explosion);
    }
}
