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

    NumberGenerator gen;

    public Game(int turnsGame) throws InputException {
        this.turnsGame = turnsGame;
        db = Database.getInstance(pull, 18d);
        preGameLoop(10);

        random = new StdRandom();
        console = Console.getInstance();
        playerPatterns = new Player[5];
        initPlayers();
    }

    public void gameLoop() {
        int[] draw = new int[0];
        int results[] = new int[5];

        for (int i = 0; i < turnsGame; i++) {
//            try {
//                draw = StdRandom.getRandomArray(5, 90);
//            } catch (InputException e) {
//                e.printStackTrace();
//            }
            db.addPull(draw);
            console.printStr("Added to db");

            playersPlay(); //chiamata ai singoli giocatori che creano una giocata secondo i loro criteri, e la inviano al db
            results = buildResultsArray(draw); //crea un array, dove per ogni indice ci sono i numeri vinti nel singolo round per il singolo giocatore
            //TODO manda i dati delle vincite al database

             //aggiorno i valori estratti con l'estrazione
            // *Chiede db di visualizare i dati*
        }
    }

    private void preGameLoop(int games) throws InputException {
        for (int i = 0; i < games; i++) {
            db.addPull(StdRandom.getRandomArray(5, 90)); //estrae cinquine per riempire i dati dei valori estratti
            Log.i("LOOP", "Added new game to pregameloop");
        }
    }

    public void sendAllData() {

    }

    private void playersPlay() {
        for (int i = 0; i < playerPatterns.length; i++) {
            playerPatterns[i].createBet();
        }
    }

    private int[] buildResultsArray(int[] draw) {
        int results[] = new int[5];
        for (int i = 0; i < playerPatterns.length; i++) {
            results[i] = checkPlayerResult(draw, Database.getInstance(5, 18).getPlayerLastBet(1));

        }
        return results;
    }

    private int checkPlayerResult(int[] draw, int[] bet) { //ritorna un int che indica quanti numeri ha vinto il giocatore in questione
        int w = 0;
        for (int i = 0; i < pull; i++) {
            if (bet[i] == draw[i]) {
                w++;
            }
        }
        return w;
    }

    private void initPlayers() {
        playerPatterns[0] = new FirstPlayer(pull);
        playerPatterns[1] = new SecondPlayer(pull);
        playerPatterns[2] = new ThirdPlayer(pull);
        playerPatterns[3] = new FourthPlayer(pull);
        playerPatterns[4] = new FifthPlayer(pull);
    }
}