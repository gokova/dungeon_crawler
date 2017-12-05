package com.crawler.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import com.crawler.entity.CurrentLevel;
import com.crawler.entity.Player;
import com.crawler.util.Settings;

public class CrawlerGUI {

	private JFrame frmCrawler;
	private MapPanel panelMap;
	private MinimapPanel panelMinimap;

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
		CurrentLevel.getInstance().generateNewLevel();
		initialise();

		Timer test = new Timer();
		test.schedule(new TimerTask() {
			@Override
			public void run() {
				Player player = CurrentLevel.getInstance().getPlayer();
				player.setLocation(player.getLocation().getX() + 1, player.getLocation().getY() + 1);
				panelMap.repaint();
				panelMinimap.repaint();
			}
		}, 1000, 500);
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
		frmCrawler.getContentPane().setBackground(Color.CYAN);

		panelMap = new MapPanel();
		frmCrawler.getContentPane().add(panelMap);

		panelMinimap = new MinimapPanel();
		frmCrawler.getContentPane().add(panelMinimap);
	}

}
