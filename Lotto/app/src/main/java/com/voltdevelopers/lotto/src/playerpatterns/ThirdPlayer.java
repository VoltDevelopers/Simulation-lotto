package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.layout.Console;
import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.random.StdRandom;

import java.util.Arrays;

public class ThirdPlayer extends Player {

    /*
   Pattern III:
       Gioca un numero casuale
   */

    public ThirdPlayer() {
        this.id = 2;
        this.bet = new int[extractions];
    }

    @Override
    public void createBet() {
        try {
            this.bet = StdRandom.getRandomArray(extractions, 90);
            Console.getInstance().printStr("Pattern " + this.id + " bet: " + Arrays.toString(bet));
        } catch (InputException e) {
            Console.getInstance().printExp(e);
        }
    }
}