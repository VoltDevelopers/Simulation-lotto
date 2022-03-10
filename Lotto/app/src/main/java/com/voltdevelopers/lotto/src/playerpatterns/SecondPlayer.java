package com.voltdevelopers.lotto.src.playerpatterns;

public class SecondPlayer extends Player {
    public SecondPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "Il Ritardatario";
    }

    @Override
    public void createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getPullChronology()[pullChronology.length - 1 - i];
        }
        //Database.get().addPlayerBet(5, bet);
    }
}
