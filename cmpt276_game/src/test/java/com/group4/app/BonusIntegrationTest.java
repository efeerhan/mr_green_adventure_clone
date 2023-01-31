package com.group4.app;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BonusIntegrationTest {
  
    @Test
    @DisplayName("test  whether adding bonus mark succeed (2 points for bonus)")
    public void addBonusMarkTest(){
        ScoreTrackerView scoreTrackerView = new ScoreTrackerView();
        ScoreTracker scoreTracker = new ScoreTracker();

        scoreTracker.addTotalScore(2);
        scoreTrackerView.addTotalScore(2);
        assertEquals(2, scoreTrackerView.getTotalScore());
        assertEquals(2, scoreTracker.getTotalScore());

    }
    @Test
    @DisplayName("test addBonus method")
    public void drawBonusTest() {
        int x=2;int y=3;
        Board board1 = new Board(null);
        board1.addBonus(x,y);
        //check whether the point is null after drawing bonus
        if(checkGridSpot(x,y)){
            System.out.println("Failed at drawing bonus");

        }else{
            System.out.println("Succeed at drawing bonus");
        }
    }
    @Test
    @DisplayName("check what would happen if the point want to draw at is not null")
    public void drawBonusFullTest(){
        int x=2;int y=3;
        Board board1 = new Board(null);
        board1.addBonus(2,3);
        board1.addBonus(x, y);

    }
    private boolean checkGridSpot(int x, int y) {
        return false;
    }
    
   
}
