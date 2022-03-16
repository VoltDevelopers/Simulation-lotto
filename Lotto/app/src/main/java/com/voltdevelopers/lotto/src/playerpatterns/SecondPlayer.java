package com.voltdevelopers.lotto.src.playerpatterns;

public class SecondPlayer extends Player {
    public SecondPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.playerN = 2;
        this.bet = new int[numsPerTurn];
    }

    @Override
    public void createBet() {
        for(int i = 0; i < numsPerTurn; i++){
            // TODO: 16/03/22  
            bet[i] = 0;
        }
    }
}
