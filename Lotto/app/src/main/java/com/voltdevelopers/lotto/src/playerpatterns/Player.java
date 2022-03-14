package com.voltdevelopers.lotto.src.playerpatterns;

public abstract class Player {
    protected String name;
    protected int numsPerTurn;
    protected int playerN;

    public Player(int numsPerTurn){
        this.numsPerTurn = 5;
    }

    public abstract void createBet();

    public String getName() {
        return this.name; //ritorna il finto nome del giocatore, utile per abbellire la UI
    }
}
