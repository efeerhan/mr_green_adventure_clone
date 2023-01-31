package com.group4.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreTrackerTest {

    @Test
    public void addMarksTest(){
        ScoreTracker sTracker = new ScoreTracker();

        sTracker.addTotalScore(0);
        assertEquals(0, sTracker.getTotalScore());

        for(int i = 0; i < 10; i++){
            sTracker.addTotalScore(1);
        } //after loop, marks = 10
        assertEquals(10, sTracker.getTotalScore());

        //test for deleting 1
        for (int i = 0; i < 9; i++) {
            sTracker.addTotalScore(-1);
        } //after loop, marks = 1
        assertEquals(1, sTracker.getTotalScore());

        sTracker.addTotalScore(9);
        assertEquals(10, sTracker.getTotalScore());
        sTracker.addTotalScore(-7);
        assertEquals(3, sTracker.getTotalScore());
    }

    @Test
    public void addRealMarksTest() {
        ScoreTracker sTracker = new ScoreTracker();
        sTracker.addTotalRRewards(0);
        assertEquals(0, sTracker.getTotalRRewards());

        //test for adding 1
        for(int i = 0; i < 10; i++){
            sTracker.addTotalRRewards(1);
        } //after loop, marks = 10
        assertEquals(10, sTracker.getTotalRRewards());

        //test for deleting 1
        for(int i = 0; i < 9; i++){
            sTracker.addTotalRRewards(-1);
        } //after loop, marks = 1
        assertEquals(1, sTracker.getTotalRRewards());

        sTracker.addTotalRRewards(9);
        assertEquals(10, sTracker.getTotalRRewards());
        sTracker.addTotalRRewards(-7);
        assertEquals(3, sTracker.getTotalRRewards());
    }
}

