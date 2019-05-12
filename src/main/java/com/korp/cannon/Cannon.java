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
    private int size;
    private int power;

    public double x_end;  //!! muszą być double bo int'y dają silnie złe wyniki
    public double y_end;

    private int diameter =20;
    private int buttonX = 105;
    private int buttonY = 350;
    private int buttonWidth = 100;
    private int buttonHeight = 50;

    private GameLoop loop;

    public Cannon(double x, double y, double width, double height, GameLoop loop) {
        this.x = x;
        this.y = y;
        w = width;
        h = height;
        this.loop = loop;
        power=2;
    }

    public Graphics draw(Graphics g, int angle, int size, int power){
        this.theta=angle;
        this.size=size;
        this.power=power;

        g=render(g);
        return g;
    }

    public void update() {

    }

    public Graphics render(Graphics g) {
        //== rotate
        int xPoly[] = {(int)x, (int)(x + w), (int)(x + w), (int)x};
        int yPoly[] = {(int)y, (int)y, (int)(y + diameter),(int)(y + diameter)};
        int i;
        for (i = 0; i < xPoly.length; i++){
            int newXY[] = rotateXY(xPoly[i], yPoly[i], (int)theta, (int)x, (int)(y + diameter));
            xPoly[i] = newXY[0];
            yPoly[i] = newXY[1];
        }
        for (i = 0; i < xPoly.length; i++){
            yPoly[i] = yPoly[i] + (int)y + 100 - yPoly[3]; //keeps it fixed to the corner
        }

        x_end = xPoly[1];
        y_end = yPoly[1];
        x_end += xPoly[2] - xPoly[1] - 2;

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        Polygon poly = new Polygon(xPoly, yPoly, xPoly.length);
        g2.fillPolygon(poly);

        return g;
    }

    public void setAngle(double angle){
        theta=angle;
    }

    public void shoot(){
        double powerX = power * Math.cos(Math.toRadians(theta));
        double powerY = power * Math.sin(Math.toRadians(theta)); //do góry
        loop.addObject(new Test(x_end,y_end,-powerX,-powerY));
    }

    private int[] rotateXY(int x, int y, int angle, int cx, int cy) {

        double tempX = x - cx;
        double tempY = y - cy;

        double rotatedX = tempX*Math.cos((double) angle / 100) - tempY*Math.sin((double) angle / 100);
        double rotatedY = tempX*Math.sin((double) angle / 100) + tempY*Math.cos((double) angle / 100);

        x = (int) (rotatedX + cx);
        y = (int) (rotatedY + cy);
        return new int[] {x, y};

    }

}
