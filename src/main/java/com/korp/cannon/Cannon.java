package com.korp.cannon;

import java.awt.*;

public class Cannon {

    public double x;
    public double y;
    public double w;
    public double h;

    public Cannon(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        w = width;
        h = height;
    }

    public void update() {

    }

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect((int) x, (int) y, (int)w, (int)h);
    }
}
