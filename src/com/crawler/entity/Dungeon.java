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
			// TODO connect children
		}
	}

	@Override
	public void fillMap() {
		for (Area area : getSubAreaList()) {
			area.fillMap();
		}
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

	@Override
	public Position getConnectionPoint() {
		return getSubAreaList().get(0).getConnectionPoint();
	}

}
