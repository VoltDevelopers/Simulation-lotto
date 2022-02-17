package com.voltdevelopers.lotto.layout;

public class Console {
    public Console() {};

    public void printStr(String str) {
        System.out.println(str);
    }

    public void printExp(String exp) {
        System.out.println("EXCEPTION: " + exp);
    }
}