package com.crawler.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import com.crawler.entity.Player;
import com.crawler.util.Position;
import com.crawler.util.Settings;

public class CrawlerGUI {

	private JFrame frmCrawler;
	private MapPanel panelMap;
	private MinimapPanel panelMinimap;

	private Integer[][] map = new Integer[100][100];
	private Player player = new Player(new Position(10, 10));

	/**
	 * Launch application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrawlerGUI window = new CrawlerGUI();
					window.frmCrawler.setVisible(true);
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
				player.setLocation(player.getLocation().getX() + 1, player.getLocation().getY() + 1);
				panelMap.repaint();
				panelMinimap.repaint();
			}
		}, 1000, 1000);
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialise() {
		frmCrawler = new JFrame();
		frmCrawler.setResizable(false);
		frmCrawler.setTitle("Battleship");
		frmCrawler.setBounds(50, 50, Settings.getInstance().getScreenWidth(), Settings.getInstance().getScreenHeight());
		frmCrawler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrawler.getContentPane().setLayout(null);
		frmCrawler.getContentPane().setBackground(Color.BLACK);

		panelMap = new MapPanel(map, player.getLocation());
		frmCrawler.getContentPane().add(panelMap);

		panelMinimap = new MinimapPanel(map, player.getLocation());
		frmCrawler.getContentPane().add(panelMinimap);
	}

	/**
	 * Initialise map before it's drawn.
	 */
	private void createMap() {
		Random rnd = new Random();
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				map[i][j] = rnd.nextInt(9);
			}
		}
	}

}
