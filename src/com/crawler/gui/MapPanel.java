package com.crawler.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.crawler.util.Position;

public class MapPanel extends JPanel {

	private Integer[][] map;
	private Position playerPosition;

	private static final long serialVersionUID = 7531216945650442732L;

	public MapPanel(Integer[][] map, Position playerPosition) {
		this.map = map;
		this.playerPosition = playerPosition;
	}

	public void setMap(Integer[][] map) {
		this.map = map;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graph = (Graphics2D) g;

		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 200; j++) {
				if (playerPosition.getX() == i / 2 && playerPosition.getY() == j / 2) {
					graph.setPaint(Color.WHITE);
					graph.drawRect(i, j, 1, 1);
				} else if (map[i / 2][j / 2] < 1) {
					graph.setPaint(Color.RED);
					graph.drawRect(i, j, 1, 1);
				} else if (map[i / 2][j / 2] < 2) {
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
