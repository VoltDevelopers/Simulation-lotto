package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class FourthPlayer extends Player {

    public FourthPlayer(int numsPerTurn) {
        super(numsPerTurn);
        try {
            this.bet = StdRandom.getRandomArray(1, 90);
        } catch (InputException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createBet() {
    }
}
