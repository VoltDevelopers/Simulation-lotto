package com.voltdevelopers.lotto.src.game;

import android.util.Log;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.layout.Console;
import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.playerpatterns.FifthPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.FirstPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.FourthPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.Player;
import com.voltdevelopers.lotto.src.playerpatterns.SecondPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.ThirdPlayer;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class Game {

    private final int turnsGame;
    private final int pull = 5;

    Database db;
    Player[] playerPatterns;
    StdRandom random;
    Console console;

    public Game(int turnsGame) throws InputException {
        this.turnsGame = turnsGame;

        db = Database.getInstance();
        console = Console.getInstance();
        random = new StdRandom();
        playerPatterns = new Player[5];

        preGameLoop(10);
        initPlayers();
    }

    public void gameLoop() {
        int[] draw;

        for (int i = 0; i < turnsGame; i++) {
            draw = generateDraw();
            db.addSignificantPull(draw);

            playersPlay();
            sendPatternsData();
        }
    }

    private void initPlayers() {
        playerPatterns[0] = new FirstPlayer();
        playerPatterns[1] = new SecondPlayer();
        playerPatterns[2] = new ThirdPlayer();
        playerPatterns[3] = new FourthPlayer();
        playerPatterns[4] = new FifthPlayer();
    }

    private void preGameLoop(int games) {
        for (int i = 0; i < games; i++) {
            db.addPull(generateDraw());
            Log.i("LOOP", "Added new game to pregameloop");
        }
    }

    private void playersPlay() {
        for (int i = 0; i < playerPatterns.length; i++) {
            playerPatterns[i].createBet();
        }
    }

    private void sendPatternsData() {
        for (int i = 0; i < playerPatterns.length; i++) {
            db.addPlayerBet(playerPatterns[i].getId(), playerPatterns[i].getBet());
        }
    }

    private int[] generateDraw(){
        try {
            return StdRandom.getRandomArray(5, 90);
        } catch (InputException e) {
            e.printStackTrace();
        }
        return null;
    }
}