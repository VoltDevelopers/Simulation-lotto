package com.voltdevelopers.lotto.data;

import java.util.ArrayList;

public class Profile {

    private String name = null;
    private int winnings, spendings, wins;
    private ArrayList<Integer[]> bets;

    public Profile() {

        winnings = 0;
        spendings = 0;
        wins = 0;
        bets = new ArrayList<Integer[]>();

    }

    public Profile(String name) {

        winnings = 0;
        spendings = 0;
        this.name = name;

    }

    public void addPull(int[] input) {

        bets.add(new Integer[Settings.get().getnOfPulls()]);
        for (int i : bets.get(bets.size() - 1)) { //copio i valori ricevuti e ne aumento il numero di estrazioni
            bets.get(bets.size() - 1)[i] = input[i];
        }

        //TODO determine if won, if so add win

    }

    public int getWinnings() {
        return winnings;
    }

    public void addToWinnings(int winnings) {
        this.winnings += winnings;
    }

    public int getSpendings() {
        return spendings;
    }

    public void addToSpendings(int spendings) {
        this.spendings += spendings;
    }

    public int getNet() {
        return winnings - spendings;
    }

    public int getWins() {
        return wins;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        if(name != null)
            return name;
        return "name not set";
    }

}
