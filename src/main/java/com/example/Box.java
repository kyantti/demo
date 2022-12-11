package com.example;

public class Box {

    private boolean occupied;
    private Player player;
    private String value;
    private int type;

    public Box() {
        this.occupied = false;
        this.player = null;
        this.value = "";
    }

    public Box(boolean occupied, Player player, String value, int type) {
        this.occupied = occupied;
        this.value = value;
        this.player = player;
        this.type = type;
    }

    public boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Box [occupied=" + occupied + ", player=" + player + ", value=" + value + ", type=" + type + "]";
    }

    
}
