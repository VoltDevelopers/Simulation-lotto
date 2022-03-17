package com.voltdevelopers.lotto.data;

public class Settings {
    private static Settings instance = null;
    public static final int MAX_EXIT = 90;
    public static final int MAX_PLAYERS = 5;

    private boolean[] playersToPlay;
    private int extractions;
    private double moneyPerWin;

    private Settings() {
    }

    public static Settings getInstance() {
        if (instance != null)
            return instance;
        instance = new Settings();
        return instance;
    }

    public boolean[] getPlayersToPlay() {
        return playersToPlay;
    }

    public void setPlayersToPlay(boolean[] playersToPlay) {
        this.playersToPlay = playersToPlay;
    }

    public void setExtractions(int extractions) {
        this.extractions = extractions;
    }

    public int getextractions() {
        return extractions;
    }

    public double getMoneyPerWin() {
        return moneyPerWin;
    }

    public void setMoneyPerWin(double moneyPerWin) {
        this.moneyPerWin = moneyPerWin;
    }

}
