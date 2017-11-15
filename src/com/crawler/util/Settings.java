package com.crawler.util;

public class Settings {

	private static Settings instance;
	private int mapWidth;
	private int mapHeight;

	private Settings() {
		setMapWidth(960);
		setMapHeight(960);
	}

	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	private void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	private void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public int getScreenWidth() {
		return getMapWidth() + 208;
	}

	public int getScreenHeight() {
		return getMapHeight() + 29;
	}

	public int getMapSize() {
		return getMapWidth() / GlobalStatics.GRID_SIZE;
	}

}
