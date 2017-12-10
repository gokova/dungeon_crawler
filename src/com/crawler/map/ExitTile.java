package com.crawler.map;

import java.awt.Color;

public class ExitTile extends TileWithBackground {

	private final static int SPRITE_NO = 1064;

	public ExitTile(MapTile background) {
		super(SPRITE_NO, background);
	}

	@Override
	public Color getMinimapColor() {
		return Color.BLUE;
	}

}
