package com.group4.app;

public class ScoreTracker {

    /**
     *Class that tracks the player's progress in the game. Since the bonus does not count as
     * an actual score, we have marks, which gets incremented by coins AND bonus, and realMarks
     * which gets incremented only by coins.
     */

    private int totalScore = 0; //count player's total score
    private int totalRRewards = 0; //count the regular reward player got

    public void addTotalScore(int i) {
        totalScore += i;
    }

    public void addTotalRRewards(int i) {
        totalRRewards += i;
    }


    //two getter functions
    public int getTotalScore(){
        return totalScore;
    }

    public int getTotalRRewards() {
        return totalRRewards;
    }
}
