package com.crawler.entity;

import java.awt.image.BufferedImage;

import com.crawler.util.Location;

public abstract class Character {

	private Location location;

	public Character(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(int x, int y) {
		this.location.setLocation(x, y);
	}

	public void moveDown() {
		int newX = getLocation().getX();
		int newY = getLocation().getY() + 1;
		move(newX, newY);
	}

	public void moveUp() {
		int newX = getLocation().getX();
		int newY = getLocation().getY() - 1;
		move(newX, newY);
	}

	public void moveLeft() {
		int newX = getLocation().getX() - 1;
		int newY = getLocation().getY();
		move(newX, newY);
	}

	public void moveRight() {
		int newX = getLocation().getX() + 1;
		int newY = getLocation().getY();
		move(newX, newY);
	}

	private void move(int newX, int newY) {
		if (CurrentLevel.getInstance().getMap().canMove(newX, newY)) {
			getLocation().setLocation(newX, newY);
		}
	}

	public abstract BufferedImage getImage();

}
