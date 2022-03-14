package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.src.game.NumberGenerator;

public class ThirdPlayer extends Player {

    private NumberGenerator gen;

    public ThirdPlayer(int numsPerTurn) {
        super(numsPerTurn);
        gen       = new NumberGenerator();
        this.name = "L'azzardoso";
        this.playerN = 3;
    }

    @Override
    public void createBet() {

        Database.getInstance(5, 18).addPlayerBet(playerN, gen.numSeries(numsPerTurn));
    }
}
