package com.voltdevelopers.lotto.src.playerpatterns;

public class FifthPlayer extends Player {

    /*
   Pattern V:
       Gioca il numero uscito più frequentemente
   */

    public FifthPlayer() {
        this.id = 4;
        this.bet = new int[extractions];

        for (int i = 0; i < extractions; i++) {
            bet[i] = 11;
        }
    }

    @Override
    public void createBet() {
    }
}