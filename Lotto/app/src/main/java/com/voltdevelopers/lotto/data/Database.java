package com.voltdevelopers.lotto.data;

import androidx.annotation.NonNull;

import com.voltdevelopers.lotto.layout.Console;

import java.util.ArrayList;

public class Database {

    private final Settings settings;
    private static Database instance = null;

    private Profile[] players;
    private final Analysis analysis;

    private final ArrayList<int[]> allRounds;
    private final ArrayList<int[]> significantRounds;
    private final int[] pullsPerNumber;                 // Statistica estrazione dei numeri
    private final ArrayList<Integer> pullChronology;    // Ordine estrazioni (ultimo estratto sta all'indice massimo)

    private Database() {
        settings = Settings.getInstance();
        analysis = new Analysis();

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
        for (int i = 0; i < input.length; i++) {
            pullsPerNumber[input[i] - 1]++;
            analysis.modChronology(input[i]);
        }
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
        return significantRounds.size();
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

    public double getPlayerWinPercentage(int playerN) {
        return (double)players[playerN].getNWins()/(double)players[playerN].getNOfBets()*100d;
    }

    public ArrayList<Integer> getPlayerWinList(int playerN) {
        return players[playerN].getWinList();
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
    public String toString() { //per poter essere chiamato da chi non sa
        return toString("");
    }

    @NonNull
    public String toString(String tabulation) {
        return "Database{" +
                ",\n" + tabulation + "moneyPerWin=" + settings.getMoneyPerWin() +
                ",\n" + tabulation + "gameCounter=" + allRounds.size() +
                ",\n" + tabulation + "pullChronology=" + pullChronologyToString(tabulation + "     ") +//\t non va, idk
                ",\n" + tabulation + "pullsPerNumber=" + pullsPerNumberToString(tabulation + "     ") +
                ",\n" + tabulation + "rounds=" + allRoundsToString(tabulation + "     ") +
                ",\n" + tabulation + "players=\n" + allPlayersToString(tabulation + "     ") +
                ",\n" + tabulation + '}';
    }

    private String allRoundsToString(String tabulation) {

        return "\n{" +
                "\n" + tabulation + "preGameRounds=" + preGameRoundsToString(tabulation + "     ") +
                ",\n" + tabulation + "significantRouds=" + significantRoundsToString(tabulation + "     ") +
                '}';

    }

    private String preGameRoundsToString(String tabulation) {

        String output = "";

        for (int i = 0; i < settings.getPresetGameCount(); i++) {
            output += tabulation + "[ ";
            for (int n : allRounds.get(i)) {

                output += n + " ";

            }
            output += "]\n";
        }

        return "\n{\n" + output + '}';

    }

    private String significantRoundsToString(String tabulation) {

        String output = "";

        for (int[] arr : significantRounds) {
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
            for (int i = 0; i < nRequested; i++) {
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
            for (int i = 0; i < nRequested; i++) {
                output[i] = pullChronology.get(i);
            }

            console.printStr("Took an array of length " + nRequested + " containing the oldest numbers -> " + output.toString() + "\n");
            return output;
        }

        public int[] getNMostFrequent(int nRequested) {
            int[] output = new int[nRequested];
            for (int i = 0; i < output.length; i++) {
                int max = 0;
                for (int j = 0; j < pullsPerNumber.length; j++) {
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
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == n) {
                    console.printStr("The array " + arr.toString() + " contains " + n + "\n");
                    return true;
                }
            }
            console.printStr("The array " + arr.toString() + " does not contains " + n + "\n");
            return false;
        }

        public void assignWins() {

            for (Profile current : players) {

                for (int i = 0; i < significantRounds.size(); i++) {

                    int winsInCurrentRound = 0;
                    for (int curentBetN : current.getSelectedBet(i)
                    ) {
                        if (intArrayContains(significantRounds.get(i), curentBetN))
                            winsInCurrentRound++;

                    }

                    current.addScore(winsInCurrentRound);
                    if (winsInCurrentRound == settings.getExtractionsPerRound())
                        current.addWin();

                }

                assignSpendings(current);
                assignWinMoney(current);
            }

            console.printStr("Assigned wins and money earned to all players" + "\n"); //<-- versione temp di joel che non sa cosa deve fare, TODO farlo giusto secondo necessità
        }

        private void assignSpendings(Profile p) {
            p.addToMoneySpent(settings.COST_OF_PLAY * p.getNOfBets());

        }

        private void assignWinMoney(Profile p) {

            for (int i = 0; i < p.getNOfBets(); i++)
                if (p.getHitsOnSelectedBet(i) == settings.getExtractionsPerRound())
                    p.addToMoneyWon(settings.getMoneyPerWin());

        }
    }
}

class Profile {
    private String name;
    private double moneyWon, moneySpent;
    private int nWins;
    private final ArrayList<int[]> betList;
    private final ArrayList<Integer> winList;

    public Profile() {
        moneyWon = 0;
        moneySpent = 0;
        nWins = 0;
        betList = new ArrayList<>();
        winList = new ArrayList<>();
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

    public int getNOfBets() {
        return betList.size();
    }

    public int getHitsOnSelectedBet(int n) {
        return winList.get(n);
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

    public ArrayList<Integer> getWinList() {
        return winList;
    }

    public void addScore(int n) {
        winList.add(n);
    }

    public void addWin() {
        nWins++;
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
                ",\n" + tabulation + "name=" + name +
                ",\n" + tabulation + "nWins=" + nWins +
                ",\n" + tabulation + "moneyWon=" + moneyWon +
                ",\n" + tabulation + "moneySpent=" + moneySpent +
                ",\n" + tabulation + "net=" + (moneyWon - moneySpent) +
                ",\n" + tabulation + "betList=" + betListToString(tabulation + "     ") +
                ",\n" + tabulation + "winList=" + winListToString(tabulation + "     ") +
                "\n}\n";
    }

    private String winListToString(String tabulation) {

        return winList.toString();

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

        return "{\n" + output + tabulation + "}";
    }
}