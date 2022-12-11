package com.example;

/**
 * Jugador
 */
public class Player {

    private int score;
    private int id;
    private int tokens;
    private Color color;
    private boolean nextTurnSkipped;

    public Player(int id, int tokens) {
        this.id = id;
        this.tokens = tokens;
        this.color = color.GRAY;
        this.nextTurnSkipped = false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isNextTurnSkipped() {
        return nextTurnSkipped;
    }

    public void setNextTurnSkipped(boolean nextTurnSkipped) {
        this.nextTurnSkipped = nextTurnSkipped;
    }

    @Override
    public String toString() {
        return "Player{" + "score=" + score + ", id=" + id + ", tokens=" + tokens + '}';
    }

    @Override
    public boolean equals(Object obj) {
        boolean value = false;

        final Player other = (Player) obj;
        value = this.id == other.id;
        return value;
    }
}
