package com.korp.cannon;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

class Ruler extends Element{
    double w;
    double h;

    Ruler(double x, double y, double w, double h){
        super(x,y);
        this.w = w;
        this.h = h;
    }

    public void update(){}

    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.darkGray);
        g.fillRect((int)x,(int)y,(int)w,(int)h);
        for(int i=0; i<w; i+=50){
            g.setColor(Color.white);
            Line2D lin = new Line2D.Double(i, y, i, y+h);
            g2.draw(lin);
            g2.drawString(String.valueOf((int)(i/GameLoop.pixtom)), i+5, (int)y+15);
        }
    }
}
