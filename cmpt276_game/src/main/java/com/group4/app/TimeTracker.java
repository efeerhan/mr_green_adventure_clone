package com.group4.app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeTracker implements ActionListener {

    /**
     * TimeTracker handles the timer on the top right of the screen. Freezes when the player has either lost
     * or won the game to show how long it took them.
     */

    private final Timer t;
    private int seconds = 0;

    public TimeTracker(ActionListener listener) {
        t = new Timer(1000,listener);
    } //1 second  = 1000 million second

    public int getSeconds(){
        return seconds;
    }

    public void stopTimer(){
        t.stop();
    }

    public void startTimer(){
        t.start();
    }

    public void addSeconds(){
        seconds++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addSeconds();
    }

}
