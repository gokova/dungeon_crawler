package com.crawler.entity;

import com.crawler.util.Location;

public abstract class Area {

	private int x;
	private int y;
	private int width;
	private int height;

	public Area(int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getArea() {
		return width * height;
	}

	public double getEdgeRatio() {
		return (double) width / (double) height;
	}

	public Location getCenter() {
		int centerX = getX() + getWidth() / 2;
		int centerY = getY() + getHeight() / 2;
		return new Location(centerX, centerY);
	}

	public abstract void generateDungeon();

	public abstract void fillMap();

	public abstract ClosestPoint getClosestPoint(int destX, int destY);

	protected class ClosestPoint {

		private int distance;
		private Location position;

		public ClosestPoint(int distance, Location position) {
			this.distance = distance;
			this.position = position;
		}

		public int getDistance() {
			return distance;
		}

		public Location getPosition() {
			return position;
		}
	}

}
