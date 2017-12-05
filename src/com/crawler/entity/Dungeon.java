package com.crawler.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.crawler.util.GlobalStatics;
import com.crawler.util.Position;

public class Dungeon extends Area {

	private List<Area> subAreaList;

	public Dungeon() {
		super(0, 0, 0, 0);
	}

	public Dungeon(int x, int y, int width, int height) {
		super(x, y, width, height);
		subAreaList = new ArrayList<>();
	}

	public List<Area> getSubAreaList() {
		return subAreaList;
	}

	public void addSubArea(Area newArea) {
		subAreaList.add(newArea);
	}

	@Override
	public void generateDungeon() {
		Area leftPart = null;
		Area rightPart = null;

		do {
			Random rnd = new Random();
			boolean horizontal = rnd.nextBoolean();
			int split = rnd.nextInt(horizontal ? getHeight() : getWidth());

			if (horizontal) {
				leftPart = createNewArea(getX(), getY(), getWidth(), split);
				rightPart = createNewArea(getX(), getY() + split, getWidth(), getHeight() - split);
			} else {
				leftPart = createNewArea(getX(), getY(), split, getHeight());
				rightPart = createNewArea(getX() + split, getY(), getWidth() - split, getHeight());
			}
		} while (leftPart.getArea() < GlobalStatics.MIN_AREA || rightPart.getArea() < GlobalStatics.MIN_AREA
				|| leftPart.getEdgeRatio() < (double) 0.3 || rightPart.getEdgeRatio() < (double) 0.3
				|| leftPart.getEdgeRatio() > (double) 1.7 || rightPart.getEdgeRatio() > (double) 1.7);

		this.addSubArea(leftPart);
		this.addSubArea(rightPart);

		for (Area area : getSubAreaList()) {
			area.generateDungeon();
		}

		for (int i = 0; i < getSubAreaList().size() - 1; i++) {
			Position firstCenter = getSubAreaList().get(i).getConnectionPoint();
			Position secondCenter = getSubAreaList().get(i + 1).getConnectionPoint();
			connect(firstCenter, secondCenter);
		}
	}

	@Override
	public void fillMap() {
		for (Area area : getSubAreaList()) {
			area.fillMap();
		}
	}

	@Override
	public Position getConnectionPoint() {
		return getSubAreaList().get(0).getConnectionPoint();
	}

	private Area createNewArea(int x, int y, int width, int height) {
		Area result;

		if (width * height > GlobalStatics.MAX_ROOM_AREA) {
			result = new Dungeon(x, y, width, height);
		} else {
			result = new Room(x, y, width, height);
		}

		return result;
	}

	public void connect(Position firstCenter, Position secondCenter) {
		int xAxisDistance = Math.abs(secondCenter.getX() - firstCenter.getX());
		int yAxisDistance = Math.abs(secondCenter.getY() - firstCenter.getY());
		if (xAxisDistance <= 2) {
			firstCenter.setLocation(secondCenter.getX(), firstCenter.getY());
		}
		if (yAxisDistance <= 2) {
			firstCenter.setLocation(firstCenter.getX(), secondCenter.getY());
		}

		int[][] map = CurrentLevel.getInstance().getMap();
		boolean isHorizontal = xAxisDistance >= yAxisDistance;
		int xAxisDirection = (int) Math.signum(secondCenter.getX() - firstCenter.getX());
		int yAxisDirection = (int) Math.signum(secondCenter.getY() - firstCenter.getY());
		int i = 0;
		int j = 0;

		if (isHorizontal) {
			for (i = 0; (firstCenter.getX() + i) != secondCenter.getX(); i += (1 * xAxisDirection)) {
				map[firstCenter.getY() + j][firstCenter.getX() + i] = 9;
				if (Math.abs(i) == (Math.abs(firstCenter.getX() - secondCenter.getX()) / 2)) {
					for (int k = 0; (firstCenter.getY() + k) != secondCenter.getY(); k += (1 * yAxisDirection)) {
						j = k;
						map[firstCenter.getY() + j][firstCenter.getX() + i] = 9;
					}
				}
			}
		} else {
			for (i = 0; (firstCenter.getY() + i) != secondCenter.getY(); i += (1 * yAxisDirection)) {
				map[firstCenter.getY() + i][firstCenter.getX() + j] = 9;
				if (Math.abs(i) == (Math.abs(firstCenter.getY() - secondCenter.getY()) / 2)) {
					for (int k = 0; (firstCenter.getX() + k) != secondCenter.getX(); k += (1 * xAxisDirection)) {
						j = k;
						map[firstCenter.getY() + i][firstCenter.getX() + j] = 9;
					}
				}
			}
		}
	}

}
