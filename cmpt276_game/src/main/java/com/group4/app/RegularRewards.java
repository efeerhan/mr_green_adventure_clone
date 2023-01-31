package com.group4.app;
import java.awt.*;
import javax.imageio.ImageIO;

import java.io.IOException;

class RegularRewards extends Collectible{

    /**
     * The main points that are found scattered across the maze, that increment the player's Marks and realMarks
     * by 1. The player must collect all 17 to win the game.
     */
    private int mark = 1;
    
    public int getRRewardsScore(){
        return mark;
    }

    public RegularRewards(int x, int y){
        //This method is meant to draw the regularRewards cell at position with X-cooridnate x, Y-coordinate Y
        super(x, y);
        try {
            image = ImageIO.read((getClass().getResourceAsStream("/point.png")));
        } catch(IOException e){ System.out.println("Point Sprite source not found"); }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 16, 0, this);
    }
    // Override to paint the connect toggle
    @Override
    public String getIdentifier() {
        return "RR";
    }
    
}
