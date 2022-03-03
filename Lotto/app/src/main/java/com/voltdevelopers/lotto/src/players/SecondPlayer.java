package com.voltdevelopers.lotto.src.players;

public class SecondPlayer extends Player {
    public SecondPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "Il Ritardatario";
    }

    @Override
    public int[] createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getPullChronology()[pullChronology.length - 1 - i];
        }
        return bet;
    }
}
