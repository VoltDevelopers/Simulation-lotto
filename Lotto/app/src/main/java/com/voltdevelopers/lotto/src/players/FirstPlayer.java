package com.voltdevelopers.lotto.src.players;

public class FirstPlayer extends Player {

    public FirstPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "Il Copione";
    }

    @Override
    public int[] createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getPullChronology()[i];
        }
        return bet;
    }
}