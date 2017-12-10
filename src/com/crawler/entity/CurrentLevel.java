package com.crawler.entity;

import com.crawler.util.Location;

public class CurrentLevel {

	private static CurrentLevel instance;
	private Map map;
	private Area dungeon;
	private Player player;
	private int dungeonSize;

	private CurrentLevel() {
		player = new Player(new Location(10, 10));
	}

	public static CurrentLevel getInstance() {
		if (instance == null) {
			instance = new CurrentLevel();
		}
		return instance;
	}

	public CurrentLevel generateNewLevel() {
		instance.initialise();
		return instance;
	}

	public Map getMap() {
		return map;
	}

	public Player getPlayer() {
		return player;
	}

	public int getDungeonSize() {
		return dungeonSize;
	}

	private void initialise() {
		dungeonSize = 100;
		map = new Map(dungeonSize);
		dungeon = new Dungeon(0, 0, dungeonSize, dungeonSize);
		dungeon.generateDungeon();
		dungeon.fillMap();
	}

	public void gameLoop() {

	}

}
