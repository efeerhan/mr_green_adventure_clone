package com.group4.app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class Tracker {

    /**
     * The parent class for either tracker. Sets a common font and point size to be used between the two.
     */

    protected final JPanel holder;
    protected JLabel display;
    protected Font customFont;
    public Tracker(){
        holder = new JPanel();
        display = new JLabel("",SwingConstants.LEFT);
        display.setForeground(Color.white);
        try { //try specified front
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font.ttf")).deriveFont(48f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) { System.out.println("Could not find specified font"); }

        display.setFont(customFont);
        display.setSize(400,120);
        holder.setSize(400,120);
        holder.setLocation(100,30);
        holder.add(display);
        holder.setOpaque(false);
    }

    public JPanel getHolder() {
        return holder;
    }
}