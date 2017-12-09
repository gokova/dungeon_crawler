package com.crawler.util;

public class Location {

	private int x;
	private int y;

	public Location() {
		setX(0);
		setY(0);
	}

	public Location(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	private void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	private void setY(int y) {
		this.y = y;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
