package com.group4.app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TimeTrackerView extends Tracker implements ActionListener {

    /**
     * The visual aspect of TimeTracker. Split from TimeTracker for ease of testing.
     */

    TimeTracker timeTracker;

    //initialize timeTracker and set its location
    public TimeTrackerView(){
        super();
        timeTracker = new TimeTracker(this);
        timeTracker.startTimer();
        holder.setLocation(650,30);
        refresh();
    }

    //display seconds on the screen in certain position
    private void refresh(){
        holder.remove(display);
        holder.repaint();
        display = new JLabel("Time: " + timeTracker.getSeconds(), SwingConstants.LEFT); //display seconds
        display.setForeground(Color.white); //white words
        display.setFont(customFont);
        display.setSize(400,120);
        holder.add(display);
        display.repaint();
        holder.repaint();
    }

    //keep add seconds and refresh the window
    @Override
    public void actionPerformed(ActionEvent e) {
        timeTracker.actionPerformed(e);
        refresh();
    }

    public void stopTimer(){
        timeTracker.stopTimer();
    }
}

