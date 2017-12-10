package com.crawler.map;

public abstract class TileWithBackground extends MapTile {

	private MapTile background;

	public TileWithBackground(int spriteNo, MapTile background) {
		super(spriteNo);
		this.background = background;
	}

	public MapTile getBackground() {
		return background;
	}

}
