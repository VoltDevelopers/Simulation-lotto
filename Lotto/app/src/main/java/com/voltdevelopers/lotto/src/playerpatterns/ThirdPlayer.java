package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.src.game.NumberGenerator;

public class ThirdPlayer extends Player {

    private NumberGenerator gen;

    public ThirdPlayer(int numsPerTurn) {
        super(numsPerTurn);
        gen       = new NumberGenerator();
        this.name = "L'azzardoso";
    }

    @Override
    public void createBet() {

        //Database.get().addPlayerBet(5, gen.fiveNumSeries(numsPerTurn));
    }
}
