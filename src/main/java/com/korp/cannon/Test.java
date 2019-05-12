package com.korp.cannon;

import java.awt.*;

public class Test extends Bullet{

    public Test(double x, double y, double vx, double vy){
        super(x, y, vx, vy);
	radius = 20;
    }
    @Override
    public void update() {
        super.update();
        vy += 0.1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillOval((int)x, (int)y, radius, radius);
    }
}
