package com.crawler.entity;

public class Map {

	private int[][] map;

	public Map(int dungeonSize) {
		this.map = new int[dungeonSize][dungeonSize];
	}

	public int get(int x, int y) {
		return map[y][x];
	}

	public void put(int x, int y, int object) {
		map[y][x] = object;
	}

	public boolean canMove(int x, int y) {
		boolean result = false;
		if (map[y][x] != 0) {
			result = true;
		}
		return result;
	}
}
