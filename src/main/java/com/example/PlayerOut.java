package com.example;

import java.util.Random;

public class PlayerOut implements Runnable {

    private Game game;
    private Random rand = new Random();

    public PlayerOut(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        for (int i = 0; i<7; i++) {
            if (!Game.getInstance().players.isEmpty()) {
                Player player = Game.getInstance().players.get(rand.nextInt(Game.getInstance().players.size()));
                try {
                    game.removePlayer(player);
                    Thread.sleep(700);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
