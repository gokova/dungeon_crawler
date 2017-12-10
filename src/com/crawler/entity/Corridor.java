package com.crawler.entity;

import java.util.Random;

public class Corridor extends MapTile {

	private final static int SPRITE_NO = 76;

	public Corridor() {
		super(new Random().nextInt(2) + SPRITE_NO);
	}

}
