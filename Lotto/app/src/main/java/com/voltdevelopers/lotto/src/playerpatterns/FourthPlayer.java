package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.src.game.NumberGenerator;

public class FourthPlayer extends Player {

    private int[] bet;
    private NumberGenerator gen;

    public FourthPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "Il testardo";

        gen = new NumberGenerator();
        bet = gen.numSeries(numsPerTurn); //creazione di una giocata fissa
    }

    @Override
    public void createBet() {
        Database.getInstance(5, 18).addPlayerBet(playerN, bet);
    }
}
