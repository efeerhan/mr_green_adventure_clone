package com.group4.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;

public class NonAnimatedEnemy extends Collectible implements ActionListener {

    /**
     * The stationary enemy that subtracts 1 point from the player when touched. Swaps between 2 sprites
     * as an idle animation. Inherits from Regular Rewards in order to implement the framework of scoring,
     * and has a score value of -1.
     */

    final protected BufferedImage[] sprites = new BufferedImage[2];
    protected int frameCount = 0;
    protected Timer t;

    public NonAnimatedEnemy(int x, int y){
        super(x,y);
        t = new Timer(750,this);
        t.start();
        try{
            sprites[0] = ImageIO.read((getClass().getResourceAsStream("/stalfos1.png")));
            sprites[1] = ImageIO.read((getClass().getResourceAsStream("/stalfos2.png")));
            image = sprites[0];
        }catch(IOException e){ System.out.println("NonAnimatedEnemy Sprite not found"); }
        collision = new collisionBox(getX()+27,getY()+27,10,10);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage(image, 4, 4, this);
    }

    @Override
    public String getIdentifier(){
        return "NAE";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( frameCount == 0 )
            frameCount++;
        else
            frameCount--;
        image = sprites[frameCount];
        repaint();
    }
}
