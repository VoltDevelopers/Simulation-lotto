package com.voltdevelopers.lotto.src.playerpatterns;

import android.provider.ContactsContract;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class SecondPlayer extends Player {

    /*
   Pattern II:
       Gioca il numero che non esce da più estrazioni
   */

    public SecondPlayer() {
        super();
        this.id = 1;
        this.bet = new int[extractions];
    }

    @Override
    public void createBet() {
        this.bet = Database.getInstance().getOldestN(extractions);
    }
}