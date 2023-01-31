package com.group4.app;

import static org.junit.Assert.*;
import java.awt.event.ActionEvent;
import org.junit.Test;

public class MovingEnemyTest {

    MovingEnemy enemy = new MovingEnemy(0,0);
    @Test
    public void shouldCreateMovingEnemy(){
        assertNotNull(enemy);
    }

    @Test
    public void shouldAdjust(){
        int w = 61;
        int h = 60;

        enemy.adjust(61, 60);

        assertEquals(w, enemy.collision.getWidth(),0);
        assertEquals(h, enemy.collision.getHeight(),0);
        assertEquals((64-w)/2, enemy.drawX);
        assertEquals((64-h)/2, enemy.drawY);
    }

    @Test
    public void shouldGetDirection(){
        int _direction = enemy.getDirection();
        assertTrue(_direction >= -1 && _direction <=270);
    }

    @Test
    public void shouldGetIdentifier(){
        assertEquals("ME", enemy.getIdentifier());
    }

    @Test
    public void shouldSetLocation(){
        int n = 5;
        enemy.setLocation(n, n);
        assertEquals(n, enemy.getX());
        assertEquals(n, enemy.getY());
    }

    @Test
    public void testActionPerformed(){
        ActionEvent e = new ActionEvent(enemy.t, 0, null);

        int x1 = enemy.getX();
        int y1 = enemy.getY();
        enemy.setNoMove(-1);
        enemy.actionPerformed(e);
        assertTrue(enemy.getX() !=x1 || enemy.getY() != y1);

        enemy.frameCount = 0;
        enemy.actionPerformed(e);
        enemy.t.stop();
        assertEquals(1, enemy.frameCount);
    }
}
