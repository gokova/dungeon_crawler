package com.crawler.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.crawler.entity.CurrentLevel;
import com.crawler.entity.Map;
import com.crawler.util.Position;
import com.crawler.util.Settings;

public class MinimapPanel extends JPanel {

	private Map map;
	private Position playerPosition;

	private static final int MINIMAP_WIDTH = 200;
	private static final int MINIMAP_HEIGHT = 200;
	private static final long serialVersionUID = 7531216945650442732L;

	public MinimapPanel() {
		this.map = CurrentLevel.getInstance().getMap();
		this.playerPosition = CurrentLevel.getInstance().getPlayer().getLocation();
		setBounds(Settings.getInstance().getMapWidth(), 0, MINIMAP_WIDTH + 2, MINIMAP_HEIGHT + 2);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setBackground(Color.GRAY);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graph = (Graphics2D) g;

		int minCenter = 16;
		int dungeonSize = CurrentLevel.getInstance().getDungeonSize();
		int mapSize = Settings.getInstance().getMapSize();
		int maxCenter = (dungeonSize - (mapSize - minCenter));
		int x = Math.max(Math.min(playerPosition.getX(), maxCenter), minCenter);
		int y = Math.max(Math.min(playerPosition.getY(), maxCenter), minCenter);

		for (int i = 0; i < MINIMAP_WIDTH; i++) {
			for (int j = 0; j < MINIMAP_HEIGHT; j++) {
				Integer tempVal = map.get(i / 2, j / 2);
				if (playerPosition.getX() == i / 2 && playerPosition.getY() == j / 2) {
					graph.setPaint(Color.WHITE);
				} else if ((i / 2.0f > x - 16 && i / 2.0f < x + 14) && (j / 2.0f == y - 16 || j / 2.0f == y + 14)
						|| (i / 2.0f == x - 16 || i / 2.0f == x + 14) && (j / 2.0f > y - 16 && j / 2.0f < y + 14)) {
					graph.setPaint(Color.WHITE);
				} else if (tempVal > 0 && tempVal <= 5) {
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
				graph.drawRect(i, j, 1, 1);
			}
		}
	}

}
