package com.group4.app;

import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;

public class TimeTrackerIntegrationTest {

    Board currentBoard;
    AppWindow window = new AppWindow();
    Board board1 = new Board(window);

    @Test
    public void TimeTrackerTest() throws InterruptedException {
        window.setVisible(false);
        currentBoard = board1;
        currentBoard.startTimer();
        TimeUnit.SECONDS.sleep(5);
        currentBoard.stopTimer();
    }

}
