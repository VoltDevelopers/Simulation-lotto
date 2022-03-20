package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.data.Settings;

public class FifthPlayer extends Player {

    /*
   Pattern V:
       Gioca il numero uscito più frequentemente
   */

    public FifthPlayer() {
        this.id = 4;
        this.bet = new int[extractions];
    }

    @Override
    public void createBet() {
        this.bet = Database.getInstance().getNMostFrequent(extractions);
    }
}