package com.korp.cannon;

import javax.lang.model.util.Elements;
import java.awt.*;

public abstract class Bullet extends Element{
    public double x;
    public double y;
    public double vx;
    public double vy;
    public int radius = 0;

    public void update(){
        x += vx;
        y += vy;

        if(x<=0){
            x = 0;
            vx = -vx;
        }
        else if(x>=GameLoop.WIDTH){
            x = GameLoop.WIDTH;
            vx = -vx;
        }
        else if(y<=0){
            y = 0;
            vy = -vy;
        }
        else if(y>=GameLoop.HEIGHT){
            y = GameLoop.HEIGHT;
            vy = -vy;
        }
    }

    public abstract void render(Graphics g);

    public Bullet(double x, double y, double vx, double vy){
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

}
