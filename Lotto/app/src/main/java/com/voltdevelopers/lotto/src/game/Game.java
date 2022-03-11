package com.voltdevelopers.lotto.src.game;

import com.voltdevelopers.lotto.data.Database;
import com.voltdevelopers.lotto.src.playerpatterns.FifthPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.FirstPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.FourthPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.Player;
import com.voltdevelopers.lotto.src.playerpatterns.SecondPlayer;
import com.voltdevelopers.lotto.src.playerpatterns.ThirdPlayer;

public class Game {

    private int gnum; // Numero partite
    // TODO: 10/03/22 Get TURN_NUMS from db
    private final int TURN_NUMS = 5;
    // TODO: 10/03/22 Get PLAYERS from db
    private final int PLAYERS = 5;
    private NumberGenerator gen; //ho creato una classe a parte perché dovevo randomizzare in più classi diverse
    private Player[] playerPatterns;

    public Game(int gnum) {
        this.gnum = gnum;
        initPlayers();
    }

    public void gameLoop(){
        int draw[];
        int results[] = new int[5];
        for (int i = 0; i < gnum; i++) {
            draw = gen.numSeries(5); //creazione cinquina estratta

            playersPlay(); //chiamata ai singoli giocatori che creano una giocata secondo i loro criteri, e la inviano al db
            results = buildResultsArray(draw); //crea un array, dove per ogni indice ci sono i numeri vinti nel singolo round per il singolo giocatore
            //TODO manda i dati delle vincite al database

            Database.get().addPull(draw); //aggiorno i valori estratti con l'estrazione
            // *Chiede db di visualizare i dati*
        }
    }

    private void preGameLoop(int games) {
        for (int i = 0; i < games; i++) {
            Database.get().addPull(gen.numSeries(5)); //estrae cinquine per riempire i dati dei valori estratti
        }
    }

    public void sendAllData(){

    }

    private void playersPlay(){
        for(int i = 0; i < PLAYERS; i++){
            playerPatterns[i].createBet();
        }
    }

    private int[] buildResultsArray(int[] draw){
        int results[] = new int[5];
        for(int i = 0; i < PLAYERS; i++){
            //TODO checkPlayerResult(draw, Database.get().MI SERVONO I BETS JOEL O NO COMBINO)
        }
        return results;
    }

    private int checkPlayerResult(int[] draw, int[] bet){ //ritorna un int che indica quanti numeri ha vinto il giocatore in questione
        int w = 0;
        for(int i = 0; i < TURN_NUMS; i++){
            if(bet[i] == draw[i]){
                w++;
            }
        }
        return w;
    }

    private void initPlayers(){
        playerPatterns[0] = new FirstPlayer (TURN_NUMS);
        playerPatterns[1] = new SecondPlayer(TURN_NUMS);
        playerPatterns[2] = new ThirdPlayer (TURN_NUMS);
        playerPatterns[3] = new FourthPlayer(TURN_NUMS);
        playerPatterns[4] = new FifthPlayer (TURN_NUMS);
    }
}