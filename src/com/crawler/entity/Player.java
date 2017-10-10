package com.crawler.entity;

import com.crawler.util.Position;

public class Player {

	private Position location;

	public Player() {
		this.location = new Position();
	}

	public Player(Position location) {
		this.location = location;
	}

	public Position getLocation() {
		return location;
	}

	public void setLocation(Position location) {
		this.location = location;
	}

	public void setLocation(int x, int y) {
		this.location.setLocation(x, y);
	}
}
