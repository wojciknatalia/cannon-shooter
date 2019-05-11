package com.korp.cannon;

import java.awt.*;
import java.util.ArrayList;

public class GameLoop extends Canvas{

    private boolean running = false;
    private final int FPS = 60;

    ArrayList<Bullet> objects = new ArrayList<Bullet>();

    public GameLoop(){
        new Window(800, 640, "Cannon Shooter", this);
    }


    public void run(){
        running = true;
        loop();
    }

    private void update(double delta){
        for(Bullet obj: objects){
            obj.update(delta);
        }
    }

    private void render(){
        for(Bullet obj: objects){
            obj.render();
        }

    }

    private void userInput(){

    }

    private void loop(){
        long lastTick = System.nanoTime();
        double ns = 1000000000 / FPS;
        double delta = 0;
        while(running){
            long currTick = System.nanoTime();
            delta = (currTick - lastTick) / ns;
            lastTick = currTick;

            userInput();
            update(delta);
            render();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
