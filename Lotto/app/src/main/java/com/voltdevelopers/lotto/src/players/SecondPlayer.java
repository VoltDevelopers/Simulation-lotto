package com.voltdevelopers.lotto.src.players;

<<<<<<< HEAD
import com.voltdevelopers.lotto.data.Database;

=======
>>>>>>> d3c6866ec3b727afad4d2d56c245d5d0e006ae29
public class SecondPlayer extends Player {
    public SecondPlayer(int numsPerTurn) {
        super(numsPerTurn);
        this.name = "Il Ritardatario";
    }

    @Override
    public int[] createBet() {
        int[] bet = new int[numsPerTurn];
        for(int i = 0; i < numsPerTurn; i++){
            //TODO: bet[i] = getPullChronology()[pullChronology.length - 1 - i];
<<<<<<< HEAD
            bet[i] = Database.getInstance().getPullsChronology().get(i);
=======
>>>>>>> d3c6866ec3b727afad4d2d56c245d5d0e006ae29
        }
        return bet;
    }
}
