package com.group4.app;

public class Collectible extends baseElement{
    public Collectible(int x, int y){
        setLocation(x,y);
        collision = new collisionBox(getX()+16,getY(),32,64);
        setSize(64,64);
        setDoubleBuffered(true);
    }
    public String getIdentifier() {
        return null;
    }
}
