package com.crawler.entity;

public class Map {

	private MapTile[][] map;

	public Map(int dungeonSize) {
		this.map = new MapTile[dungeonSize][dungeonSize];
	}

	public MapTile get(int x, int y) {
		return map[y][x];
	}

	public void put(int x, int y, MapTile object) {
		map[y][x] = object;
	}

	public boolean canMove(int x, int y) {
		boolean result = false;
		if (map[y][x] != null && !(map[y][x] instanceof EmptyTile)) {
			result = true;
		}
		return result;
	}
}
