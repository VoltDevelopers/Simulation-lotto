package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class FirstPlayer extends Player {

    /*
    Pattern I:
        Gioca un numero della cinquina precedente
    */

    public FirstPlayer() {
        this.id = 0;
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