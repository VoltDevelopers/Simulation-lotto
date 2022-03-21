package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.layout.Console;

import java.util.Arrays;

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
        Console.getInstance().printStr("Pattern " + this.id + " bet: " + Arrays.toString(bet));
    }
}