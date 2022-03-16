package com.voltdevelopers.lotto.src.playerpatterns;

public class FifthPlayer extends Player {

    public FifthPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.bet = new int[numsPerTurn];
    }

    @Override
    public void createBet() {
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getMostPulled()[i];
        }
    }
}
