package com.crawler.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.crawler.entity.CurrentLevel;
import com.crawler.entity.Map;
import com.crawler.util.GlobalStatics;
import com.crawler.util.Position;
import com.crawler.util.Settings;

public class MapPanel extends JPanel {

	private Map map;
	private Position playerPosition;
	private int dungeonSize = 0;
	private int mapSize = 0;

	private static final long serialVersionUID = 3919306614878318497L;

	public MapPanel() {
		this.map = CurrentLevel.getInstance().getMap();
		this.playerPosition = CurrentLevel.getInstance().getPlayer().getLocation();
		this.dungeonSize = CurrentLevel.getInstance().getDungeonSize();
		this.mapSize = Settings.getInstance().getMapSize();
		setBounds(0, 0, Settings.getInstance().getMapWidth(), Settings.getInstance().getMapHeight());
		setBackground(Color.GRAY);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D graph = (Graphics2D) g;

		/*
		 * To ensure that grids to be drawn is not out of bounds. If player is
		 * too close to right or bottom edge, Math.min finds center grid. If
		 * player is too close to left or top edge, Math.max finds center grid.
		 * Then minCenter is subtracted from it to find where to start drawing
		 * from.
		 */
		int minCenter = 16;
		int maxCenter = (dungeonSize - (mapSize - minCenter));
		int x = Math.max(Math.min(playerPosition.getX(), maxCenter), minCenter) - minCenter;
		int y = Math.max(Math.min(playerPosition.getY(), maxCenter), minCenter) - minCenter;

		for (int i = x; i < x + mapSize; i++) {
			for (int j = y; j < y + mapSize; j++) {
				Integer tempVal = map.get(i, j);
				if (tempVal > 0 && tempVal <= 5) {
					graph.setPaint(Color.RED);
				} else if (tempVal > 5 && tempVal <= 7) {
					graph.setPaint(Color.GREEN);
				} else if (tempVal > 7 && tempVal <= 8) {
					graph.setPaint(Color.BLUE);
				} else if (tempVal > 8) {
					graph.setPaint(Color.BLACK);
				} else {
					graph.setPaint(Color.GRAY);
				}
				graph.fillRect((i - x) * GlobalStatics.GRID_SIZE, (j - y) * GlobalStatics.GRID_SIZE,
						GlobalStatics.GRID_SIZE, GlobalStatics.GRID_SIZE);

				if (playerPosition.getX() == i && playerPosition.getY() == j) {
					graph.setPaint(Color.WHITE);
					graph.fillOval(((i - x) * GlobalStatics.GRID_SIZE) + 6, ((j - y) * GlobalStatics.GRID_SIZE) + 6, 20,
							20);
				}
			}
		}
	}

}
