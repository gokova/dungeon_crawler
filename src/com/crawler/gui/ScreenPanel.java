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

		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				Integer tempVal = map[i + Math.max((playerPosition.getX() - 22), 0)][j
						+ Math.max((playerPosition.getY() - 22), 0)];
				if (tempVal < 1) {
					graph.setPaint(Color.RED);
					graph.fillRect(i * 32, j * 32, 32, 32);
				} else if (tempVal < 2) {
					graph.setPaint(Color.GREEN);
					graph.fillRect(i * 32, j * 32, 32, 32);
				} else {
					graph.setPaint(Color.BLUE);
					graph.fillRect(i * 32, j * 32, 32, 32);
				}

				if (Math.min(playerPosition.getX(), 22) == i && Math.min(playerPosition.getY(), 22) == j) {
					graph.setPaint(Color.BLACK);
					graph.fillOval((i * 32) + 6, (j * 32) + 6, 20, 20);
				}
			}
		}
	}

}
