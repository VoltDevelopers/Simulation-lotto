package com.voltdevelopers.lotto.layout;

import android.util.Log;
import java.util.Scanner;

public class Console {

    private final String TAG = this.getClass().getSimpleName();
    private static Console instance = null;
    Scanner inputSc;

    private Console() {
        inputSc = new Scanner(System.in);
    }

    public static Console getInstance () {
        if (instance != null)
            return instance;
        instance = new Console();
        return instance;
    }

    public void printStr(String str) {
        Log.i(TAG, str);
    }

    public void printExp(Exception exp) {
        exp.printStackTrace();
        Log.e(TAG, "EXCEPTION", exp);
    }

    public int getInt(){
        int input = inputSc.nextInt();
        printStr(String.valueOf(input));
        return input;
    }
}