package com.korp.cannon;

import javax.lang.model.util.Elements;
import java.awt.*;

public abstract class Bullet extends Element{
    public double vx;
    public double vy;
    public int radius = 0;

    public void update(){
        x += vx * GameLoop.timeratio;
        y += vy * GameLoop.timeratio;

        if(x<=0){
            x = 0;
            vx = -vx;
        }
        else if(x>=GameLoop.WIDTH-radius){
            x = GameLoop.WIDTH-radius;
            vx = -vx;
        }
        else if(y<=0){
            y = 0;
            vy = -vy;
        }
        else if(y>=GameLoop.HEIGHT-radius){
            y = GameLoop.HEIGHT-radius;
            vy = -vy;
        }
    }

    //public abstract void render(Graphics g);

    public Bullet(double x, double y, double vx, double vy){
        super(x, y);
        this.vx = vx;
        this.vy = vy;
    }


}
