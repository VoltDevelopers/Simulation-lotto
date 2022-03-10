package com.voltdevelopers.lotto.data;

public class Settings {

    private static Settings instance = null;
    public static final int N_NUMBERS = 90;
    public static final int N_PLAYERS = 5;
    public static final int NUMBERS_X_EXTRACTION = 5;
    public static final String[] PLAYER_NAMES = {"Il Copione", "Il Ritardatario", "L' Azzardoso", "Il Testardo", "L' Ingenuo"};
    private static int nOfPulls;
    private static double moneyPerWin;


    private Settings() {
    }

    public static Settings get() {

        if (instance != null)
            return instance;
        instance = new Settings();
        return instance;
    }

    public int getnOfPulls() {
        return nOfPulls;
    }

    public void setNOfPulls(int nOfPulls) {
        this.nOfPulls = nOfPulls;
    }

    public static double getMoneyPerWin() { return moneyPerWin; }

    public static void setMoneyPerWin(double moneyPerWin) { this.moneyPerWin = moneyPerWin; }
}