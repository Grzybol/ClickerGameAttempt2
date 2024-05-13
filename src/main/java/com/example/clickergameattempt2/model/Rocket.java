package com.example.clickergameattempt2.model;
import com.example.clickergameattempt2.util.ExplosionUtil;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Rocket extends Rectangle {

    private ExplosionUtil.ExplosionListener explosionListener;

    public Rocket(double x, double y) {
        super(x, y, 10, 30);
        setFill(Color.RED);
    }

    public void setOnExplosion(ExplosionUtil.ExplosionListener listener) {
        this.explosionListener = listener;
    }

    public void launch() {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), this);
        transition.setToY(-600);
        transition.setOnFinished(event -> explode());
        transition.play();
    }

    private void explode() {
        if (explosionListener != null) {
            explosionListener.onExplode(getTranslateX() + getX(), getTranslateY() + getY());
        }
        if (getParent() instanceof Pane) {
            ((Pane) getParent()).getChildren().remove(this);
        }
    }
}