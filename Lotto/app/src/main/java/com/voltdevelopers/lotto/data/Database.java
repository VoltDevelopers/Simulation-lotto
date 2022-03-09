package com.voltdevelopers.lotto.data;

import java.util.ArrayList;

public class Database {

    private static Database instance = null;
    private Profile[] players; //i 5 giocatori, ordinati come nel pptx con le istruzioni
    private int[] gameCounter; //conteggio partite, diviso in due celle per separare storici e non
    private ArrayList<Integer[]> rounds;
    private int[] pullsPerNumber; //n di estrazioni per valore (n di estrazioni di 1 si trova nella cella 0, di 2 nella 1, etc etc)
    private ArrayList<Integer> pullChronology; //ordine estrazioni (ultimo estratto sta all'indice massimo)
    private String log;             //log usato per la console

    private Database() {

        initPlayers();
        gameCounter = new int[2]; //il primo è per le partite "storiche", il secondo è per quelle in cui partecipano i giocatori
        gameCounter[0] = 0;
        gameCounter[1] = 0;

        rounds = new ArrayList<Integer[]>();

        pullsPerNumber = new int[Settings.N_NUMBERS];
        for (int i : pullsPerNumber) pullsPerNumber[i] = 0; //inizializzo array
        pullChronology = new ArrayList<Integer>();

        log = "";

        //TODO add log edit

    }

    public static Database get() {

        if (instance != null)
            return instance;
        instance = new Database();
        return instance;

    }

    private void initPlayers(){

        players = new Profile[Settings.N_PLAYERS];
        for(int i = 0; i < Settings.N_PLAYERS; i++){

            players[i] = new Profile(Settings.PLAYER_NAMES[i]);

        }
        //TODO add log edit

    }

    public int getPlayerMoneyWon(int playerN){

        return players[playerN].getMoneyWon();
        //TODO add log edit

    }

    public int getPlayerMoneySpent(int playerN){

        return players[playerN].getMoneySpent();
        //TODO add log edit

    }

    public int getPlayerNet(int playerN){

        return players[playerN].getNet();
        //TODO add log edit

    }

    public int getPlayerWins(int playerN){

        return players[playerN].getMoneyWon();
        //TODO add log edit

    }

    public void addPull(int[] input) {

        rounds.add(new Integer[Settings.NUMBERS_X_EXTRACTION]);
        for (int i : rounds.get(rounds.size() - 1)) { //copio i valori ricevuti e ne aumento il numero di estrazioni
            rounds.get(rounds.size() - 1)[i] = input[i];
            pullsPerNumber[input[i] - 1]++;
            modChronology(input[i]);
            //TODO add log edit
        }

    }

    private void modChronology(int n){

        if(pullChronology.contains(n))
            pullChronology.remove(pullChronology.lastIndexOf(n));

        pullChronology.add(n);

    }

    public void addPlayerBet(int playerN, int[] input){

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

    public int[] getNMostFrequent(int nRequested){

        int[] output = new int[nRequested];
        for (int i : output){

            int max = 0;
            for (int j : pullsPerNumber){
                if(pullsPerNumber[j] >= max && !intArrayContains(output, j+1)) {
                    max = pullsPerNumber[j];
                    output[i] = j+1;
                }
            }
        }

        //TODO add log edit

        return output;
    }

    private boolean intArrayContains(int[] arr, int n){

        for (int i : arr) {
            if (arr[i] == n)
                return true;
        }

        return false;
    }

    public int[] getLatestN(int nRequested){

        int[] output = new int[nRequested];
        int chronoSize = pullChronology.size();
        for (int i : output){

            output[i] = pullChronology.get(chronoSize);
            chronoSize--;

        }
        //TODO add log edit
        return output;

    }

    public int[] getOldestN(int nRequested){

        int[] output = new int[nRequested];
        for (int i : output){

            output[i] = pullChronology.get(i);

        }

        //TODO add log edit
        return output;

    }

    //-----------------------log managment----------------------------------------------------------

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
