package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;

public class FifthPlayer extends Player {
    public FifthPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "L'ingenuo";
    }

    @Override
    public void createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getMostPulled()[i];
        }
        Database.getInstance(5, 18).addPlayerBet(playerN, bet);
    }
}
