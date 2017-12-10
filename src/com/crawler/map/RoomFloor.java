package com.crawler.map;

import java.util.Random;

public class RoomFloor extends MapTile {

	private final static int SPRITE_NO = 307;

	public RoomFloor() {
		super(generateTileNo());
	}

	private static int generateTileNo() {
		Random rnd = new Random();
		int result = 0;
		int randomInt = rnd.nextInt(7);
		if (randomInt < 5) {
			result = 0;
		} else if (randomInt < 6) {
			result = 2;
		} else {
			result = 3;
		}
		return result + SPRITE_NO;
	}

}
