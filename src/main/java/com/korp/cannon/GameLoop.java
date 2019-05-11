package com.korp.cannon;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameLoop extends Canvas{

    private boolean running = false;
    private final int FPS = 30;

    private LinkedList<Bullet> objects = new LinkedList<Bullet>();

    public GameLoop(){
        new Window(800, 640, "Cannon Shooter", this);
        addObject(new Test(300,300,1,0));
    }


    public void run(){
        running = true;
        loop();
    }

    public void addObject(Bullet obj){
        objects.push(obj);
    }

    public void delObject(Bullet obj){
        objects.remove(obj);
    }

    private void update(){
        for(Bullet obj: objects){
            obj.update();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.pink);
        g.fillRect(0,0, 800, 640);

        for(Bullet obj: objects){
            obj.render(g);
        }

        g.dispose();
        bs.show();
    }

    private void userInput(){

    }

    private void loop(){
        long lastTick = System.nanoTime();
        double ns = 1000000000 / FPS;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long currTick = System.nanoTime();
            delta += (currTick - lastTick) / ns;
            lastTick = currTick;
            while(delta >= 1){
                update();
                delta--;
                render();
            }
            userInput();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
