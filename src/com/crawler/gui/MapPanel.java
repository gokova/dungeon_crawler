package com.crawler.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.crawler.entity.CurrentLevel;
import com.crawler.entity.EmptyTile;
import com.crawler.entity.Map;
import com.crawler.entity.MapTile;
import com.crawler.entity.Player;
import com.crawler.util.GlobalStatics;
import com.crawler.util.Settings;

public class MapPanel extends JPanel {

	private static final long serialVersionUID = 3919306614878318497L;

	public MapPanel() {
		setBounds(0, 0, Settings.getInstance().getMapWidth(), Settings.getInstance().getMapHeight());
		setBackground(Color.GRAY);
		setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D graph = (Graphics2D) g;

		Map map = CurrentLevel.getInstance().getMap();
		Player player = CurrentLevel.getInstance().getPlayer();
		int dungeonSize = CurrentLevel.getInstance().getDungeonSize();
		int mapSize = Settings.getInstance().getMapSize();

		/*
		 * To ensure that grids to be drawn is not out of bounds. If player is
		 * too close to right or bottom edge, Math.min finds center grid. If
		 * player is too close to left or top edge, Math.max finds center grid.
		 * Then minCenter is subtracted from it to find where to start drawing
		 * from.
		 */
		int minCenter = 16;
		int maxCenter = (dungeonSize - (mapSize - minCenter));
		int x = Math.max(Math.min(player.getLocation().getX(), maxCenter), minCenter) - minCenter;
		int y = Math.max(Math.min(player.getLocation().getY(), maxCenter), minCenter) - minCenter;

		for (int i = x; i < x + mapSize; i++) {
			for (int j = y; j < y + mapSize; j++) {
				MapTile tile = map.get(i, j);
				int dx1 = (i - x) * GlobalStatics.GRID_SIZE;
				int dy1 = (j - y) * GlobalStatics.GRID_SIZE;

				if (tile == null) {
					tile = new EmptyTile();
					map.put(i, j, tile);
				}
				graph.drawImage(tile.getImage(), dx1, dy1, null);

				if (player.getLocation().getX() == i && player.getLocation().getY() == j) {
					graph.drawImage(player.getImage(), dx1, dy1, null);
				}
			}
		}
	}

}
