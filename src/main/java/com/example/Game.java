package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Game {

    private static Game game = null;

    public Board board;
    public List<Player> players;
    public int maxPlayers;
    private int currentPlayers;
    private Lock lock;  //para la exclusion mutua
    private Condition full; //varibale de condicion
    private Condition empty; //varibale de condicion
    private Random rand;

    private Game() {
        board = Board.getBoardInstance();
        this.players = new ArrayList<>();
        this.maxPlayers = 7;
        currentPlayers = players.size();
        lock = new ReentrantLock();
        full = lock.newCondition();
        empty = lock.newCondition();
        rand = new Random();
    }

    public static Game getInstance(){
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public void addPlayer(Player player) throws InterruptedException {
        lock.lock();

        try {
            while (currentPlayers >= maxPlayers) {
                full.await();
            }
            //sc
            //comprueba que el jugador no exista en la lista para poder añadirlo
            if (!players.contains(player)) {
                players.add(player);
                System.out.print("Jugador " + player.getId() + " entra en la partida...");
                currentPlayers = currentPlayers + 1;
                System.out.print("OK\n");
                //señalo variable condicion
                empty.signal();
            } else {
                System.out.println("El jugador " + player.getId() + " no puede entrar porque ya hay un jugador con ese id en la lista.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void removePlayer(Player player) throws InterruptedException {
        lock.lock();
        try {
            while (currentPlayers == 0) {
                empty.await();
            }
            //sc
            //comprueba que el jugador exista en la lista de jugadoers para poder sacarlo
            if (players.contains(player)) {
                players.remove(player);
                System.out.print("Jugador " + player.getId() + " abandona la partida...");
                currentPlayers--;
                System.out.print("OK\n");
                full.signal();
            } else {
                System.out.println("El jugador " + player.getId() + " no puede salir porque se encuentra en la lista.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void play(int playerid) {
        Player player = players.get(playerid);
        if (currentPlayers > 0) {
            //Player player = players.get(rand.nextInt(currentPlayers));
            Box box = board.getBoxes()[rand.nextInt(board.rows())][rand.nextInt(board.columns())];

            lock.lock();
            try {
                if (Boolean.FALSE.equals(box.getOccupied())) {
                    //SECCION CRITICA
                    if (!player.isNextTurnSkipped()) {
                        // CRITICAL SECTION
                        box.setOccupied(true);
                        box.setPlayer(player);
                        player.setTokens(player.getTokens() - 1);
                        int pointsEarned = 0;
                        switch (box.getType()) {
                            case 0:
                                pointsEarned = rand.nextInt(100);
                                player.setScore(player.getScore() + pointsEarned);
                                System.out.println("El juugador " + player.getId() + " ha gando +" + pointsEarned + "puntos");
                                box.setValue("+" + pointsEarned);
                                break;
                            case 1:
                                pointsEarned = rand.nextInt(4);
                                player.setScore(player.getScore() * pointsEarned);
                                System.out.println("El juugador " + player.getId() + " ha recivido un BOOST de x" + pointsEarned + "puntos");
                                box.setValue("x" + pointsEarned);
                                break;
                            case 2:
                                player.setNextTurnSkipped(true);
                                System.out.println("El jugador " + player.getId() + " pierde el siguiente turno");
                                box.setValue("Pierde turno");
                                break;
                            default:
                                break;
                        }
                    } else {
                        player.setNextTurnSkipped(false);
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public boolean gameEnded() {
        boolean end = false;
        if (currentPlayers == 0) {
            System.out.println("No quedan jugadores, fin del juego");
            end = !end;
        }
        return end;
    }

    public void countPlayers() {
        lock.lock();
        //sc
        System.out.println("Numero de jugadores actual: " + currentPlayers);
        //sc
        lock.unlock();
    }
}
