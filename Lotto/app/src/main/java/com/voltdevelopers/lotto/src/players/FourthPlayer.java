package com.voltdevelopers.lotto.src.players;

public class FourthPlayer extends Player {

    private int[] bet;

    public FourthPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "Il testardo";

        bet = new int[numsPerTurn];
    }

    @Override
    public int[] createBet() {
        return bet;
    }

    private void generateFinalBet(){
        int val = 0;
        for(int i = 0; i < numsPerTurn; i++){
            val = ((int) Math.abs(Math.random()) % 90) + 1;
            if(isAlreadyInBet(val)){ //se il numero già presente in quelli da giocare
                i--; //ripeti l'estrazione
            }
        }
    }

    private boolean isAlreadyInBet(int val){
        for(int i = 0; i < bet.length; i++){
            if(bet[i] == val){
                return true;
            }
        }
        return false;
    }
}
