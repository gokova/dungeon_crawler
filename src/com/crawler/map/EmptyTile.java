package com.crawler.map;

import java.awt.Color;

public class EmptyTile extends MapTile {

	private final static int SPRITE_NO = 279;

	public EmptyTile() {
		super(SPRITE_NO, true);
	}

	@Override
	public Color getMinimapColor() {
		return new Color(0.56f, 0.41f, 0.23f);
	}

}
