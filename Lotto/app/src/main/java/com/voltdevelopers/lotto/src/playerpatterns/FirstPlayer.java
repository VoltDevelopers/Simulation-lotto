package com.voltdevelopers.lotto.src.playerpatterns;

import android.provider.ContactsContract;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class FirstPlayer extends Player {

    /*
    Pattern I:
        Gioca un numero della cinquina precedente
    */

    public FirstPlayer() {
        super();
        this.id = 0;
        this.bet = new int[extractions];
    }

    @Override
    public void createBet() {
        this.bet = Database.getInstance().getLatestN(extractions);
    }
}