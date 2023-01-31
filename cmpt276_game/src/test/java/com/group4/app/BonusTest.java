package com.group4.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusTest {

    @Test
    public void BonusScoreTest() {
        Bonus b = new Bonus((1 * 64) + 128, (1 * 64) + 304);
        int score = b.getBonusScore();

        if (score > 3 || score < 1) {
            assert (false);


        }
    }
}
