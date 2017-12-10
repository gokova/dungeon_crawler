package com.crawler.entity;

import java.awt.image.BufferedImage;

import com.crawler.util.Location;
import com.crawler.util.SpriteHelper;

public abstract class Enemy extends Character {

	private int spriteNo;

	public Enemy(Location location, int spriteNo) {
		super(location);
		this.spriteNo = spriteNo;
	}

	@Override
	public BufferedImage getImage() {
		return SpriteHelper.getInstance().getImage(spriteNo);
	}

}
