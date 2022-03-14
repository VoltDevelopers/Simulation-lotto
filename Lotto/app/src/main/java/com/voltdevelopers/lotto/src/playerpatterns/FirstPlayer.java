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
        int[] bet;
        bet = Database.getInstance(5, 18).getLatestN(5);
        Database.getInstance(5, 18).addPlayerBet(playerN, bet);
    }
}
