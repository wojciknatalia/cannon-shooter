package com.korp.cannon;

import java.awt.*;

public class Slider {
    private int min;
    private int max;
    private int x;
    private int y;

    private int width = 250;
    private int height = 5;
    private String label;
    private int sliderX;
    private int sliderWidth = 10;
    private int sliderHeight = 20;
    private boolean sliderGrapped = false;

    public Slider(int x, int y, int min, int max, String label){
        this.x=x;
        this.y=y;
        this.min=min;
        this.max=max;
        this.label=label;
        this.sliderX=(width/2)-(sliderWidth/2);
    }

    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        if (GameLoop.dragging && GameLoop.cursorX > (sliderX - 10) + x && GameLoop.cursorX < sliderX + (sliderWidth + 10) + x && GameLoop.cursorY > y && GameLoop.cursorY < y + height) {
            sliderGrapped = true;
        }
        if (!GameLoop.dragging) {
            sliderGrapped = false;
        }
        if (sliderGrapped && GameLoop.cursorX > x + (sliderWidth / 2) && GameLoop.cursorX < x + width - 1) {
            sliderX = GameLoop.cursorX - x - (sliderWidth / 2);
        }

        g2.setColor(Color.GRAY);
        g2.fillRect(x, y, width, height);

        g2.setColor(Color.BLACK);
        g2.fillRect(sliderX + x, y - (sliderHeight / 3), sliderWidth, sliderHeight);

        g2.setColor(Color.BLACK);
        Font f = new Font("Monospaced", Font.PLAIN, 20);
        g2.setFont(f);
        g2.drawString(label, (width / 2) - (sliderWidth / 2), y - 12);
    }

    public int getVal(){
        return (int)((double)(sliderX+(sliderWidth/2))/(double)width*(double)(max-min));
    }

}
