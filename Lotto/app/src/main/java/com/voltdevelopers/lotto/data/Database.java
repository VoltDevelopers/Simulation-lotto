package com.voltdevelopers.lotto.data;

import java.util.ArrayList;

public class Database {

    private static Database instance = null;

    private static final int N_NUMBERS = 90;
    private static final int N_PLAYERS = 5;
    private static final int N_NUMBERS_X_QUINTET = 5;

    //private ArrayList <Integer> gameCounter;
    private ArrayList <Profile> players;
    private ArrayList <ArrayList <Integer>> allQuintets;
    private ArrayList <Integer> pullsPerNumber;

    private ArrayList <Integer> pullsChronology;

    //private String log;

    private Database(){

        initPlayers();

        /* gameCounter = new ArrayList<>(2);
           gameCounter.add(0);
           gameCounter.add(0); */

        allQuintets = new ArrayList<ArrayList<Integer>>();

        initPullsPerNumber();
        pullsChronology = new ArrayList<>();

        //log = "";

        //TODO add log edit

    }

    private void initPlayers(){

        players = new ArrayList<Profile>(N_PLAYERS);

        for(int i = 0; i < players.size(); i++)  players.add(new Profile("Giocatore" + i));

        //TODO add log edit
    }

    private void initPullsPerNumber(){

        pullsPerNumber = new ArrayList<>(N_NUMBERS);

        for(int i = 0;i < pullsPerNumber.size(); i++) pullsPerNumber.add(0);

        //TODO add log edit

    }

    public void addQuintet(ArrayList<Integer> input){

        ArrayList <Integer> quintet = new ArrayList<>(N_NUMBERS_X_QUINTET);

        for(int i = 0; i < quintet.size(); i++){

            quintet.add(input.get(i));
            pullsPerNumber.set(input.get(i) - 1, pullsPerNumber.get(input.get(i) - 1) + 1);

        }

        allQuintets.add(quintet);
        modifyChronology();

        //TODO add log edit

    }

    private void modifyChronology(){

        int counter = 0;

        for(int i = 0; i < allQuintets.size(); i++){

            for( int j = 0; j < N_NUMBERS_X_QUINTET; j++){

                pullsChronology.add(counter, allQuintets.get(i).get(j));

                for(int k = 0; k < N_NUMBERS; k++){

                    if(pullsChronology.get(counter) == pullsChronology.get(k)) {

                        pullsChronology.remove(k);
                        counter--;

                    }

                }

                counter++;

            }
        }

        //TODO add log edit

    }

    public void addPlayerBet(int playerN, ArrayList<Integer> input){

        players.get(playerN).addPlayerBet(input);

        //TODO add log edit

    }

    public ArrayList<Integer> getMostFrequents(){

        int i = allQuintets.size() * 5;
        ArrayList <Integer> mostFrequentsNumbers = new ArrayList<>();

        do {

            for(int j = 0; j < N_NUMBERS;  j++){

                if(pullsPerNumber.get(j) == i) {

                    if (mostFrequentsNumbers.size() != Settings.getInstance().getNumbersXBet()) {

                        mostFrequentsNumbers.add(pullsPerNumber.get(j));

                    }
                }
            }

        }while(mostFrequentsNumbers.size() != Settings.getInstance().getNumbersXBet());

        return mostFrequentsNumbers;

        //TODO add log edit

    }

    public ArrayList<Integer> getLatestN(){

        ArrayList<Integer> latestNumbers = new ArrayList<>();
        int counter = 89;

        for (int i = 0; i < Settings.getInstance().getNumbersXBet(); i++){

            latestNumbers.add(pullsChronology.get(counter));
            counter--;

        }

        return latestNumbers;

        //TODO add log edit
    }

    public ArrayList<Integer> getOldestN(){

        ArrayList<Integer> oldestNumbers = new ArrayList<>();

        for (int i = 0; i < Settings.getInstance().getNumbersXBet(); i++){

            oldestNumbers.add(pullsChronology.get(i));

        }

        return oldestNumbers;

        //TODO add log edit

    }

    public int getPlayerWinnings(int playerN){

        return players.get(playerN).getMoneyWon();

        //TODO add log edit

    }

    public int getPlayerSpendings(int playerN){

        return players.get(playerN).getMoneySpent();

        //TODO add log edit



    }

    public int getPlayerNet(int playerN){

        return players.get(playerN).getNet();

        //TODO add log edit


    }

    public int getPlayerWins(int playerN){

        return players.get(playerN).getnWins();
        //TODO add log edit

    }

    /*public int getTotalPulls() {
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

    }*/

    public ArrayList<Profile> getPlayers() {

        return players;

    }

    public ArrayList<ArrayList<Integer>> getAllQuintets() {

        return allQuintets;

    }

    public ArrayList<Integer> getPullsPerNumber() {

        return pullsPerNumber;

    }

    public ArrayList<Integer> getPullsChronology() {

        return pullsChronology;

    }

    public static Database getInstance() {

        return instance == null ? instance = new Database() : instance;

        //TODO add log edit

    }

}
