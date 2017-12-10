package com.crawler.map;

import java.awt.Color;

public class EntranceTile extends TileWithBackground {

	private final static int SPRITE_NO = 1065;

	public EntranceTile(MapTile background) {
		super(SPRITE_NO, background);
	}

	@Override
	public Color getMinimapColor() {
		return Color.BLUE;
	}

}
