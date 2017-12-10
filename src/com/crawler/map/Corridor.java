package com.crawler.map;

import java.awt.Color;
import java.util.Random;

public class Corridor extends MapTile {

	private static final int SPRITE_NO = 76;

	public Corridor() {
		super(new Random().nextInt(2) + SPRITE_NO);
	}

	@Override
	public Color getMinimapColor() {
		return Color.BLACK;
	}

}
