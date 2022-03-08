package com.voltdevelopers.lotto.layout;

import android.util.Log;
import java.util.Scanner;

public class Console {

    private static Console instance = null;
    Scanner input;

    private Console() {
        input = new Scanner(System.in);
        // add file for logs
    }

    public static Console getInstance () {
        if (instance != null)
            return instance;
        instance = new Console();
        return instance;
    }

    private final String TAG = this.getClass().getSimpleName();

    public void printStr(String str) {
        System.out.println(str);
        Log.i(TAG, str);
    }

    public void printExp(Exception exp) {
        exp.printStackTrace();
        Log.e(TAG, "EXCEPTION", exp);
    }

    public String getStr(){
        String input = new Scanner(System.in).next();
        printStr(input);
        return input;
    }

    public int getInt(){
        int input = new Scanner(System.in).nextInt();
        printStr(String.valueOf(input));
        return input;
    }

}