package com.group4.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

class slotComponent extends baseElement{

    /**
     * Components that fit into each of the 4 empty spots on the Board: Top, Left, Bottom, and Right.
     * Can either be a wall or a door leading to another Board.
     */

    private final String type;
    private final String position;
    int wall_x = 0; int wall_y = 0; int door_x = 0; int door_y = 0;

    public slotComponent(String comp, String pos){
        type = comp; position = pos;
        try {
            if ( pos.equals("left" )){
                wall_x = 0; wall_y = 464;
                door_x = 64; door_y = 464;
                if ( comp.equals("wall") ) {
                    image = ImageIO.read((getClass().getResourceAsStream("/slotWallLeft.png")));
                    collision = new collisionBox(wall_x+64,wall_y,64,64);
                }
                if ( comp.equals("door") ) {
                    image = ImageIO.read((getClass().getResourceAsStream("/slotDoorLeft.png")));
                    collision = new collisionBox(door_x,door_y,6,64);
                }
            }
            if ( pos.equals("right") ){
                wall_x = 896; wall_y = 464;
                door_x = 967; door_y = 464;
                if ( comp.equals("wall") ) {
                    image = ImageIO.read((getClass().getResourceAsStream("/slotWallRight.png")));
                    collision = new collisionBox(wall_x,wall_y,64,64);
                }
                if ( comp.equals("door") ) {
                    image = ImageIO.read((getClass().getResourceAsStream("/slotDoorRight.png")));
                    collision = new collisionBox(door_x,door_y,6,64);
                }
            }
            if ( pos.equals("top") ){
                wall_x = 448; wall_y = 176;
                door_x = 447; door_y = 239;
                if ( comp.equals("wall") ) {
                    image = ImageIO.read((getClass().getResourceAsStream("/slotWallTop.png")));
                    collision = new collisionBox(wall_x,wall_y+64,64,64);
                }
                if ( comp.equals("door") ) {
                    image = ImageIO.read((getClass().getResourceAsStream("/slotDoorTop.png")));
                    collision = new collisionBox(door_x,door_y,64,6);
                }
            }
            if ( pos.equals("bottom") ){
                wall_x = 448; wall_y = 752;
                door_x = 448; door_y = 827;
                if ( comp.equals("wall") ) {
                    image = ImageIO.read((getClass().getResourceAsStream("/slotWallBottom.png")));
                    collision = new collisionBox(wall_x,wall_y,64,64);
                }
                if ( comp.equals("door") ) {
                    image = ImageIO.read((getClass().getResourceAsStream("/slotDoorBottom.png")));
                    collision = new collisionBox(door_x,door_y,64,6);
                }
            }
            setLocation(wall_x,wall_y);
        } catch(IOException e){ System.out.println("wall slot source not found"); }
        setSize(128,128);
        setDoubleBuffered(true);
        repaint();
    }
    public String getType(){
        return type;
    }
    public String getPosition(){
        return position;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public String toString(){
        return position + " " + type;
    }
}
