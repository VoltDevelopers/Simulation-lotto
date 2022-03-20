package com.voltdevelopers.lotto.data;

public class Settings {
    private static Settings instance = null;
    public static final int MAX_EXIT = 90;
    public static final int MAX_PLAYERS = 5;
    public static final int COST_OF_PLAY = 1;
    public static final int EXTRACTIONS = 5;

    private int startMoney;
    private boolean[] playersToPlay;
    private double moneyPerWin;
    private int presetGameCount;
    private int extractionsPerRound;

    private Settings() {
        playersToPlay = new boolean[]{true, true, true, true, true};
    }

    public static Settings getInstance() {
        if (instance != null)
            return instance;
        instance = new Settings();
        return instance;
    }

    public int getExtractionsPerRound() {
        return extractionsPerRound;
    }

    public void setExtractionsPerRound(int extractionsPerRound) {
        this.extractionsPerRound = extractionsPerRound;
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

    public double getMoneyPerWin() {
        return moneyPerWin;
    }

    public void setMoneyPerWin(double moneyPerWin) {
        this.moneyPerWin = moneyPerWin;
    }

    public void setStartMoney(int startMoney) {
        this.startMoney = startMoney;
    }

    public int getStartMoney() {
        return startMoney;
    }
}
