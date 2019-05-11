package com.korp.cannon;

public abstract class Bullet {
    public double x;
    public double y;
    public double vx;
    public double vy;
    public double ax;
    public double ay;

    public abstract void update(double delta);
    public abstract void render();

}
