package com.voltdevelopers.lotto.data;

import java.util.ArrayList;

public class Profile {

    private String name;
    private int moneyWon, moneySpent;
    private int nWins;
    private ArrayList <int[]> betList;

    public Profile() {

        moneyWon = 0;
        moneySpent = 0;
        nWins = 0;
        betList = new ArrayList<int[]>();

    }

    public Profile(String name) {

        this();
        this.name = name;

    }

    public void addPull(int[] input) {

        betList.add(new int[Settings.get().getnOfPulls()]);
        for (int i : betList.get(betList.size() - 1)) { //copio i valori ricevuti e ne aumento il numero di estrazioni
            betList.get(betList.size() - 1)[i] = input[i];
        }

    }

    public int[] getLastBet(){

        return betList.get(betList.size()-1);

    }

    public int[] getSelectedBet(int n){

        return betList.get(n);

    }

    public int getMoneyWon() {
        return moneyWon;
    }

    public void addToMoneyWon(int moneyWon) {
        this.moneyWon += moneyWon;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public void addToMoneySpent(int moneySpent) {
        this.moneySpent += moneySpent;
    }

    public int getNet() {
        return moneyWon - moneySpent;
    }

    public int getNWins() {
        return nWins;
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
