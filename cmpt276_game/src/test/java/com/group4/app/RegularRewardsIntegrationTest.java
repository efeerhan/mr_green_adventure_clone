package com.group4.app;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class RegularRewardsIntegrationTest {
    @Test
    @DisplayName("test  whether adding rewards mark succeed (1 points for RegularRewards)")
    public void addRewardsMarkTest(){
        ScoreTrackerView scoreTrackerView = new ScoreTrackerView();
        ScoreTracker scoreTracker = new ScoreTracker();

        scoreTracker.addTotalScore(1);
        scoreTrackerView.addTotalScore(1);
        assertEquals(1, scoreTrackerView.getTotalScore());
        assertEquals(1, scoreTracker.getTotalScore());

    }
    @Test
    @DisplayName("test addRegularRewards method")
    public void drawRewardTest() {
        int x=2;int y=3;
        Board board1 = new Board(null);
        board1.addRegularRewards(x,y);
        //check whether the point is null after drawing 
        if(checkGridSpot(x,y)){
            System.out.println("Failed at drawing rewards");

        }else{
            System.out.println("Succeed at drawing rewards");
        }
   
    
 }
    @Test
    @DisplayName("check what is print out if the point want to draw at is not null")
    public void drawRewardsFullTest(){
        int x=2;int y=3;
        Board board1 = new Board(null);
        board1.addRegularRewards(2,3);
        board1.addRegularRewards(x, y);
 }
    private boolean checkGridSpot(int x, int y) {
        return false;
    }
    
   
}
