package com.voltdevelopers.lotto.data;

public class Settings {

    private static Settings instance = null;
    private int numbersXBet;

    private Settings (){ }

    public static Settings getInstance(){

        return instance == null ? instance = new Settings() : instance;

    }

    public int getNumbersXBet() {

        return numbersXBet;

    }

    public void setNumbersXBet(int numbersXBet) {

        this.numbersXBet = numbersXBet;

<<<<<<< HEAD
    }
}

=======
}
>>>>>>> d3c6866ec3b727afad4d2d56c245d5d0e006ae29
