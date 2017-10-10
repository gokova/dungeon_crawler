package com.crawler.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.crawler.util.Position;
import com.crawler.util.Settings;

public class MapPanel extends JPanel {

	private Integer[][] map;
	private Position playerPosition;

	private static final long serialVersionUID = 3919306614878318497L;

	public MapPanel(Integer[][] map, Position playerPosition) {
		this.map = map;
		this.playerPosition = playerPosition;
		setBounds(0, 0, Settings.getInstance().getMapWidth(), Settings.getInstance().getMapHeight());
		setLayout(null);
	}

	public void setMap(Integer[][] map) {
		this.map = map;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D graph = (Graphics2D) g;

		int x = Math.max(Math.min(playerPosition.getX(), 84), 16) - 16;
		int y = Math.max(Math.min(playerPosition.getY(), 84), 16) - 16;

		for (int i = x; i < x + 30; i++) {
			for (int j = y; j < y + 30; j++) {
				Integer tempVal = map[i][j];
				if (tempVal < 6) {
					graph.setPaint(Color.RED);
					graph.fillRect((i - x) * 32, (j - y) * 32, 32, 32);
				} else if (tempVal < 8) {
					graph.setPaint(Color.GREEN);
					graph.fillRect((i - x) * 32, (j - y) * 32, 32, 32);
				} else {
					graph.setPaint(Color.BLUE);
					graph.fillRect((i - x) * 32, (j - y) * 32, 32, 32);
				}

				if (playerPosition.getX() == i && playerPosition.getY() == j) {
					graph.setPaint(Color.BLACK);
					graph.fillOval(((i - x) * 32) + 6, ((j - y) * 32) + 6, 20, 20);
				}
			}
		}
	}

}
