package com.voltdevelopers.lotto.data;

import androidx.annotation.NonNull;

import com.voltdevelopers.lotto.layout.Console;

import java.util.ArrayList;
import java.util.Arrays;

public class Database {

    private final Settings settings;
    private static Database instance = null;
    private Profile[] players;
    private final Analysis analysis;

    public int allPulls;          // COUNTER: estrazioni primari + significative
    public int significantPulls;  // COUNTER: estrazioni significative
    public final double moneyToPay;

    private final ArrayList<int[]> allRounds;
    private final ArrayList<int[]> significantRounds;
    private final int[] pullsPerNumber; //n di estrazioni per valore (n di estrazioni di 1 si trova nella cella 0, di 2 nella 1, etc etc)
    private final ArrayList<Integer> pullChronology; //ordine estrazioni (ultimo estratto sta all'indice massimo)


    private OnGraphData onGraphData;

    private Database() {
        settings = Settings.getInstance();
        analysis = new Analysis();

        this.allPulls = 0;
        this.significantPulls = 0;
        this.moneyToPay = settings.getMoneyPerWin();

        allRounds = new ArrayList<>();
        significantRounds = new ArrayList<>();

        pullsPerNumber = new int[Settings.MAX_EXIT];
        pullChronology = new ArrayList<>();

        initPlayers();
    }

    public static Database getInstance() {
        if (instance != null)
            return instance;
        instance = new Database();
        return instance;
    }

    private void initPlayers() {
        players = new Profile[Settings.MAX_PLAYERS];
        for (int i = 0; i < settings.getPlayersToPlay().length; i++) {
            if (settings.getPlayersToPlay()[i]) {
                players[i] = new Profile(String.valueOf(i));
            }else {
                players[i] = null;
            }
        }
    }

    /*
    Add new pull
        input {45,32,56,..}
    */
    public void addPull(int[] input) {
        allRounds.add(input);
        for (int i = 0; i < input.length; i++) {
            pullsPerNumber[input[i] - 1]++;
            analysis.modChronology(input[i]);
        }
        allPulls++;
    }

    /*
    Add new significant pull
        input {45,32,56,..}
    */
    public void addSignificantPull(int[] input) {
        allRounds.add(input);
        significantRounds.add(input);
        for (int i = 0; i < input.length; i++) {
            pullsPerNumber[input[i] - 1]++;
            analysis.modChronology(input[i]);
        }
        allPulls++;
        significantPulls++;
    }

    /*
    Add player bet
        id {3},input {45,32,56,..}
    */
    public void addPlayerBet(int playerID, int[] input) {
        players[playerID].addBet(input);
    }


    public int[] getPlayerLastBet(int playerN) {
        return players[playerN].getLastBet();
    }

    public int getSizeAllPulls() {
        return allRounds.size();
    }

    public int getSizeSignificantPulls() {
        return allRounds.size();
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

    public int[] getPlayerBet(int playerN, int n) {
        return players[playerN].getBet(n);
    }



    //----------------------interface for adding data to the graph----------------------------------

    public void setOnGraphData(OnGraphData onGraphData) {
        this.onGraphData = onGraphData;
    }

    public interface OnGraphData {

        public void addData(int gameCounter, int[] results); // gameCounter asse x, winningsOfAllPlayers asse y

    }

    public void sendDataToGraph(int[] results) {
        onGraphData.addData(allRounds.size(), results);
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

    public void assignWins() {
        analysis.assignWins();
    }

    //-----------------------log managment----------------------------------------------------------

    @NonNull
    @Override
    public String toString() {
        return "Database{" +
                ",\n" + "-----" + "moneyPerWin=" + moneyToPay +
                ",\n" + "-----" + "betsPerRound=" + allPulls +
                ",\n" + "-----" + "gameCounter=" + allRounds.size() +
                ",\n" + "-----" + "pullChronology=" + pullChronologyToString("-----" + "     ") +//\t non va, idk
                ",\n" + "-----" + "pullsPerNumber=" + pullsPerNumberToString("-----" + "     ") +
                ",\n" + "-----" + "rounds=" + roundsToString("-----" + "     ") +
                ",\n" + "-----" + "players=\n" + allPlayersToString("-----" + "     ") +
                ",\n" + "-----" + '}';
    }

    private String roundsToString(String tabulation) {

        String output = "";

        for (int[] arr : allRounds) {
            output += tabulation + "[ ";
            for (int n : arr) {

                output += n + " ";

            }
            output += "]\n";
        }

        return "\n{\n" + output + '}';
    }

    private String pullsPerNumberToString(String tabulation) {

        String output = "";

        for (int i = 0; i < pullsPerNumber.length; i++) {
            output += tabulation + "[" + (i + 1) + " -> " + pullsPerNumber[i] + "]\n";
        }

        return "\n{\n" + output + '}';
    }

    private String pullChronologyToString(String tabulation) {

        String output = pullChronology.toString() + "\n";

        return "\n{\n" + output + '}';
    }

    private String allPlayersToString(String tabulation) {

        String out = "";
        for (int i = 0; i < players.length; i++)
            out += tabulation + playerToString(i, tabulation);

        return "{\n" + out + '}';
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

        public void assignWins() {

            //TODO the code
            console.printStr("Assigned wins and money earned to all players" + "\n"); //<-- versione temp di joel che non sa cosa deve fare, TODO farlo giusto secondo necessità

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
        betList.add(new int[input.length]);
        for (int i = 0; i < betList.get(betList.size() - 1).length; i++) {
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
                ",\n" + tabulation + "name='" + name +
                ",\n" + tabulation + "nWins=" + nWins +
                ",\n" + tabulation + "moneyWon=" + moneyWon +
                ",\n" + tabulation + "moneySpent=" + moneySpent +
                ",\n" + tabulation + "net=" + (moneyWon - moneySpent) +
                ",\n" + tabulation + "betList=" + betListToString(tabulation + "     ") +
                "}\n";
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

        return "{\n" + output + tabulation + "}\n";
    }
}