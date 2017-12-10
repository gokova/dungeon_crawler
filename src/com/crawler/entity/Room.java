package com.crawler.entity;

import java.util.Random;

import com.crawler.map.EntranceTile;
import com.crawler.map.ExitTile;
import com.crawler.map.Map;
import com.crawler.map.RoomFloor;
import com.crawler.util.Location;

public class Room extends Area {

	private final int PADDING = 2;

	public Room(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void generateDungeon() {
		Random rnd = new Random();
		int _x, _y, _width, _height;
		int _area = 0;
		double _ratio = 0;

		do {
			_x = rnd.nextInt(getWidth() / 3);
			_y = rnd.nextInt(getHeight() / 3);
			_width = rnd.nextInt(getWidth() - _x) - PADDING;
			_height = rnd.nextInt(getHeight() - _y) - PADDING;
			_area = _width * _height;
			_ratio = (double) _width / (double) _height;
		} while (_area < 250 || _ratio < (double) 0.3 || _ratio > (double) 1.7);

		this.setX(getX() + _x + PADDING);
		this.setY(getY() + _y + PADDING);
		this.setWidth(_width);
		this.setHeight(_height);
	}

	@Override
	public void fillMap() {
		Random rnd = new Random();
		CurrentLevel level = CurrentLevel.getInstance();
		Map map = level.getMap();

		for (int j = getX(); j < getX() + getWidth(); j++) {
			for (int k = getY(); k < getY() + getHeight(); k++) {
				map.put(j, k, new RoomFloor());
			}
		}

		if (!level.getIsEntrancePlaced() && rnd.nextInt(100) < 20) {
			int x = getX() + rnd.nextInt(getWidth() - 2) + 1;
			int y = getY() + rnd.nextInt(getHeight() - 2) + 1;
			map.put(x, y, new EntranceTile(map.get(x, y)));
			level.getPlayer().setLocation(x, y);
			level.setIsEntrancePlaced(true);
		} else if (!level.getIsExitPlaced() && rnd.nextInt(100) < 20) {
			int x = getX() + rnd.nextInt(getWidth() - 2) + 1;
			int y = getY() + rnd.nextInt(getHeight() - 2) + 1;
			map.put(x, y, new ExitTile(map.get(x, y)));
			level.setIsExitPlaced(true);
		}
	}

	@Override
	public ClosestPoint getClosestPoint(int destX, int destY) {
		int centerX = getX() + getWidth() / 2;
		int centerY = getY() + getHeight() / 2;
		int distance = (int) (Math.pow(centerX - destX, 2) + Math.pow(centerY - destY, 2));
		return new ClosestPoint(distance, new Location(centerX, centerY));
	}

}
