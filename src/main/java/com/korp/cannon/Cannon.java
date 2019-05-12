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

    public double x_end;  //!! muszą być double bo int'y dają silnie złe wyniki
    public double y_end;

    private GameLoop loop;

    public Cannon(double x, double y, double width, double height, GameLoop loop) {
        this.x = x;
        this.y = y;
        w = width;
        h = height;
        theta = -45;
        EndCoordinates();
        this.loop = loop;
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

    public void setAngle(double angle){
        theta=angle;
        EndCoordinates();
    }

    public void EndCoordinates(){
        x_end = Math.cos(Math.toRadians(theta)) * w + x;
        y_end = Math.sin(Math.toRadians(theta)) * w + y;
    }

    public void shoot(){
        double power = 5;
        double powerX = power * Math.cos(Math.toRadians(theta));
        double powerY = power * Math.sin(Math.toRadians(theta));
        loop.addObject(new Test(x_end,y_end,powerX,powerY));
    }
}
