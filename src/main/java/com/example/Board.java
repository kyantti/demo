package com.example;

import java.util.Random;

/**
 * Tablero
 */
public class Board {

    private static Board board = null;
    private static Random rand = new Random();

    public Box[][] boxes;

    private Board() {

    }

    public static Board getBoardInstance(){
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public void setBoxesProperties(){
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                boxes[i][j] = new BoxBuilder().withOccupied(false).withtype(rand.nextInt(3)).build();
            }
        }
    }

    public Box[][] getBoxes() {
        return boxes;
    }

    public int rows() {
        return boxes.length;
    }

    public int columns() {
        return boxes[0].length;
    }

    public void emptyBoxes(){
        for (int i = 0; i < boxes.length; i++) {
            for (int j = 0; j < boxes[0].length; j++) {
                boxes[i][j].setOccupied(false);
            }
        }
    }    
}
