package com.korp.cannon;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas {
    private static final long serialVersionUID = -1409849032435917931L;

    public Window(int width, int height, int offset, String title, GameLoop game){
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height+offset));
        frame.setMaximumSize(new Dimension(width, height+offset));
        frame.setMinimumSize(new Dimension(width, height+offset));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.add(game);
        frame.setVisible(true);

    }
}
