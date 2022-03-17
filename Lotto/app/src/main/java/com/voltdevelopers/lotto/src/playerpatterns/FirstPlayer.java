package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;

public class FirstPlayer extends Player {

    /*
    Pattern I:
        Gioca un numero della cinquina precedente
    */

    public FirstPlayer() {
        this.id = 0;
        this.bet = new int[extractions];
    }

    @Override
    public void createBet() {
        bet = Database.getInstance().getLatestN(5);
    }
}