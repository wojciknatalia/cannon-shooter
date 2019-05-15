package com.korp.cannon;

import java.awt.*;

public abstract class Element {
    public double x;
    public double y;
    public double vx;
    public double vy;

    public Element(double x, double y, double vx, double vy){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public Element(double x, double y, double width, double height, GameLoop loop){

    }

    public abstract void shoot();

    public abstract void update();

    public abstract void render(Graphics g);
}
