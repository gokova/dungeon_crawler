package com.crawler.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import com.crawler.util.Position;

public class CrawlerGUI {

	private JFrame frmBattleship;
	private ScreenPanel panelScreen;
	private MapPanel panelMap;

	private Integer[][] map = new Integer[100][100];
	private Position currentPosition = new Position(10, 10);

	/**
	 * Launch application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrawlerGUI window = new CrawlerGUI();
					window.frmBattleship.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create application.
	 */
	public CrawlerGUI() {
		createMap();
		initialise();

		Timer test = new Timer();
		test.schedule(new TimerTask() {
			@Override
			public void run() {
				currentPosition.setLocation(currentPosition.getX() + 1, currentPosition.getY() + 1);
				panelScreen.repaint();
				panelMap.repaint();
			}
		}, 1000, 1000);
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialise() {
		frmBattleship = new JFrame();
		frmBattleship.setResizable(false);
		frmBattleship.setTitle("Battleship");
		frmBattleship.setBounds(50, 50, 1166, 989);
		frmBattleship.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattleship.getContentPane().setLayout(null);
		frmBattleship.getContentPane().setBackground(Color.BLACK);

		panelScreen = new ScreenPanel(map, currentPosition);
		panelScreen.setBounds(0, 0, 960, 960);
		panelScreen.setLayout(null);
		frmBattleship.getContentPane().add(panelScreen);

		panelMap = new MapPanel(map, currentPosition);
		panelMap.setBounds(960, 0, 200, 200);
		panelMap.setLayout(null);
		frmBattleship.getContentPane().add(panelMap);
	}

	/**
	 * Initialise map before it's drawn.
	 */
	private void createMap() {
		Random rnd = new Random();
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				map[i][j] = rnd.nextInt(3);
			}
		}
	}

}
