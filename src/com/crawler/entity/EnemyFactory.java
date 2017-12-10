package com.crawler.entity;

import java.util.Random;

import com.crawler.util.Location;

public class EnemyFactory {

	private Random rnd;

	public EnemyFactory() {
		rnd = new Random();
	}

	public Enemy createEnemy(Location location) {
		Enemy newEnemy;
		if (rnd.nextBoolean()) {
			newEnemy = new Wolf(location);
		} else {
			newEnemy = new Snake(location);
		}
		return newEnemy;
	}

}
