package com.crawler.util;

import java.awt.Point;

public class Position {

	private int x;
	private int y;

	public Position() {
		setX(0);
		setY(0);
	}

	public Position(int x, int y) {
		setX(x);
		setY(y);
	}

	public Position(Point point) {
		setX((int) point.getX());
		setY((int) point.getY());
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
