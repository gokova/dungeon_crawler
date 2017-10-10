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

	public int getScreenWidth() {
		return getMapWidth() + 206;
	}

	public int getScreenHeight() {
		return getMapHeight() + 29;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

}
