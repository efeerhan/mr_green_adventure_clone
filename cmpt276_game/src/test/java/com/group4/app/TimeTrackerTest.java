package com.group4.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TimeTrackerTest {

    @Test
    public void addSecondsTest(){
        TimeTracker tTracker = new TimeTracker(null);

        assertEquals(0, tTracker.getSeconds());
        tTracker.addSeconds();
        assertEquals(1, tTracker.getSeconds());

        for(int i = 0; i < 10; i++){
            tTracker.addSeconds();
        } //after loop, marks = 11
        assertEquals(11, tTracker.getSeconds());
    }


}
