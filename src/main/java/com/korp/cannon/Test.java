package com.korp.cannon;

import java.awt.*;

public class Test extends Bullet{

    public Test(double x, double y, double vx, double vy){
        super(x, y, vx, vy);
    }
    @Override
    public void update() {
        super.update();

        vy -= 0.03;
        if(x < 0){
            x=0;
        }
        if(y < 0){
            y=0;
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillOval((int)x, (int)y, 10, 10);
    }
}
