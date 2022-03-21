package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.layout.Console;
import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.random.StdRandom;

import java.util.Arrays;

public class FourthPlayer extends Player {

    /*
   Pattern VI:
       Gioca un numero fisso
   */

    public FourthPlayer() {
        this.id = 3;
        this.bet = new int[extractions];
        createFinalBet();
    }

    @Override
    public void createBet() {
        //already created a definitive array
    }

    private void createFinalBet(){
        try {
            this.bet = StdRandom.getRandomArray(extractions, 90);
            Console.getInstance().printStr("Pattern " + this.id + " bet: " + Arrays.toString(bet));
        } catch (InputException e) {
            Console.getInstance().printExp(e);
        }
    }
}
