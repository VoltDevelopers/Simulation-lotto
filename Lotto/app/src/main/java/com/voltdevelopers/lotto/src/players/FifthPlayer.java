package com.voltdevelopers.lotto.src.players;

<<<<<<< HEAD
import com.voltdevelopers.lotto.data.Database;

=======
>>>>>>> d3c6866ec3b727afad4d2d56c245d5d0e006ae29
public class FifthPlayer extends Player {
    public FifthPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "L'ingenuo";
    }

    @Override
    public int[] createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getMostPulled()[i];
        }
        return bet;
    }
}
