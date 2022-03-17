package com.voltdevelopers.lotto.src.playerpatterns;

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
        for(int i = 0; i < extractions; i++){
            // TODO: 16/03/22  
            bet[i] = 11;
        }
    }
}