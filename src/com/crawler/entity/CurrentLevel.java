package com.crawler.entity;

import java.util.Scanner;

import com.crawler.util.Position;

public class CurrentLevel {

	private static CurrentLevel instance;
	private int[][] map;
	private Area newMap;
	private Player player;
	private int dungeonSize;

	private CurrentLevel() {
	}

	public static CurrentLevel getInstance() {
		if (instance == null) {
			instance = new CurrentLevel();
			instance.initialise();
		}
		return instance;
	}

	public static CurrentLevel generateNewLevel() {
		instance = new CurrentLevel();
		instance.initialise();
		return instance;
	}

	public int[][] getMap() {
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
		map = new int[dungeonSize][dungeonSize];
		player = new Player(new Position(10, 10));
		newMap = new Dungeon(0, 0, dungeonSize, dungeonSize);
		newMap.generateDungeon();
		newMap.fillMap();
		printDungeon();
	}

	void printDungeon() {
		Scanner scn = new Scanner(System.in);
		for (int i = 0; i < dungeonSize; i++) {
			for (int j = 0; j < dungeonSize; j++) {
				String format = "%c ";
				if (map[i][j] != 0) {
					System.out.print(String.format(format, (char) (map[i][j] + 97)));
				} else {
					System.out.print(String.format(format, '.'));
				}
			}
			System.out.println(" ");
		}
		System.out.println("Press enter to continue");
		scn.nextLine();
		scn.close();
	}

}
