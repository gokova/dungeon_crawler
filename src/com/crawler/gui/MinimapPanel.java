package com.crawler.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.crawler.util.Position;
import com.crawler.util.Settings;

public class MinimapPanel extends JPanel {

	private Integer[][] map;
	private Position playerPosition;

	private static final int MINIMAP_WIDTH = 200;
	private static final int MINIMAP_HEIGHT = 200;
	private static final long serialVersionUID = 7531216945650442732L;

	public MinimapPanel(Integer[][] map, Position playerPosition) {
		this.map = map;
		this.playerPosition = playerPosition;
		setBounds(Settings.getInstance().getMapWidth(), 0, MINIMAP_WIDTH, MINIMAP_HEIGHT);
		setLayout(null);
	}

	public void setMap(Integer[][] map) {
		this.map = map;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graph = (Graphics2D) g;

		for (int i = 0; i < MINIMAP_WIDTH; i++) {
			for (int j = 0; j < MINIMAP_HEIGHT; j++) {
				if (playerPosition.getX() == i / 2 && playerPosition.getY() == j / 2) {
					graph.setPaint(Color.WHITE);
					graph.drawRect(i, j, 1, 1);
				} else if (map[i / 2][j / 2] < 6) {
					graph.setPaint(Color.RED);
					graph.drawRect(i, j, 1, 1);
				} else if (map[i / 2][j / 2] < 8) {
					graph.setPaint(Color.GREEN);
					graph.drawRect(i, j, 1, 1);
				} else {
					graph.setPaint(Color.BLUE);
					graph.drawRect(i, j, 1, 1);
				}
			}
		}
	}

}
