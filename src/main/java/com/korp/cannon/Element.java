package com.korp.cannon;

import java.awt.*;

public abstract class Element {
    public double x;
    public double y;

    public Element(double x, double y) {  //for Slider
        this.x = x;
        this.y = y;
    }

    public abstract void update();

    public abstract void render(Graphics g);
}
