package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class FourthPlayer extends Player {

    /*
   Pattern VI:
       Gioca un numero fisso
   */

    public FourthPlayer() {
        super();
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
        } catch (InputException e) {
            e.printStackTrace();
        }
    }
}
