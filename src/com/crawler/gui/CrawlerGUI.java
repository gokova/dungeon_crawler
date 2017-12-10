package com.crawler.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import com.crawler.entity.CurrentLevel;
import com.crawler.util.Settings;

public class CrawlerGUI implements PlayerActionListener {

	private JFrame frmCrawler;
	private MapPanel panelMap;
	private MinimapPanel panelMinimap;
	private ControllerPanel panelController;

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
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialise() {
		frmCrawler = new JFrame();
		frmCrawler.setResizable(false);
		frmCrawler.setTitle("Dungeon Crawler");
		frmCrawler.setBounds(50, 50, Settings.getInstance().getScreenWidth(), Settings.getInstance().getScreenHeight());
		frmCrawler.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrawler.getContentPane().setLayout(null);
		frmCrawler.getContentPane().setBackground(Color.CYAN);

		panelMap = new MapPanel();
		frmCrawler.getContentPane().add(panelMap);

		panelMinimap = new MinimapPanel();
		frmCrawler.getContentPane().add(panelMinimap);

		panelController = new ControllerPanel();
		panelController.addPlayerActionListener(this);
		frmCrawler.getContentPane().add(panelController);
	}

	@Override
	public void onActionTaken() {
		CurrentLevel.getInstance().gameLoop();
		panelMap.repaint();
		panelMinimap.repaint();
	}

}
