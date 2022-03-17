package com.voltdevelopers.lotto.data;

public class Settings {
    private static Settings instance = null;
    public static final int MAX_EXIT = 90;
    public static final int MAX_PLAYERS = 5;
    public static final int COST_OF_PLAY = 1;

    private boolean[] playersToPlay;
    private int extractions;
    private double moneyPerWin;
    private int presetGameCount;

    private Settings() {
        playersToPlay = new boolean[]{true, true, true, true, true};
    }

    public static Settings getInstance() {
        if (instance != null)
            return instance;
        instance = new Settings();
        return instance;
    }

    public int getPresetGameCount() {
        return presetGameCount;
    }

    public void setPresetGameCount(int presetGameCount) {
        this.presetGameCount = presetGameCount;
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
