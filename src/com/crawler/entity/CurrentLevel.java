package com.crawler.entity;

import java.util.Scanner;

import com.crawler.util.Location;

public class CurrentLevel {

	private static CurrentLevel instance;
	private Map map;
	private Area dungeon;
	private Player player;
	private int dungeonSize;

	private CurrentLevel() {

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
		player = new Player(new Location(10, 10));
		dungeon = new Dungeon(0, 0, dungeonSize, dungeonSize);
		dungeon.generateDungeon();
		dungeon.fillMap();
		printDungeon();
	}

	private void printDungeon() {
		Scanner scn = new Scanner(System.in);
		for (int i = 0; i < dungeonSize; i++) {
			for (int j = 0; j < dungeonSize; j++) {
				String format = "%c ";
				if (map.get(j, i) != 0) {
					System.out.print(String.format(format, (char) (map.get(j, i) + 97)));
				} else {
					System.out.print(String.format(format, '.'));
				}
			}
			System.out.println(" ");
		}
		System.out.println("Press enter to continue");
		// scn.nextLine();
		scn.close();
	}

}
