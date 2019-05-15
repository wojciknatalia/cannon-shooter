package com.korp.cannon;

import java.awt.*;

public abstract class Element {
    public double x;
    public double y;
    public double vx;
    public double vy;
    public double width;
    public double height;


    public Element(double x, double y, double vx, double vy){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public Element(double x, double y, double width, double height, GameLoop loop){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void shoot();

    public abstract void update(int angle, int size, int power);

    public abstract void render(Graphics g);
}
