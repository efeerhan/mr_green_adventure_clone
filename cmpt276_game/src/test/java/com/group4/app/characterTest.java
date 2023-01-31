package com.group4.app;

import org.junit.Test;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static org.junit.jupiter.api.Assertions.*;

public class characterTest {
    character _character = new character();
    character.spriteAnimListener anim = _character.new spriteAnimListener();

    @Test
    public void shouldCreateCharacter() {
        assertNotNull( _character);
    }

    @Test
    public void shouldGetDirection() {
        int _direction = _character.getDirection();
        assert(_direction >= -1 && _direction <= 270);
    }

    @Test
    public void testKeyReleased() {
        KeyEvent e = new KeyEvent(_character, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UNDEFINED, KeyEvent.CHAR_UNDEFINED);
        _character.keyReleased(e);
        assertEquals(-1, _character.getDirection());
    }

    @Test
    public void testKeyPressed() {
        KeyEvent l = new KeyEvent(_character, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, '←');
        _character.keyPressed(l);
        assertEquals(180, _character.getDirection());

        KeyEvent u = new KeyEvent(_character, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, '↑');
        _character.keyPressed(u);
        assertEquals(90, _character.getDirection());

        KeyEvent d = new KeyEvent(_character, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, '↓');
        _character.keyPressed(d);
        assertEquals(270, _character.getDirection());

        KeyEvent r = new KeyEvent(_character, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, '→');
        _character.keyPressed(r);
        assertEquals(0, _character.getDirection());
    }

    @Test
    public void testActionPerformed() {
        ActionEvent e = new ActionEvent(_character.t1, 0, null);
        int x = _character.getLocation().x;
        int y = _character.getLocation().y;
        KeyEvent l = new KeyEvent(_character, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, '←');
        _character.keyPressed(l);
        _character.t1.stop();
        _character.actionPerformed(e);
        assert(_character.getLocation().x == x-10 && _character.getLocation().y == y);
    }

    @Test
    public void shouldSetLocation(){
        int n = 5;
        _character.setLocation(n, n);
        assertEquals(n, _character.getX());
        assertEquals(n, _character.getY());
    }

    @Test
    public void shouldCreateSpriteAnimListener() {
        assertNotNull( anim);
    }
    @Test
    public void shouldAdjust(){
        int w = 61;
        int h = 60;

        anim.adjust(w, h);

        assertEquals(w, _character.collision.getWidth(),0);
        assertEquals(h, _character.collision.getHeight(),0);
        assertEquals((64-w)/2, _character.drawX);
        assertEquals((64-h)/2, _character.drawY);
    }

    @Test
    public void testAnimFrameUpdater(){
        int aF = anim.animFrame;
        anim.animFrameUpdater();
        assertTrue(anim.animFrame == 1 || anim.animFrame == aF-1);
    }

    @Test
    public void testActionPerformed_sAL(){
        anim.t2 = _character.t1;
        ActionEvent e = new ActionEvent(anim.t2, 0, null);
        KeyEvent l = new KeyEvent(_character, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, '←');
        _character.keyPressed(l);
        //direction set to 180
        anim.actionPerformed(e);
        assertTrue(_character.collision.getWidth() == 60 ||_character.collision.getHeight() == 60);
    }
}