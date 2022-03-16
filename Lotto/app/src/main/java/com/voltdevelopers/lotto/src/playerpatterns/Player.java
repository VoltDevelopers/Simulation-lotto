package com.voltdevelopers.lotto.src.playerpatterns;

public abstract class Player {
    protected int numsPerTurn;
    protected int playerN;
    protected int[] bet;

    public Player(int numsPerTurn) {
        this.numsPerTurn = 5;
    }

    public int[] getBet() {
        return bet;
    }

    public int getPlayerN(){
        return playerN;
    }

    public abstract void createBet();

}
