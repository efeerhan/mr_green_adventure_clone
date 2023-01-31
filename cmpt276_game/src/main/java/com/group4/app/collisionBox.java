package com.group4.app;

import java.awt.*;

class collisionBox extends Rectangle {

    /**
     * A simple Rectangle wrapper class meant to utilize its functionality and also
     * eliminate confusion about its purpose with a rename.
     */

    public collisionBox(int x, int y, int width, int height){
        setSize(width, height);
        setLocation(x, y);
    }
}