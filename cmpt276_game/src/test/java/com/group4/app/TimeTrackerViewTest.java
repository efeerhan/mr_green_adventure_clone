package com.group4.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTrackerViewTest {

    @Test
    public void ViewTest(){

        TimeTrackerView tTracker = new TimeTrackerView();
        TimeTracker timeTracker = new TimeTracker(null);
        timeTracker.startTimer();

        //test for initialize
        assertEquals(650, tTracker.getHolder().getX());//test location
        assertEquals(30, tTracker.getHolder().getY());
    }
}
