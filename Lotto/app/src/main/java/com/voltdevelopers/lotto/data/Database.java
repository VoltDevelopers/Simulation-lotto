package com.voltdevelopers.lotto.data;

import com.voltdevelopers.lotto.layout.Console;

import java.util.ArrayList;

public class Database {

    public final int numOfPulls; //Il numero di estrazioni
    public final double moneyToPay;
    private static Database instance = null;
    private Profile[] players; //i 5 giocatori, ordinati come nel pptx con le istruzioni
    private int gameCounter;
    private final ArrayList<int[]> rounds;
    private final int[] pullsPerNumber; //n di estrazioni per valore (n di estrazioni di 1 si trova nella cella 0, di 2 nella 1, etc etc)
    private final ArrayList<Integer> pullChronology; //ordine estrazioni (ultimo estratto sta all'indice massimo)

    private Analysis analysis;

    private OnGraphData onGraphData;

    private Database(int numOfPulls, double moneyToPay) {
        this.numOfPulls = numOfPulls;
        this.moneyToPay = moneyToPay;
        analysis = new Analysis();

        initPlayers();
        gameCounter = 0;

        rounds = new ArrayList<>();

        pullsPerNumber = new int[Settings.N_NUMBERS];
//        for (int i : pullsPerNumber) pullsPerNumber[i] = 0; //inizializzo array
        pullChronology = new ArrayList<>();
    }

    public static Database getInstance() {

        return getInstance(Settings.get().getnOfPulls(), Settings.get().getMoneyPerWin());

    }

    public static Database getInstance(int numOfPulls, double moneyToPay) {//rimane per non causare problemi

        if (instance != null)
            return instance;
        instance = new Database(numOfPulls, moneyToPay);
        return instance;

    }

    private void initPlayers() {
        players = new Profile[Settings.N_PLAYERS];
        for (int i = 0; i < Settings.N_PLAYERS; i++) {
            players[i] = new Profile(Settings.PLAYER_NAMES[i]);
        }
    }

    public double getPlayerMoneyWon(int playerN) {
        return players[playerN].getMoneyWon();
    }

    public double getPlayerMoneySpent(int playerN) {
        return players[playerN].getMoneySpent();
    }

    public double getPlayerNet(int playerN) {
        return players[playerN].getNet();
    }

    public int getPlayerWins(int playerN) {
        return players[playerN].getNWins();
    }

    public void addPull(int[] input) {
        rounds.add(input);
        for (int i = 0; i < input.length; i++) {
            pullsPerNumber[input[i] - 1]++;
            analysis.modChronology(input[i]);
        }
        gameCounter++;

    }

    public void addPlayerBet(int playerN, int[] input) {
        players[playerN].addBet(input);
    }

    public int[] getPlayerBet(int playerN, int n) {
        return players[playerN].getBet(n);
    }

    public int[] getPlayerLastBet(int playerN) {
        return players[playerN].getLastBet();
    }

    public int getPullCount() {
        return gameCounter;
    }

    //----------------------interface for adding data to the graph----------------------------------

    public void setOnGraphData(OnGraphData onGraphData) {
        this.onGraphData = onGraphData;
    }

    public interface OnGraphData {

        public void addData(int gameCounter, int[] results); // gameCounter asse x, winningsOfAllPlayers asse y

    }

    public void sendDataToGraph(int[] results) {

        onGraphData.addData(gameCounter, results);

    }

    //----------------------analysis methods--------------------------------------------------------

    public int[] getNMostFrequent(int nRequested) {
        return analysis.getNMostFrequent(nRequested);
    }

    public int[] getLatestN(int nRequested) {
        return analysis.getLatestN(nRequested);
    }

    public int[] getOldestN(int nRequested) {
        return analysis.getOldestN(nRequested);
    }

    //-----------------------log managment----------------------------------------------------------

    @Override
    public String toString() {//defaut per chi non conosce

        return toString("");

    }

    public String toString(String tabulation) {//metodo proprio, tab serve per i rientri
        return "Database{" +
                ",\n" + tabulation + "moneyPerWin=" + moneyToPay +
                ",\n" + tabulation + "betsPerRound=" + numOfPulls +
                ",\n" + tabulation + "gameCounter=" + gameCounter +
                ",\n" + tabulation + "pullChronology=" + pullChronologyToString(tabulation + "     ") +//\t non va, idk
                ",\n" + tabulation + "pullsPerNumber=" + pullsPerNumberToString(tabulation + "     ") +
                ",\n" + tabulation + "rounds=" + roundsToString(tabulation + "     ") +
                ",\n" + tabulation + "players=" + allPlayersToString(tabulation + "     ") +
                ",\n" + tabulation + '}';
    }

