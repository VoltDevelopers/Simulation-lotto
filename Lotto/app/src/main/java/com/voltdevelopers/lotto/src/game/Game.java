package com.voltdevelopers.lotto.src.game;

public class Game {

    private int gnum; // Numero partite

    public Game(int gnum) {
        this.gnum = gnum;
    }

    public void gameLoop(){
        for (int i = 0; i < gnum; i++) {
            // Crea 5 numeri random estratti
            // controlla chi ha vinto e chi a perso
            // manda i dati

            // *Chiede db di visualizare i dati*
        }
    }

    private void preGameLoop(int games){
        for (int i = 0; i < games; i++) {

        }
    }

    public void sendAllData(){

    }
}