package com.group4.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private static AppWindow window;

    @BeforeAll
    static void setup(){
        window = new AppWindow();
        window.setVisible(false);
    }


    @Test
    @DisplayName("Setup Test")
    public void constructorTest(){
        assertNotNull(window);
        assertNotNull(window.gettTracker());
        assertNotNull(window.getsTracker());
        //assertNotNull(window.getAudioFile());
    }

    @Test
    @DisplayName("ScoreTracker Test")
    public void updateScoreTrackerTest() {
        window.updateTotalScoreTracker(100);
        assertEquals(100, window.getsTracker().getTotalScore());
        window.updateTotalRRewardsTracker(100);
        assertEquals(100, window.getsTracker().getTotalScore());
        assertEquals(100, window.getsTracker().getTotalRRewards());
    }

    @Test
    @DisplayName("Integrated AppWindow and Board")
    public void integratedWindowAndBoardTest(){
        Board board1 = new Board(window);
        Board board2 = new Board(window);
        board1.setDown(board2);
        board2.setUp(board1);
        board1.addPlayer(new character());
        window.addBoard(board1);
        window.setBoard(board1);
        assertNotNull(board1.window);
        assertNotNull(board2.window);
        assertNotNull(window.currentBoard);
        assert(board1.t.isRunning());
        window.changeBoard(board2,0,0);
        assert(!board1.t.isRunning());
        assert(board2.t.isRunning());
        assertEquals(window.currentBoard, board2);
        window.changeBoard(board1,0,0);
        assertEquals(window.currentBoard, board1);
        board1.addRegularRewards(0,0);
        board1.addNonAnimatedEnemy(0,1);
        board1.collectPoint(0,0,"RR");
        assert(window.getsTracker().getTotalScore() == 101);
        board1.collectPoint(0,1,"NAE");
        assert(window.getsTracker().getTotalScore() == 100);
    }
}
