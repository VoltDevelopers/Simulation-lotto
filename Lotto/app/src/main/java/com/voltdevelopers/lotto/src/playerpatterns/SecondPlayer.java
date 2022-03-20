package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.data.Settings;

public class SecondPlayer extends Player {

    /*
   Pattern II:
       Gioca il numero che non esce da più estrazioni
   */

    public SecondPlayer() {
        this.id = 1;
        this.bet = new int[extractions];
    }

    @Override
    public void createBet() {
        this.bet = Database.getInstance().getOldestN(extractions);
    }
}