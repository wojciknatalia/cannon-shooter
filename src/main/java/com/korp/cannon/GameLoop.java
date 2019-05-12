package com.korp.cannon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameLoop extends Canvas implements MouseListener, MouseMotionListener {

    private boolean running = false;
    private final int FPS = 30;

    private LinkedList<Bullet> objects = new LinkedList<Bullet>();
    private Cannon cannon;
    private Slider angleSlider=new Slider(50,80,157,0,"Angle");
    private Slider sizeSlider=new Slider(50, 140, 0, 75, "Size");
    private Slider powerSlider=new Slider(50, 200, 150, 0, "Power");

    public GameLoop(){
        new Window(800, 640, "Cannon Shooter", this);
        cannon = new Cannon(0,500, 50, 10);  //od 0 do -90
        addMouseListener(this);
        addMouseMotionListener(this);
        addObject(new Test(cannon.x_end,cannon.y_end,5,-3));
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
        cannon.update();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Background
        g.setColor(Color.pink);
        g.fillRect(0,0, 800, 640);

        //Bullet
        for(Bullet obj: objects){
            obj.render(g);
        }

        //Sliders
        angleSlider.render(g);
        sizeSlider.render(g);
        powerSlider.render(g);

        //Cannon
        cannon.render(g);



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

    //============ Mouse Listening ============
    static boolean dragging = false;
    static boolean click = false;
    static int cursorX = 0;
    static int cursorY = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
        click = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragging = true;
        cursorX = e.getX();
        cursorY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        dragging = false;
        click = false;
        cursorX = e.getX();
        cursorY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
}
