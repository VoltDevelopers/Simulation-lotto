package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.game.NumberGenerator;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class ThirdPlayer extends Player {

    public ThirdPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.playerN = 3;
        this.bet = new int[numsPerTurn];
    }

    @Override
    public void createBet() {
        try {
            bet = StdRandom.getRandomArray(bet.length, 90);
        } catch (InputException e) {
            e.printStackTrace();
        }
    }
}
