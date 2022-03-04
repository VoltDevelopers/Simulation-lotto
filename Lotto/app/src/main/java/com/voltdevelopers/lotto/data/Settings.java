package com.voltdevelopers.lotto.data;

public class Settings {

    private static Settings istance = null;
    private int numbersXBet;

    private Settings (){ }

    public static Settings getIstance(){

        return istance == null ? istance = new Settings() : istance;

    }

    public int getNumbersXBet() {

        return numbersXBet;

    }

    public void setNumbersXBet(int numbersXBet) {

        this.numbersXBet = numbersXBet;

    }
}

