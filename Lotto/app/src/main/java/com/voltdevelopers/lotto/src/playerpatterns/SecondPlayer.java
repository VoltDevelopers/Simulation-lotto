package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class SecondPlayer extends Player {

    /*
   Pattern II:
       Gioca il numero che non esce da più estrazioni
   */

    public SecondPlayer() {
        this.id = 1;
        this.bet = new int[extractions];
    }

    @Override
    public void createBet() {
        try {
            this.bet = StdRandom.getRandomArray(extractions, 90);
        } catch (InputException e) {
            e.printStackTrace();
        }
    }
}