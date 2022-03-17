package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class ThirdPlayer extends Player {

    /*
   Pattern III:
       Gioca il numero che non esce da più estrazioni
   */

    public ThirdPlayer() {
        this.id = 2;
        this.bet = new int[extractions];
    }

    @Override
    public void createBet() {
        try {
            bet = StdRandom.getRandomArray(extractions, 90);
        } catch (InputException e) {
            e.printStackTrace();
        }
    }
}