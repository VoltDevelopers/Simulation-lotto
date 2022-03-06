package com.voltdevelopers.lotto.src.players;

import com.voltdevelopers.lotto.data.Database;

public class FifthPlayer extends Player {
    public FifthPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "L'ingenuo";
    }

    @Override
    public int[] createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getMostPulled()[i];
        }
        return bet;
    }
}
