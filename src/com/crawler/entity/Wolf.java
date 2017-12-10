package com.crawler.entity;

import com.crawler.util.Location;

public class Wolf extends Enemy {

	private static final int SPRITE_NO = 2255;

	public Wolf(Location location) {
		super(location, SPRITE_NO);
	}

}
