package com.group4.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bonus extends Collectible{

    /**
     * Bonus point that gives the player 3 points. These 3 points do not go towards
     * the player's progress in the game, but serve as a padding to their score to
     * make them feel better.
     *
     * @param x the bonus' X-coordinate on screen
     * @param y the bonus' Y-coordinate on screen
     */

    int bonusScore = 3;

    public Bonus(int x, int y) {
        ////This method is meant to draw the Bonus cell at position with X-cooridnate x, Y-coordinate Y
        super(x, y);
        try {
            image = ImageIO.read((getClass().getResourceAsStream("/bonus.png")));
        }catch (IOException e) { System.out.println("Bonus Sprite not found"); }

    }

    public int getBonusScore(){
        return bonusScore;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 16, 0, this);
    }
    // Override to paint the connect toggle

    @Override
    public String getIdentifier() {
        return "BR";
    }
}
