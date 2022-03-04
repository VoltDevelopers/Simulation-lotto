package com.voltdevelopers.lotto.data;

import java.util.ArrayList;

public class Profile {

    private String name;
    private int moneyWon, moneySpent;
    private int nWins;
    private ArrayList <ArrayList<Integer>> playerBetList;
    private ArrayList <Integer> playerBet;

    public Profile(){

        moneyWon = 0;
        moneySpent = 0;
        nWins = 0;
        playerBetList = new ArrayList<ArrayList<Integer>>();

    }

    public Profile(String name){

        this();
        this.name = name;

    }

    public void addPlayerBet(ArrayList <Integer> input){

        playerBet = new ArrayList<>(Settings.getIstance().getNumbersXBet());

        for(int i = 0; i < playerBet.size(); i++){

            this.playerBet.add(input.get(i));

        }

        verifyWin();

        playerBetList.add(playerBet);

    }

    private void verifyWin(){

        //TODO determine if won, if so add win

    }

    public String getName() {

        if(name != null)
            return name;
        return "name not set";

    }

    public void setName(String name) {

        this.name = name;

    }

    public int getMoneyWon() {

        return moneyWon;

    }

    public void addMoneyWon(int moneyWon) {

        this.moneyWon += moneyWon;

    }

    public int getMoneySpent() {

        return moneySpent;

    }

    public void addMoneySpent(int moneySpent) {

        this.moneySpent += moneySpent;

    }

    public int getnWins() {

        return nWins;

    }

    public void setnWins(int nWins) {

        this.nWins = nWins;

    }

    public ArrayList<ArrayList<Integer>> getPlayerBetList() {

        return playerBetList;

    }

    public void setPlayerBetList(ArrayList<ArrayList<Integer>> playerBetList) {

        this.playerBetList = playerBetList;

    }

    public ArrayList<Integer> getPlayerBet() {

        return playerBet;

    }

    public void setPlayerBet(ArrayList<Integer> playerBet) {

        this.playerBet = playerBet;

    }

    public int getNet() {

        return moneyWon - moneySpent;

    }
}
