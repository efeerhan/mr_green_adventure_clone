package com.group4.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreTrackerViewTest{

    @Test
    @DisplayName("test for initialize")
    public void viewTest(){
        ScoreTrackerView sTracker = new ScoreTrackerView();
        //check location
        assertEquals(100, sTracker.getHolder().getX());
        assertEquals(30, sTracker.getHolder().getY());
        //check initialized two marks
        assertEquals(0,sTracker.getTotalScore());
        assertEquals(0,sTracker.getTotalRRewards());
    }

    @Test
    public void addMarksTest(){
        ScoreTrackerView sTracker = new ScoreTrackerView();
        ScoreTracker scoreTracker = new ScoreTracker();

        scoreTracker.addTotalScore(1);
        sTracker.addTotalScore(1);
        assertEquals(scoreTracker.getTotalScore(), sTracker.getTotalScore());

        scoreTracker.addTotalScore(2);
        sTracker.addTotalScore(2);
        assertEquals(scoreTracker.getTotalScore(), sTracker.getTotalScore());

        scoreTracker.addTotalScore(3);
        sTracker.addTotalScore(3);
        assertEquals(scoreTracker.getTotalScore(), sTracker.getTotalScore());

        scoreTracker.addTotalScore(4);
        sTracker.addTotalScore(4);
        assertEquals(scoreTracker.getTotalScore(), sTracker.getTotalScore());

        scoreTracker.addTotalScore(210);
        sTracker.addTotalScore(210);
        assertEquals(scoreTracker.getTotalScore(), sTracker.getTotalScore());

        scoreTracker.addTotalScore(-12);
        sTracker.addTotalScore(-12);
        assertEquals(scoreTracker.getTotalScore(), sTracker.getTotalScore());

        scoreTracker.addTotalScore(0);
        sTracker.addTotalScore(0);
        assertEquals(scoreTracker.getTotalScore(), sTracker.getTotalScore());

        scoreTracker.addTotalScore(-1);
        sTracker.addTotalScore(-1);
        assertEquals(scoreTracker.getTotalScore(), sTracker.getTotalScore());

    }

    @Test
    public void addRealMarksTest(){
        ScoreTracker scoreTracker = new ScoreTracker();
        ScoreTrackerView sTracker = new ScoreTrackerView();

        scoreTracker.addTotalRRewards(1);
        sTracker.addTotalRRewards(1);
        assertEquals(scoreTracker.getTotalRRewards(), sTracker.getTotalRRewards());

        scoreTracker.addTotalRRewards(2);
        sTracker.addTotalRRewards(2);
        assertEquals(scoreTracker.getTotalRRewards(), sTracker.getTotalRRewards());

        scoreTracker.addTotalRRewards(8);
        sTracker.addTotalRRewards(8);
        assertEquals(scoreTracker.getTotalRRewards(), sTracker.getTotalRRewards());

        scoreTracker.addTotalRRewards(9);
        sTracker.addTotalRRewards(9);
        assertEquals(scoreTracker.getTotalRRewards(), sTracker.getTotalRRewards());

        scoreTracker.addTotalRRewards(100);
        sTracker.addTotalRRewards(100);
        assertEquals(scoreTracker.getTotalRRewards(), sTracker.getTotalRRewards());

        scoreTracker.addTotalRRewards(-1);
        sTracker.addTotalRRewards(-1);
        assertEquals(scoreTracker.getTotalRRewards(), sTracker.getTotalRRewards());

        scoreTracker.addTotalRRewards(-190);
        sTracker.addTotalRRewards(-190);
        assertEquals(scoreTracker.getTotalRRewards(), sTracker.getTotalRRewards());
    }
}
