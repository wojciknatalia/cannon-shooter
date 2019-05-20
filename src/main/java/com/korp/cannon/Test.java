package com.korp.cannon;

import java.awt.*;

public class Test extends Bullet{

    public Test(double x, double y, double vx, double vy, int size){
        super(x, y, vx, vy);
	    radius = size;
    }


    @Override
    public void update() {
        super.update();
        Force.gravity(this);
    }


    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillOval((int)x, (int)y, radius, radius);
    }
}
