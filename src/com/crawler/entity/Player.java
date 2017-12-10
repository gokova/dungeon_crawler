package com.crawler.entity;

import java.awt.image.BufferedImage;

import com.crawler.util.Location;
import com.crawler.util.SpriteHelper;

public class Player extends Character {

	private final int SPRITE_NO = 2113;

	public Player(Location location) {
		super(location);
	}

	@Override
	public BufferedImage getImage() {
		return SpriteHelper.getInstance().getImage(SPRITE_NO);
	}

}
