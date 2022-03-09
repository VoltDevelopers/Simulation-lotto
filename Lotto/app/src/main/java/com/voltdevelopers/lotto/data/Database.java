package com.voltdevelopers.lotto.data;

import java.util.ArrayList;

public class Database {

    private static Database instance = null;
    private Profile[] players; //i 5 giocatori, ordinati come nel pptx con le istruzioni
    private int[] gameCounter;
    private ArrayList<Integer[]> rounds;
    private int[] pullsPerNumber; //n di estrazioni per valore (n di estrazioni di 1 si trova nella cella 0, di 2 nella 1, etc etc)
    private int[] pullChronology; //ordine estrazioni (ultimo estratto sta in arr[89])
    private String log;             //log usato per la console

    private Database() {

//        initPlayers();
        gameCounter = new int[2]; //il primo è per le partite "storiche", il secondo è per quelle in cui partecipano i giocatori
        gameCounter[0] = 0;
        gameCounter[1] = 0;

        rounds = new ArrayList<>();

        pullsPerNumber = new int[90];
        for (int i : pullsPerNumber) pullsPerNumber[i] = 0; //inizializzo array
        pullChronology = new int[90];

        log = "";
    }

    public static Database get() {

        if (instance != null)
            return instance;
        instance = new Database();
        return instance;

        //TODO add log edit
    }

//    private void initPlayers(){
//
//        //TODO initialize players and set player names via constructor if needed
//        //TODO add log edit
//
//    }

    public int getPlayerWinnings(int playerN) {

        return players[playerN].getWinnings();
        //TODO add log edit

    }

    public int getPlayerSpendings(int playerN) {

        return players[playerN].getSpendings();
        //TODO add log edit

    }

    public int getPlayerNet(int playerN) {

        return players[playerN].getNet();
        //TODO add log edit

    }

    public int getPlayerWins(int playerN) {

        return players[playerN].getWins();
        //TODO add log edit

    }

    public void addPull(int[] input) {

        rounds.add(new Integer[5]);
        for (int i : rounds.get(rounds.size() - 1)) { //copio i valori ricevuti e ne aumento il numero di estrazioni
            rounds.get(rounds.size() - 1)[i] = input[i];
            pullsPerNumber[input[i] - 1]++;
            //TODO modifico la cronologia dei valori estratti
            //TODO add log edit
        }

    }

    public void addPlayerBet(int playerN, int[] input) {

        players[playerN].addPull(input);

    }

    public int getTotalPulls() {
        return gameCounter[0] + gameCounter[1];
        //TODO add log edit
    }

    public int getHistoricPulls() {
        return gameCounter[0];
        //TODO add log edit
    }

    public int getPlayedPulls() {
        return gameCounter[1];
        //TODO add log edit
    }

    /*public int getMostFrequent(int nRequested){

        //TODO return most pulled
        //TODO add log edit

    }*/

    /*public int[] getLatestN(int nRequested){

        //TODO return latest n pulled form pullChronology
        //TODO add log edit

    }*/

    /*public int[] getOldestN(int nRequested){

        //TODO return oldest n pulled form pullChronology
        //TODO add log edit

    }*/

    private void addToString(String in) { //aggiunge un azione al log

        log += in + "\n";
        //TODO add log edit

    }

    public String pushString() { //ritorna il log senza pulirlo

        return log;
        //TODO add log edit

    }

    public String pushString(boolean clear) { //ritorna il log, pulisce se input vero

        if (clear) {

        }
        String out = log;
        log = "";
        return out;
        //TODO add log edit

    }

}