package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;

public class SecondPlayer extends Player {
    public SecondPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "Il Ritardatario";
        this.playerN = 2;
    }

    @Override
    public void createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getPullChronology()[pullChronology.length - 1 - i];
        }
        Database.getInstance(5, 18).addPlayerBet(playerN, bet);
    }
}
