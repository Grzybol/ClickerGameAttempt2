package com.example.clickergameattempt2;

import java.awt.*;

class Explosion {
    Point location;
    int frame;

    public Explosion(Point location) {
        this.location = location;
        this.frame = 0;
    }

    public boolean update() {
        frame++;
        return frame <= 20; // Trwanie wybuchu przez 20 klatek
    }
}

