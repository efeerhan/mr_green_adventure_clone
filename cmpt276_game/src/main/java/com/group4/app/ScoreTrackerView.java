package com.group4.app;

import javax.swing.*;
import java.awt.*;

class ScoreTrackerView extends Tracker {

    /**
     * ScoreTrackerView handles the visual aspect of the Score. Split from ScoreTracker for ease of testing.
     */

    ScoreTracker scoreTracker = new ScoreTracker(); //initialize scoreTracker

    public ScoreTrackerView() {
        super();
        holder.setLocation(100, 30);
        addTotalScore(0);
    }

    //add totalScore and display totalScore
    public void addTotalScore(int i) {
        scoreTracker.addTotalScore(i);
        holder.remove(display);
        holder.repaint();

        //change display content
        display = new JLabel("Score: " + getTotalScore(), SwingConstants.LEFT); //display totalScore
        display.setForeground(Color.white); //white words
        display.setFont(customFont);
        display.setSize(400, 120);
        holder.add(display);

        //paint again
        display.repaint();
        holder.repaint();
    }

    //total regular rewards does not need to display
    public void addTotalRRewards(int i) {
        scoreTracker.addTotalRRewards(i);
    }

    //getters
    public int getTotalScore(){
        return scoreTracker.getTotalScore();
    }

    public int getTotalRRewards() {
        return scoreTracker.getTotalRRewards();
    }

    //get panel holder
    public JPanel getHolder() {
        return holder;
    }
}
