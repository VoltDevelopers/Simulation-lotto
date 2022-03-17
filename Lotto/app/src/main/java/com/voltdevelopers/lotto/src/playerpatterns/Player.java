package com.voltdevelopers.lotto.src.playerpatterns;

import com.voltdevelopers.lotto.data.Settings;

public abstract class Player {
    protected int id;
    protected int extractions;
    protected int[] bet;

    public Player() {
        this.extractions = Settings.getInstance().getExtractions();
    }

    public abstract void createBet();

    public int[] getBet() {
        return bet;
    }

    public int getId() {
        return id;
    }
}