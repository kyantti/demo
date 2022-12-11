package com.example;

public class PlayBoard extends Thread {

    private Game game;

    public PlayBoard(Game game) {
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
        for (int i = 0; i < game.getCurrentPlayers(); i++) {
            game.play(i);
        }
    }
}
