package com.crawler.entity;

import java.util.Random;

import com.crawler.util.Position;

public class Room extends Area {

	private final int PADDING = 2;

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
		int tempArea = 0;
		double tempRatio = 0;

		do {
			x = rnd.nextInt(getWidth() / 3);
			y = rnd.nextInt(getHeight() / 3);
			width = rnd.nextInt(getWidth() - x);
			height = rnd.nextInt(getHeight() - y);
			tempArea = (width - PADDING) * (height - PADDING);
			tempRatio = ((double) width - PADDING) / ((double) height - PADDING);
		} while (tempArea < 250 || tempRatio < (double) 0.3 || tempRatio > (double) 1.7);

		this.setX(getX() + x + PADDING);
		this.setY(getY() + y + PADDING);
		this.setWidth(width - PADDING);
		this.setHeight(height - PADDING);
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

	@Override
	public Position getConnectionPoint() {
		return new Position(getX() + getWidth() / 2, getY() + getHeight() / 2);
	}

}
