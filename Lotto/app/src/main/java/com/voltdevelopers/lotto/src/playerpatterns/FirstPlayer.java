package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;

public class FirstPlayer extends Player {

    public FirstPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.playerN = 1;
        this.bet = new int[numsPerTurn];
    }

    @Override
    public void createBet() {
        bet = Database.getInstance(5, 18d).getLatestN(5);
    }
}
