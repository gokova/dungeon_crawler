package com.crawler.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.crawler.entity.Corridor;
import com.crawler.entity.CurrentLevel;
import com.crawler.entity.Map;
import com.crawler.entity.MapTile;
import com.crawler.entity.RoomFloor;
import com.crawler.util.Location;
import com.crawler.util.Settings;

public class MinimapPanel extends JPanel {

	private static final int MINIMAP_WIDTH = 200;
	private static final int MINIMAP_HEIGHT = 200;
	private static final long serialVersionUID = 7531216945650442732L;

	public MinimapPanel() {
		setBounds(Settings.getInstance().getMapWidth(), 0, MINIMAP_WIDTH + 2, MINIMAP_HEIGHT + 2);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setBackground(Color.GRAY);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graph = (Graphics2D) g;
		Color room = new Color(0.4f, 0.2f, 0f);
		Color empty = new Color(0.56f, 0.41f, 0.23f);

		Map map = CurrentLevel.getInstance().getMap();
		Location playerPosition = CurrentLevel.getInstance().getPlayer().getLocation();
		int dungeonSize = CurrentLevel.getInstance().getDungeonSize();
		int mapSize = Settings.getInstance().getMapSize();

		int minCenter = 16;
		int maxCenter = (dungeonSize - (mapSize - minCenter));
		int x = Math.max(Math.min(playerPosition.getX(), maxCenter), minCenter);
		int y = Math.max(Math.min(playerPosition.getY(), maxCenter), minCenter);

		for (int i = 0; i < MINIMAP_WIDTH; i++) {
			for (int j = 0; j < MINIMAP_HEIGHT; j++) {
				MapTile tile = map.get(i / 2, j / 2);
				if (playerPosition.getX() == i / 2 && playerPosition.getY() == j / 2) {
					graph.setPaint(Color.WHITE);
				} else if ((i / 2.0f > x - 16 && i / 2.0f < x + 14) && (j / 2.0f == y - 16 || j / 2.0f == y + 14)
						|| (i / 2.0f == x - 16 || i / 2.0f == x + 14) && (j / 2.0f > y - 16 && j / 2.0f < y + 14)) {
					// Boundary of visible map
					graph.setPaint(Color.WHITE);
				} else if (tile instanceof RoomFloor) {
					graph.setPaint(room);
				} else if (tile instanceof Corridor) {
					graph.setPaint(Color.BLACK);
				} else {
					graph.setPaint(empty);
				}
				graph.drawRect(i, j, 1, 1);
			}
		}
	}

}
