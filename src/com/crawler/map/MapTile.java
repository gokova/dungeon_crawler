package com.crawler.map;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.crawler.util.SpriteHelper;

public abstract class MapTile {

	private int spriteNo;
	private boolean isSolid;

	public MapTile(int spriteNo) {
		this.spriteNo = spriteNo;
		this.isSolid = false;
	}

	public MapTile(int spriteNo, boolean isSolid) {
		this.spriteNo = spriteNo;
		this.isSolid = isSolid;
	}

	public BufferedImage getImage() {
		return SpriteHelper.getInstance().getImage(spriteNo);
	}

	public boolean getIsSolid() {
		return isSolid;
	}

	public abstract Color getMinimapColor();

}
