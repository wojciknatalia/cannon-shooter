package com.korp.cannon;

import java.awt.*;

public class Test extends Bullet{

    public Test(double x, double y, double vx, double vy, int size){
        super(x, y, vx, vy);
	    radius = size;
    }

    @Override
    public void shoot(){}

    @Override
    public void update(int angle, int size, int power) {
        super.update(angle, size, power);
        vy += 0.1;
    }


    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillOval((int)x, (int)y, radius, radius);
    }
}
