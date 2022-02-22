package com.voltdevelopers.lotto.data;

public class Settings {

    private static Settings instance = null;
    private int nOfPulls;

    private Settings(){

    }

    public static Settings get(){

        if(instance != null)
            return instance;
        instance = new Settings();
        return instance;
    }

    public int getnOfPulls() {
        return nOfPulls;
    }

    public void setnOfPulls(int nOfPulls) {
        this.nOfPulls = nOfPulls;
    }

}