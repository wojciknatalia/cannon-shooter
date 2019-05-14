package com.korp.cannon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameLoop extends Canvas implements MouseListener, MouseMotionListener, KeyListener {

    private boolean running = false;
    private final int FPS = 30;
    public final static int WIDTH = 800;
    public final static int HEIGHT = 700;

    private CopyOnWriteArrayList<Bullet> objects = new CopyOnWriteArrayList<Bullet>();
    private Cannon cannon;
    private Slider angleSlider=new Slider(50,80,90,0,"Angle");
    private Slider sizeSlider=new Slider(50, 140, 0, 75, "Size");
    private Slider powerSlider=new Slider(50, 200, 10, 0, "Power");

    public GameLoop(){
        new Window(WIDTH, HEIGHT, "Cannon Shooter", this);
        cannon = new Cannon(100,500, 50, 10, this);  //od 0 do -90
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
    }


    public void run(){
        running = true;
        loop();
    }

    public void addObject(Bullet obj){
        objects.add(obj);
    }

    public void delObject(Bullet obj){
        objects.remove(obj);
    }

    public void collideEffect(){
	    for(Bullet A: objects){
		    for(Bullet B: objects){
			    if(A.equals(B)) continue;

			    Vector2D vecA = new Vector2D(A.x, A.y);
			    Vector2D vecB = new Vector2D(B.x, B.y);
			    Vector2D dist = vecA.subtract(vecB);

			    if(dist.getNorm() > A.radius + B.radius)
				    continue;

			    if(dist.getX() == 0 && dist.getY() == 0)	
				    dist = new Vector2D(1,0);

			    dist = dist.normalize();
			    double dotA = vecA.dotProduct(dist);
			    double dotB = vecB.dotProduct(dist);
			    double mn = dotA - dotB;
			    
			    A.vx += dist.getX()*mn/70;
			    A.vy += dist.getY()*mn/70;
			    B.vx -= dist.getX()*mn/70;
			    B.vy -= dist.getY()*mn/70;
		    }
	    }

    }

    private void update(){
        for(Bullet obj: objects){
            obj.update();
        }
        collideEffect();
        cannon.set(angleSlider.getVal(), sizeSlider.getVal(), powerSlider.getVal());
        cannon.update();
    }

    private synchronized void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Background
        g.setColor(Color.pink);
        g.fillRect(0,0, WIDTH, HEIGHT);

        //Bullet
        for(Bullet obj: objects){
            obj.render(g);
        }

        //Sliders
        angleSlider.render(g);
        sizeSlider.render(g);
        powerSlider.render(g);

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
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()){
            case KeyEvent.VK_SPACE:
                cannon.shoot();
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}

}

