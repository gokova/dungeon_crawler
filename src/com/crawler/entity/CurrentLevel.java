package com.crawler.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.crawler.map.EntranceTile;
import com.crawler.map.ExitTile;
import com.crawler.map.Map;
import com.crawler.util.Location;

public class CurrentLevel {

	private static CurrentLevel instance;
	private Map map;
	private Area dungeon;
	private Player player;
	private int dungeonSize;
	private boolean isEntrancePlaced;
	private boolean isExitPlaced;
	private List<Enemy> enemyList;

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

	public boolean getIsEntrancePlaced() {
		return isEntrancePlaced;
	}

	public void setIsEntrancePlaced(boolean isEntrancePlaced) {
		this.isEntrancePlaced = isEntrancePlaced;
	}

	public boolean getIsExitPlaced() {
		return isExitPlaced;
	}

	public void setIsExitPlaced(boolean isExitPlaced) {
		this.isExitPlaced = isExitPlaced;
	}

	public List<Enemy> getEnemyList() {
		return enemyList;
	}

	public void addEnemy(Enemy newEnemy) {
		enemyList.add(newEnemy);
	}

	private void initialise() {
		isEntrancePlaced = false;
		isExitPlaced = false;
		dungeonSize = 100;
		enemyList = new ArrayList<>();
		map = new Map(dungeonSize);
		dungeon = new Dungeon(0, 0, dungeonSize, dungeonSize);
		dungeon.generateDungeon();
		dungeon.fillMap();
		if (!isEntrancePlaced) {
			placeEntrance();
		}
		if (!isExitPlaced) {
			placeExit();
		}
	}

	public void gameLoop() {
		if (map.get(player.getLocation().getX(), player.getLocation().getY()) instanceof ExitTile) {
			generateNewLevel();
		}
		for (Enemy enemy : enemyList) {
			enemy.update();
		}
	}

	private void placeEntrance() {
		Random rnd = new Random();
		Area tempArea = dungeon;
		while (tempArea instanceof Dungeon) {
			tempArea = ((Dungeon) tempArea).getSubAreaList().get(0);
		}
		int x = tempArea.getX() + rnd.nextInt(tempArea.getWidth() - 2) + 1;
		int y = tempArea.getY() + rnd.nextInt(tempArea.getHeight() - 2) + 1;
		map.put(x, y, new EntranceTile(map.get(x, y)));
		CurrentLevel.getInstance().getPlayer().setLocation(x, y);
		CurrentLevel.getInstance().setIsEntrancePlaced(true);
	}

	private void placeExit() {
		Random rnd = new Random();
		Area tempArea = dungeon;
		while (tempArea instanceof Dungeon) {
			tempArea = ((Dungeon) tempArea).getSubAreaList().get(1);
		}
		int x = tempArea.getX() + rnd.nextInt(tempArea.getWidth() - 2) + 1;
		int y = tempArea.getY() + rnd.nextInt(tempArea.getHeight() - 2) + 1;
		map.put(x, y, new ExitTile(map.get(x, y)));
		CurrentLevel.getInstance().setIsExitPlaced(true);
	}

}
