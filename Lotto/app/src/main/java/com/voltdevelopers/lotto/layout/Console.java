package com.voltdevelopers.lotto.layout;

import android.util.Log;

import java.util.Scanner;

public class Console {
    private static Console instance = null;
    private final String TAG = this.getClass().getSimpleName();
    private final Scanner inputSc;

    private Console() {
        inputSc = new Scanner(System.in);
        // add file for logs
    }

    public static Console getInstance() {
        if (instance == null) {
            instance = new Console();
        }

        return instance;
    }

    public void printStr(String str) {
        Log.i(TAG, str);
    }

    public void printExp(Exception exp) {
        exp.printStackTrace();
        Log.e(TAG, "EXCEPTION", exp);
    }

    public String getStr() {
        String input = inputSc.next();
        printStr(input);

        return input;
    }

    public int getInt() {
        int input = inputSc.nextInt();
        printStr(String.valueOf(input));

        return input;
    }
}