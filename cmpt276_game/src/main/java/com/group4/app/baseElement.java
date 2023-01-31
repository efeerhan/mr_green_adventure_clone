package com.group4.app;

import javax.swing.*;
import java.awt.image.BufferedImage;

class baseElement extends JComponent {

    /**
     *The base class that Character, Barrier, Enemy, Moving Enemy, Regular Rewards,
     * and Bonus inherit from. Contains collision data and a BufferedImage for a sprite.
     */

    protected BufferedImage image;
    protected collisionBox collision;
    protected collisionBox getCollision(){
        return collision;
    }
}