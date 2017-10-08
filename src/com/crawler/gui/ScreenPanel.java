package com.crawler.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.crawler.util.Position;

public class ScreenPanel extends JPanel {

	private Integer[][] map;
	private Position playerPosition;

	private static final long serialVersionUID = 3919306614878318497L;

	public ScreenPanel(Integer[][] map, Position playerPosition) {
		this.map = map;
		this.playerPosition = playerPosition;
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
				if (tempVal < 1) {
					graph.setPaint(Color.RED);
					graph.fillRect((i - x) * 32, (j - y) * 32, 32, 32);
				} else if (tempVal < 2) {
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
