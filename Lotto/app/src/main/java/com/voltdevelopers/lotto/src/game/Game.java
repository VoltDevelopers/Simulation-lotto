package com.voltdevelopers.lotto.src.game;


import android.app.Activity;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.data.Settings;
import com.voltdevelopers.lotto.layout.Console;
import com.voltdevelopers.lotto.src.exception.InputException;
import com.voltdevelopers.lotto.src.playerpatterns.FifthPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.FirstPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.FourthPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.Player;
import com.voltdevelopers.lotto.src.playerpatterns.SecondPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.ThirdPlayer;
import com.voltdevelopers.lotto.src.random.StdRandom;

public class Game{

    private final int turnsGame;

    Player[] playerPatterns;
    OnData onData;
    Activity activity;

    public Game(int turnsGame, OnData onData,Activity activity) throws InputException {
        this.turnsGame = turnsGame;
        playerPatterns = new Player[5];
        this.onData = onData;
        this.activity = activity;
        initPlayers();
        preGameLoop(Settings.getInstance().getPresetGameCount());
    }

    public void gameLoop() {



            for (int i = 0; i < turnsGame; i++) {

                playersPlayBets();
                Database.getInstance().addSignificantPull(generateDraw());
                sendPatternsData();
                Console.getInstance().printStr("New significant pull");
                Database.getInstance().assignWinsInCurrentGame(i);
                int finalI = i;
                activity.runOnUiThread(() ->{

                    int[] currentWins = {Database.getInstance().getPlayerWinList(0).get(finalI), Database.getInstance().getPlayerWinList(1).get(finalI), Database.getInstance().getPlayerWinList(2).get(finalI), Database.getInstance().getPlayerWinList(3).get(finalI), Database.getInstance().getPlayerWinList(4).get(finalI)};
                    onData.addDataToFirstChart(finalI,currentWins);

                });

                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

    }

    private void preGameLoop(int games) {
        for (int i = 0; i < games; i++) {
            Database.getInstance().addPull(generateDraw());
            Console.getInstance().printStr("New historic pull");
        }
    }

    private void initPlayers() {
        playerPatterns[0] = new FirstPlayer();
        playerPatterns[1] = new SecondPlayer();
        playerPatterns[2] = new ThirdPlayer();
        playerPatterns[3] = new FourthPlayer();
        playerPatterns[4] = new FifthPlayer();
    }

    private void playersPlayBets() {
        for (int i = 0; i < playerPatterns.length; i++) {
            playerPatterns[i].createBet();
        }
    }

    private void sendPatternsData() {
        for (int i = 0; i < playerPatterns.length; i++) {
            Database.getInstance().addPlayerBet(playerPatterns[i].getId(), playerPatterns[i].getBet());
        }
    }

    private int[] generateDraw(){
        try {
            return StdRandom.getRandomArray(Settings.EXTRACTIONS, Settings.MAX_EXIT);
        } catch (InputException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface OnData{

        public void addDataToFirstChart(int x, int[]currentWins);
        public void addDataToSecondChart(int x, double[]currentNets);

    }

}