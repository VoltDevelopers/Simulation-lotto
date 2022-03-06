package com.voltdevelopers.lotto.src.players;

public class ThirdPlayer extends Player {

    public ThirdPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "L'azzardoso";
    }

    @Override
    public int[] createBet() {
        int[] bet = new int[numsPerTurn];
        int val = 0;
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: val = generateRandNum();
            if(isAlreadyInBet(bet, val)){ //se il numero già presente in quelli da giocare
                i--; //ripeti l'estrazione
            }
        }
        return bet;
    }

    private boolean isAlreadyInBet(int[] bet, int val){
        for(int i = 0; i < bet.length; i++){
            if(bet[i] == val){
                return true;
            }
        }
        return false;
    }
}
