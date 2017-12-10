package com.crawler.entity;

import com.crawler.util.Location;

public class Snake extends Enemy {

	private static final int SPRITE_NO = 2210;

	public Snake(Location location) {
		super(location, SPRITE_NO);
	}

}
