package com.voltdevelopers.lotto.src.players;

<<<<<<< HEAD
import com.voltdevelopers.lotto.data.Database;

=======
>>>>>>> d3c6866ec3b727afad4d2d56c245d5d0e006ae29
public class FirstPlayer extends Player {

    public FirstPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "Il Copione";
    }

    @Override
    public int[] createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getPullChronology()[i];
<<<<<<< HEAD
            bet[i] = Database.getInstance().getPullsChronology().get(89 - i);
=======
>>>>>>> d3c6866ec3b727afad4d2d56c245d5d0e006ae29
        }
        return bet;
    }
}
