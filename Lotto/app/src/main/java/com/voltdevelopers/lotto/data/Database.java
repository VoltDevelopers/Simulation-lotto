package com.voltdevelopers.lotto.data;

import androidx.annotation.NonNull;

import com.voltdevelopers.lotto.layout.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    private static Database instance = null;
    private final String tabulation;
    private final Settings settings;
    private final Analysis analysis;
    private Profile[] players;

    private final List<int[]> allRounds;
    private final List<int[]> significantRounds;
    private final List<Integer> pullChronology;         // Ordine estrazioni (ultimo estratto sta all'indice massimo)
    private final int[] pullsPerNumber;                 // Statistica estrazione dei numeri

    private Database() {
        tabulation = "-----     ";
        settings = Settings.getInstance();
        analysis = new Analysis();

        allRounds = new ArrayList<>();
        significantRounds = new ArrayList<>();
        pullChronology = new ArrayList<>();
        pullsPerNumber = new int[Settings.MAX_EXIT];

        initPlayers();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    private void initPlayers() {
        players = new Profile[Settings.MAX_PLAYERS];

        for (int i = 0; i < settings.getPlayersToPlay().length; i++) {
            if (settings.getPlayersToPlay()[i]) {
                players[i] = new Profile(String.valueOf(i));
            } else {
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

        for (int i : input) {
            pullsPerNumber[i - 1]++;
            analysis.modChronology(i);
        }
    }

    /*
    Add new significant pull
        input {45,32,56,..}
    */
    public void addSignificantPull(int[] input) {
        allRounds.add(input);
        significantRounds.add(input);

        for (int i : input) {
            pullsPerNumber[i - 1]++;
            analysis.modChronology(i);
        }
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
        return "Database {" +
                ",\n" + tabulation + "moneyPerWin=" + settings.getMoneyPerWin() +
                ",\n" + tabulation + "gameCounter=" + allRounds.size() +
                ",\n" + tabulation + "pullChronology=" + pullChronologyToString() +
                ",\n" + tabulation + "pullsPerNumber=" + pullsPerNumberToString() +
                ",\n" + tabulation + "rounds=" + roundsToString() +
                ",\n" + tabulation + "players=\n" + allPlayersToString() +
                ",\n" + tabulation + '}';
    }

    private String roundsToString() {
        StringBuilder output = new StringBuilder();

        for (int[] arr : allRounds) {
            output.append(tabulation)
                    .append("[ ");
            for (int n : arr) {
                output.append(n)
                        .append(" ");
            }
            output.append("]\n");
        }

        return "\n{\n" + output + '}';
    }

    private String pullsPerNumberToString() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < pullsPerNumber.length; i++) {
            output.append("-----     ")
                    .append("[")
                    .append(i + 1)
                    .append(" -> ")
                    .append(pullsPerNumber[i])
                    .append("]\n");
        }

        return "\n{\n" + output + '}';
    }

    private String pullChronologyToString() {
        return "\n{\n" + pullChronology.toString() + "\n}";
    }

    private String allPlayersToString() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < players.length; i++)
            output.append(tabulation)
                    .append(playerToString(i));

        return "{\n" + output + '}';
    }

    public String playerToString(int n) {
        return players[n].toString();
    }

    private class Analysis {
        private final Console console;

        public Analysis() {
            console = Console.getInstance();
        }

        public int[] getLatestN(int nRequested) {
            int[] output = new int[nRequested];
            int chronologySize = pullChronology.size() - 1;

            for (int i : output) {
                output[i] = pullChronology.get(chronologySize);
                chronologySize--;
            }

            console.printStr("Took an array of length "
                    + nRequested
                    + " containing the last numbers -> "
                    + Arrays.toString(output) + "\n");
            return output;
        }

        private void modChronology(int n) {
            if (pullChronology.contains(n)) {
                pullChronology.remove(pullChronology.lastIndexOf(n));
            }

            pullChronology.add(n);
            console.printStr("Modified pullChronology " + pullChronology.toString() + "\n");
        }

        public int[] getOldestN(int nRequested) {
            int[] output = new int[nRequested];

            for (int i : output) {
                output[i] = pullChronology.get(i);
            }

            console.printStr("Took an array of length "
                    + nRequested
                    + " containing the oldest numbers -> "
                    + Arrays.toString(output) + "\n");
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

            console.printStr("Took an array of length "
                    + nRequested
                    + " containing the most frequent numbers -> "
                    + Arrays.toString(output) + "\n");
            return output;
        }

        private boolean intArrayContains(int[] arr, int n) {
            for (int i : arr) {
                if (arr[i] == n) {
                    console.printStr("The array "
                            + Arrays.toString(arr)
                            + " contains "
                            + n + "\n");
                }
                return true;
            }

            console.printStr("The array "
                    + Arrays.toString(arr)
                    + " does not contain "
                    + n + "\n");
            return false;
        }

        public void assignWins() {
            //TODO the code
            console.printStr("Assigned wins and money earned to all players" + "\n"); //<-- versione temp di joel che non sa cosa deve fare, TODO farlo giusto secondo necessità
        }
    }
}

class Profile {
    private final List<int[]> betList;
    private final String tabulation;
    private String name;

    private double moneyWon, moneySpent;
    private final int nWins;

    public Profile() {
        betList = new ArrayList<>();
        tabulation = "-----     ";
        moneyWon = 0;
        moneySpent = 0;
        nWins = 0;
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
        if (name != null) {
            return name;
        }

        return "name is not set";
    }

    @NonNull
    public String toString() {
        return "Profile {" +
                ",\n" + tabulation + "name='" + name +
                ",\n" + tabulation + "nWins=" + nWins +
                ",\n" + tabulation + "moneyWon=" + moneyWon +
                ",\n" + tabulation + "moneySpent=" + moneySpent +
                ",\n" + tabulation + "net=" + (moneyWon - moneySpent) +
                ",\n" + tabulation + "betList=" + betListToString(tabulation + "     ") +
                "}\n";
    }

    private String betListToString(String tabulation) {
        StringBuilder output = new StringBuilder();

        for (int[] arr : betList) {
            output.append(tabulation)
                    .append("[ ");
            for (int n : arr) {
                output.append(n)
                        .append(" ");
            }
            output.append("]\n");
        }

        return "{\n" + output + tabulation + "}\n";
    }
}