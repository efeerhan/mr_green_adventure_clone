package com.group4.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private static Board board;

    @BeforeAll
    static void setup(){
        board = new Board(null);
    }

    @BeforeEach
    public void cleanup(){
        for ( int i = 0; i < 12; i++ ){
            for ( int j = 0; j < 7; j++ ){
                board.grid[i][j] = null;
            }
        }
        board.removeAll();
    }

    @Test
    @DisplayName("BoardSetupTest")
    public void constructorTest(){
        assert(!board.t.isRunning());
        assertNotNull(board.enemyStream);
        assertNotNull(board.rewardStream);
        assertNotNull(board.rewardClip);
        assertNotNull(board.enemyClip);
        assertNotNull(board.tempSound);
    }

    @Test
    public void addTest() {
        board.addBarrier(1,1);
        board.addRegularRewards(1,2);
        board.addBonus(1,3);
        board.addNonAnimatedEnemy(1,4);
        board.addMovingEnemy(1,5);
        for ( int i = 1; i <=5; i++ )
            assertNotNull(board.grid[1][i]);
    }

    @Test
    public void collectTest(){
        board.window = new AppWindow();
        board.window.setVisible(false);
        board.addRegularRewards(1,1);
        board.collectPoint(1,1,"RR");
        assertNull(board.grid[1][1]);
        //Cleanup
        board.window.dispose();
    }

    @Test
    public void playerCollisionTest(){
        board.addBarrier(1,1);
        board.addPlayer(new character());
        board.player.setLocation(192,368);
        assert( board.checkPlayerCollision() == 14 );
    }

    @Test
    public void enemyCollisionTest(){
        board.addMovingEnemy(0,0);
        board.mEnemy.setLocation(192,240);
        assert(board.checkMEnemyCollision() == 4 );
    }
}
