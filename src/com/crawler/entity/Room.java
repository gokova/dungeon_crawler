package com.crawler.entity;

import java.util.Random;

public class Room extends Area {

	public Room() {
		super(0, 0, 0, 0);
	}

	public Room(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void generateDungeon() {
		Random rnd = new Random();
		int x, y, width, height;

		do {
			x = rnd.nextInt(getWidth() / 2);
			y = rnd.nextInt(getHeight() / 2);
			width = rnd.nextInt(getWidth() - x);
			height = rnd.nextInt(getHeight() - y);
		} while (width * height < 250 || ((double) width / (double) height) < (double) 0.3
				|| ((double) width / (double) height) > (double) 1.7);

		this.setX(getX() + x);
		this.setY(getY() + y);
		this.setWidth(width);
		this.setHeight(height);
	}

	@Override
	public void fillMap() {
		Random rnd = new Random();
		int[][] map = CurrentLevel.getInstance().getMap();

		for (int j = getX(); j < getX() + getWidth(); j++) {
			for (int k = getY(); k < getY() + getHeight(); k++) {
				int num = rnd.nextInt(8) + 1;
				map[j][k] = num;
			}
		}
	}

}
