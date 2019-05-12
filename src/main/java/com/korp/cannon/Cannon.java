package com.korp.cannon;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class Cannon {

    public double x;
    public double y;
    public double w;
    public double h;
    public double theta;


    public Cannon(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        w = width;
        h = height;
        theta = -45;
    }

    public void update() {

    }

    public void render(Graphics g) {
        if(theta<  -90)
            theta = -90;
        if(theta  > 0)
            theta = 0;
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.rotate(Math.toRadians(theta),x,y);
        g2.fillRect((int) x, (int) y, (int)w, (int)h);
    }

    public void userinput(double angle){
        theta = angle;
    }
}
