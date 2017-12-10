package com.crawler.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.crawler.util.GlobalStatics;
import com.crawler.util.Location;

public class Dungeon extends Area {

	private List<Area> subAreaList;

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
			Location center = getSubAreaList().get(i + 1).getCenter();
			ClosestPoint firstPoint = getSubAreaList().get(i).getClosestPoint(center.getX(), center.getY());
			ClosestPoint secondPoint = getSubAreaList().get(i + 1).getClosestPoint(firstPoint.getPosition().getX(),
					firstPoint.getPosition().getY());
			connect(firstPoint.getPosition(), secondPoint.getPosition());
		}
	}

	@Override
	public void fillMap() {
		for (Area area : getSubAreaList()) {
			area.fillMap();
		}
	}

	@Override
	public ClosestPoint getClosestPoint(int destX, int destY) {
		ClosestPoint minDistance = new ClosestPoint(Integer.MAX_VALUE, new Location());

		for (Area area : subAreaList) {
			ClosestPoint tempDist = area.getClosestPoint(destX, destY);
			if (tempDist.getDistance() < minDistance.getDistance()) {
				minDistance = tempDist;
			}
		}

		return minDistance;
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

	public void connect(Location firstCenter, Location secondCenter) {
		int xAxisDirection = (int) Math.signum(secondCenter.getX() - firstCenter.getX());
		int yAxisDirection = (int) Math.signum(secondCenter.getY() - firstCenter.getY());
		int xAxisDistance = Math.abs(secondCenter.getX() - firstCenter.getX());
		int yAxisDistance = Math.abs(secondCenter.getY() - firstCenter.getY());
		if (xAxisDistance <= 2) {
			firstCenter.setLocation(secondCenter.getX(), firstCenter.getY());
		}
		if (yAxisDistance <= 2) {
			firstCenter.setLocation(firstCenter.getX(), secondCenter.getY());
		}

		Map map = CurrentLevel.getInstance().getMap();
		boolean isHorizontal = xAxisDistance >= yAxisDistance;
		int i = 0;
		int j = 0;

		if (isHorizontal) {
			j = firstCenter.getY();
			for (i = firstCenter.getX(); i != secondCenter.getX(); i += (1 * xAxisDirection)) {
				map.put(i, j, new Corridor());
				if (Math.abs(i - firstCenter.getX()) == (Math.abs(firstCenter.getX() - secondCenter.getX()) / 2)) {
					for (int k = firstCenter.getY(); k != secondCenter.getY(); k += (1 * yAxisDirection)) {
						j = k;
						map.put(i, j, new Corridor());
					}
				}
			}
		} else {
			j = firstCenter.getX();
			for (i = firstCenter.getY(); i != secondCenter.getY(); i += (1 * yAxisDirection)) {
				map.put(j, i, new Corridor());
				if (Math.abs(i - firstCenter.getY()) == (Math.abs(firstCenter.getY() - secondCenter.getY()) / 2)) {
					for (int k = firstCenter.getX(); k != secondCenter.getX(); k += (1 * xAxisDirection)) {
						j = k;
						map.put(j, i, new Corridor());
					}
				}
			}
		}
	}

}
