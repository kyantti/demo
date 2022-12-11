package com.example;

public class BoxBuilder {
    private boolean occupied;
    private Player player;
	private String value;
	private int type;
	

	public BoxBuilder() {
		value = "";
		player = null;
	}

	public BoxBuilder withOccupied(final boolean occupied){
        this.occupied = occupied;
		return this;
    }

	public BoxBuilder withplayer(final Player player) {
		this.player = player;
		return this;
	}

    public BoxBuilder withvalue(final String value) {
		this.value = value;
		return this;
	}

	public BoxBuilder withtype(final int type) {
		this.type = type;
		return this;
	}

	public Box build() {
		return new Box(this.occupied, this.player, this.value, this.type);
	}

}
