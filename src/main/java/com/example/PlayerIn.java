package com.example;

public class PlayerIn implements Runnable {

    private Game game;

    public PlayerIn(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        for (int i = 0; i<7; i++) {
            Player player = new Player(Game.getInstance().players.size(), 10);
            try {
                game.addPlayer(player);
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
