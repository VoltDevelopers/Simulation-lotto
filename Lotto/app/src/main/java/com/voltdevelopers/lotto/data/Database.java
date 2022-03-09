package com.voltdevelopers.lotto.data;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Database {

    private static Database instance = null;
    private final int numExitsInRound;

    private final ArrayList<int[]> allExits;
    private final ArrayList<PlayerStruct> allPatterns;

    private Database(int numExitsInRound) {
        allExits = new ArrayList<>();
        allPatterns = new ArrayList<>();
        PlayerStruct pattern_1 = new PlayerStruct("Pattern 1");
        PlayerStruct pattern_2 = new PlayerStruct("Pattern 2");
        PlayerStruct pattern_3 = new PlayerStruct("Pattern 3");
        PlayerStruct pattern_4 = new PlayerStruct("Pattern 4");
        PlayerStruct pattern_5 = new PlayerStruct("Pattern 5");

        allPatterns.add(pattern_1);
        allPatterns.add(pattern_2);
        allPatterns.add(pattern_3);
        allPatterns.add(pattern_4);
        allPatterns.add(pattern_5);

        this.numExitsInRound = numExitsInRound;
    }

    public static Database getInstanse(int numExitsInRound) {
        if (instance != null)
            return instance;
        instance = new Database(numExitsInRound);
        return instance;
    }

    public void addExits(int[] exits) {
        allExits.add(exits);
    }

    public void addPatternExits(int[][] exits) {
        for (int i = 0; i < 5; i++) {
            int[] arr = new int[numExitsInRound];
            System.arraycopy(exits[i], 0, arr, 0, numExitsInRound);
            allPatterns.get(i).allExits.add(arr);
        }
    }

    public ArrayList getExits() {
        return allExits;
    }

    public ArrayList getPatternExits() {
        return allPatterns;
    }

    // TODO: 09/03/22 - Make money

    @NonNull
    @Override
    public String toString() {
        return "Database{" +
                "numExitsInRound=" + numExitsInRound +
                ", allExits=" + allExits +
                ", allPatterns=" + allPatterns +
                ", " + allPatterns.get(0).playerName + "=" + allPatterns.get(0).toString() +
                ", " + allPatterns.get(1).playerName + "=" + allPatterns.get(1).toString() +
                ", " + allPatterns.get(2).playerName + "=" + allPatterns.get(2).toString() +
                ", " + allPatterns.get(3).playerName + "=" + allPatterns.get(3).toString() +
                ", " + allPatterns.get(4).playerName + "=" + allPatterns.get(4).toString() +
                '}';
    }

    private static class PlayerStruct {
        public String playerName;
        public final ArrayList<int[]> allExits;
        public double money;

        public PlayerStruct(String name) {
            playerName = name;
            allExits = new ArrayList<>();
        }

        @NonNull
        @Override
        public String toString() {
            return "PatternStruct{" +
                    "patternName='" + playerName + '\'' +
                    ", allExits=" + allExits +
                    ", money=" + money +
                    '}';
        }
    }
}