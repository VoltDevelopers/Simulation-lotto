package com.voltdevelopers.lotto.src.game;

public class NumberGenerator {

    public NumberGenerator(){}

    public int[] numSeries(int numsPerTurn){
        int[] series = new int[numsPerTurn];
        int val;
        for(int i = 0; i < numsPerTurn; i++){
            val = ((int) Math.abs(Math.random()) % 90);
            series[i] = val;
//            if(isAlreadyDrawn(series, val)){ //se il numero già presente in quelli da giocare
//                i--; //ripeti l'estrazione
//            }
        }
        return series;
    }

    private boolean isAlreadyDrawn(int[] bet, int val){
        for(int i = 0; i < bet.length; i++){
            if(bet[i] == val){
                return true;
            }
        }
        return false;
    }
}