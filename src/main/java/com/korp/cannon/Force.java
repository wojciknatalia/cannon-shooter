package com.korp.cannon;

public class Force{
    static private double g = 9.81;
    static public void gravity(Bullet bullet){
            bullet.vy += g*GameLoop.timeratio;
    }
};
