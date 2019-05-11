package com.korp.cannon;

import java.awt.*;

public abstract class Bullet {
    public double x;
    public double y;
    public double vx;
    public double vy;

    public abstract void update();
    public abstract void render(Graphics g);

    public Bullet(double x, double y, double vx, double vy){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }
}
