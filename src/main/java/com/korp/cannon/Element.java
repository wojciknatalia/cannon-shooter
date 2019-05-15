package com.korp.cannon;

import java.awt.*;

public abstract class Element {
    //public double x;
    //public double y;
    //public double vx;
    //public double vy;

    public abstract void update();

    public abstract void render(Graphics g);
}