    private String roundsToString(String tabulation) {

        String output = "";

        for (int[] arr : rounds) {
            output += tabulation + "[ ";
            for (int n : arr) {

                output += n + " ";

            }
            output += "]\n";
        }

        return
                "\n{\n"
                        + output +
                        '}';

    }

    private String pullsPerNumberToString(String tabulation) {

        String output = "";

        for (int i = 0; i < pullsPerNumber.length; i++) {

            output += tabulation + "[" + (i + 1) + " -> " + pullsPerNumber[i] + "]\n";

        }

        return
                "\n{\n"
                        + output +
                        '}';

    }

    private String pullChronologyToString(String tabulation) {

        String output = pullChronology.toString() + "\n";

        return
                "\n{\n"
                        + output +
                        '}';


    }

    private String allPlayersToString(String tabulation) {

        String out = "";
        for (int i = 0; i < players.length; i++)
            out += playerToString(i, tabulation);

        return out;

    }

    public String playerToString(int n, String tabulation) {

        return players[n].toString(tabulation);

    }

    private class Analysis {

        private Console console = Console.getInstance();

        public int[] getLatestN(int nRequested) {
            int[] output = new int[nRequested];
            int chronoSize = pullChronology.size() - 1;
            for (int i : output) {
                output[i] = pullChronology.get(chronoSize);
                chronoSize--;
            }

            console.printStr("Took an array of length " + nRequested + " containing the last numbers -> " + output.toString() + "\n");
            return output;
        }

        private void modChronology(int n) {
            if (pullChronology.contains(n))
                pullChronology.remove(pullChronology.lastIndexOf(n));

            pullChronology.add(n);
            console.printStr("Modified pullChronology " + pullChronology.toString() + "\n");
        }

        public int[] getOldestN(int nRequested) {
            int[] output = new int[nRequested];
            for (int i : output) {
                output[i] = pullChronology.get(i);
            }

            console.printStr("Took an array of length " + nRequested + " containing the oldest numbers -> " + output.toString() + "\n");
            return output;
        }

        public int[] getNMostFrequent(int nRequested) {
            int[] output = new int[nRequested];
            for (int i : output) {
                int max = 0;
                for (int j : pullsPerNumber) {
                    if (pullsPerNumber[j] >= max && !intArrayContains(output, j + 1)) {
                        max = pullsPerNumber[j];
                        output[i] = j + 1;
                    }
                }
            }
            console.printStr("Took an array of length " + nRequested + " containing the most frequent numbers -> " + output.toString() + "\n");
            return output;
        }

        private boolean intArrayContains(int[] arr, int n) {
            for (int i : arr) {
                if (arr[i] == n)
                    console.printStr("The array " + arr.toString() + " contains " + n + "\n");
                return true;
            }
            console.printStr("The array " + arr.toString() + " does not contains " + n + "\n");
            return false;
        }
    }
}

class Profile {
    private String name;
    private double moneyWon, moneySpent;
    private final int nWins;
    private final ArrayList<int[]> betList;

    public Profile() {
        moneyWon = 0;
        moneySpent = 0;
        nWins = 0;
        betList = new ArrayList<>();
    }

    public Profile(String name) {
        this();
        this.name = name;
    }

    public void addBet(int[] input) {
        betList.add(new int[Settings.get().getnOfPulls()]);
        for (int i : betList.get(betList.size() - 1)) {
            betList.get(betList.size() - 1)[i] = input[i];
        }
    }

    public int[] getBet(int n) {
        return betList.get(n);
    }

    public int[] getLastBet() {
        return betList.get(betList.size() - 1);
    }

    public int[] getSelectedBet(int n) {
        return betList.get(n);
    }

    public double getMoneyWon() {
        return moneyWon;
    }

    public void addToMoneyWon(double moneyWon) {
        this.moneyWon += moneyWon;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void addToMoneySpent(double moneySpent) {
        this.moneySpent += moneySpent;
    }

    public double getNet() {
        return moneyWon - moneySpent;
    }

    public int getNWins() {
        return nWins;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        if (name != null)
            return name;
        return "name not set";
    }

    public String toString(String tabulation) {
        return "Profile{" +
                ",\n" + tabulation + "name='" + name + '\n' +
                ",\n" + tabulation + "nWins=" + nWins +
                ",\n" + tabulation + "moneyWon=" + moneyWon +
                ",\n" + tabulation + "moneySpent=" + moneySpent +
                ",\n" + tabulation + "net=" + (moneyWon - moneySpent) +
                ",\n" + tabulation + "betList=" + betListToString(tabulation + "     ") +
                '}';
    }

    private String betListToString(String tabulation) {


        String output = "";

        for (int[] arr : betList) {
            output += tabulation + "[ ";
            for (int n : arr) {

                output += n + " ";

            }
            output += "]\n";
        }

        return
                "\n{\n"
                        + output +
                        '}';


    }
}