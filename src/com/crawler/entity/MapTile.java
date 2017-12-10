package com.crawler.entity;

import java.awt.image.BufferedImage;

import com.crawler.util.SpriteHelper;

public abstract class MapTile {

	private int spriteNo;

	public MapTile(int spriteNo) {
		this.spriteNo = spriteNo;
	}

	public BufferedImage getImage() {
		return SpriteHelper.getInstance().getImage(spriteNo);
	}

}
