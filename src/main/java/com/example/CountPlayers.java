package com.example;

public class CountPlayers extends Thread {

    private Game game;

    public CountPlayers(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        for (int i = 0; i<7; i++) {
            try {
                game.countPlayers();
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
