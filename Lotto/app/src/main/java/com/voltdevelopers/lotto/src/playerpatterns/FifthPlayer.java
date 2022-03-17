package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class FifthPlayer extends Player {

    /*
   Pattern V:
       Gioca il numero uscito più frequentemente
   */

    public FifthPlayer() {
        this.id = 4;
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