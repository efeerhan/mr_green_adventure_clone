package com.group4.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;

public class BarrierIntegrationTest {
   
    @Test
    @DisplayName("test addBarrier method")
    public void drawBarrierTest() {
        int x=2;int y=3;
        Board board1 = new Board(null);
        board1.addBarrier(x,y);
        //check whether the point is null after drawing 
        if(checkGridSpot(x,y)){
            System.out.println("Failed at drawing barrier");

        }else{
            System.out.println("Succeed at drawing barrier");
        }
        

    }
    @Test
    @DisplayName("check if the point what to draw barrier at is not null")
    public void drawBarrierFullTest(){
        int x=2;int y=3;
        Board board1 = new Board(null);
        board1.addBarrier(2,3);
        board1.addBarrier(x, y);
    }
    private boolean checkGridSpot(int x, int y) {
        return false;
    }
}
