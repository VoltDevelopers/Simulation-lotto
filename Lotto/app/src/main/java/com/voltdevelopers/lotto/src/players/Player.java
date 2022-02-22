package com.voltdevelopers.lotto.src.players;

public abstract class Player {
    protected String name;
    protected int numsPerTurn;

    public Player(int numsPerTurn){
        this.numsPerTurn = numsPerTurn;
    }

    public abstract int[] createBet();

    public String getName(){
        return this.name;
    }
}
