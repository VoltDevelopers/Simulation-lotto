package com.voltdevelopers.lotto.src.playerpatterns;

public class FifthPlayer extends Player {
    public FifthPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "L'ingenuo";
    }

    @Override
    public void createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getMostPulled()[i];
        }
        //Database.get().addPlayerBet(5, bet);
    }
}
