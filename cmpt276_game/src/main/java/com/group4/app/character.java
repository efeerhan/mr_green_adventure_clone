package com.group4.app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

class character extends baseElement implements ActionListener, KeyListener {

    /**
     * Character is the Playable Character in this game. This class handles movement, has a timer to read input,
     * and a nested class to handle its animation on its own timer and methods.
     */

    private int noMove = -1;
    private int direction = -1;
    protected int drawX = 0; protected int drawY = 0;

    final private BufferedImage[] spritesLeft = new BufferedImage[2];
    final private BufferedImage[] spritesRight = new BufferedImage[2];
    final private BufferedImage[] spritesUp = new BufferedImage[2];
    final private BufferedImage[] spritesDown = new BufferedImage[2];
    final private BufferedImage[][] sprites = new BufferedImage[][] { spritesLeft,spritesRight,spritesUp,spritesDown };

    protected Timer t1;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        new spriteAnimListener();
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT: direction = 180;
                break;
            case KeyEvent.VK_UP: direction = 90;
                break;
            case KeyEvent.VK_DOWN: direction = 270;
                break;
            case KeyEvent.VK_RIGHT: direction = 0;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        direction = -1;
    }

    public class spriteAnimListener implements ActionListener{
        protected int animFrame = 0;
        public Timer t2;

        public spriteAnimListener(){
            Timer t2 = new Timer(100, this);
            t2.start();
        }

        protected void adjust(int width, int height){
            collision.setSize(width, height);
            drawX = (64-width)/2;
            drawY = (64-height)/2;
        }

        protected void animFrameUpdater(){
            if ( animFrame == 0 )
                animFrame++;
            else {
                animFrame--;
            }
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (direction == 90) {
                animFrameUpdater();
                image = sprites[2][animFrame];
                adjust(48,64);
            }
            if (direction == 270){
                animFrameUpdater();
                image = sprites[3][animFrame];
                if ( animFrame == 0 )
                    adjust(61,64);
                else
                    adjust(52,60);
            }
            if (direction == 180) {
                animFrameUpdater();
                image = sprites[0][animFrame];
                if ( animFrame == 0 )
                    adjust(60,64);
                else
                    adjust(56,60);
            }
            if (direction == 0) {
                animFrameUpdater();
                image = sprites[1][animFrame];
                if ( animFrame == 0 )
                    adjust(60,64);
                else
                    adjust(56,60);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, drawX, drawY, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( direction == 180 && noMove != 180 ) {
            setLocation(getX() - 10, getY());
            collision.setLocation(getX() - 10, getY());
        }
        if ( direction == 0 && noMove != 0 ) {
            setLocation(getX()+10,getY());
            collision.setLocation(getX() + 10, getY());
        }
        if ( direction == 90 && noMove != 90 ) {
            setLocation(getX(), getY() - 10);
            collision.setLocation(getX(), getY() - 10);
        }
        if ( direction == 270 && noMove != 270 ) {
            setLocation(getX(), getY() + 10);
            collision.setLocation(getX(), getY() + 10);
        }
        requestFocusInWindow();
        repaint();
    }

    private void readSprites() {
        try {
            sprites[0][0] = ImageIO.read((getClass().getResourceAsStream("/LinkL1.png")));
            sprites[0][1] = ImageIO.read((getClass().getResourceAsStream("/LinkL2.png")));
            sprites[1][0] = ImageIO.read((getClass().getResourceAsStream("/LinkR1.png")));
            sprites[1][1] = ImageIO.read((getClass().getResourceAsStream("/LinkR2.png")));
            sprites[2][0] = ImageIO.read((getClass().getResourceAsStream("/LinkU1.png")));
            sprites[2][1] = ImageIO.read((getClass().getResourceAsStream("/LinkU2.png")));
            sprites[3][0] = ImageIO.read((getClass().getResourceAsStream("/LinkD1.png")));
            sprites[3][1] = ImageIO.read((getClass().getResourceAsStream("/LinkD2.png")));
            image = sprites[3][0];
        } catch(IOException e){ System.out.println("Character Sprite source not found"); }
    }

    public void refresh(){
        noMove = -1;
        t1.restart();
    }

    public character(){
        setLocation(160,658);
        collision = new collisionBox(getX(),getY(),64,64);
        addKeyListener(this);
        setSize(64,64);
        t1 = new Timer(50, this);
        t1.start();
        readSprites();
        setDoubleBuffered(true);
        setFocusable(true);
    }

    public int getDirection(){
        return direction;
    }
    public void setNoMove(int direction){
        noMove = direction;
    }
}
