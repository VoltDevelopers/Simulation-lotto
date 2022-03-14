package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;

public class FirstPlayer extends Player {

    public FirstPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "Il Copione";
        this.playerN = 1;
    }

    @Override
    public void createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getLastestN(5);
        }
        Database.getInstance(5, 18).addPlayerBet(playerN, bet);
    }
}
