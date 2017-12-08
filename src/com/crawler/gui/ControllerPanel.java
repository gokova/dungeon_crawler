package com.crawler.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.crawler.command.Command;
import com.crawler.command.MoveDownCommand;
import com.crawler.command.MoveLeftCommand;
import com.crawler.command.MoveRightCommand;
import com.crawler.command.MoveUpCommand;
import com.crawler.entity.CurrentLevel;
import com.crawler.entity.Player;
import com.crawler.util.Settings;

public class ControllerPanel extends JPanel {

	private Player player;
	private List<PlayerActionListener> listeners;

	private static final int PANEL_WIDTH = 200;
	private static final int PANEL_HEIGHT = 200;
	private static final long serialVersionUID = 3582617667492090491L;

	public ControllerPanel() {
		this.player = CurrentLevel.getInstance().getPlayer();
		this.listeners = new ArrayList<>();

		setBounds(Settings.getInstance().getMapWidth(), 202, PANEL_WIDTH + 2, PANEL_HEIGHT + 2);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setBackground(Color.GRAY);
		setLayout(null);

		CustomButton btnUp = new CustomButton(new MoveUpCommand(player), getImageFile("resources/images/up_arrow.png"));
		btnUp.setName("btnUp");
		btnUp.setToolTipText("Go up");
		btnUp.setBounds(37, 5, 32, 32);
		btnUp.setForeground(Color.GRAY);
		btnUp.setContentAreaFilled(false);
		btnUp.addActionListener(moveListener);
		this.add(btnUp);

		CustomButton btnDown = new CustomButton(new MoveDownCommand(player),
				getImageFile("resources/images/down_arrow.png"));
		btnDown.setName("btnDown");
		btnDown.setToolTipText("Go down");
		btnDown.setBounds(37, 69, 32, 32);
		btnDown.setForeground(Color.GRAY);
		btnDown.setContentAreaFilled(false);
		btnDown.addActionListener(moveListener);
		this.add(btnDown);

		CustomButton btnLeft = new CustomButton(new MoveLeftCommand(player),
				getImageFile("resources/images/left_arrow.png"));
		btnLeft.setName("btnLeft");
		btnLeft.setToolTipText("Go left");
		btnLeft.setBounds(5, 37, 32, 32);
		btnLeft.setForeground(Color.GRAY);
		btnLeft.setContentAreaFilled(false);
		btnLeft.addActionListener(moveListener);
		this.add(btnLeft);

		CustomButton btnRight = new CustomButton(new MoveRightCommand(player),
				getImageFile("resources/images/right_arrow.png"));
		btnRight.setName("btnRight");
		btnRight.setToolTipText("Go right");
		btnRight.setBounds(69, 37, 32, 32);
		btnRight.setForeground(Color.GRAY);
		btnRight.setContentAreaFilled(false);
		btnRight.addActionListener(moveListener);
		this.add(btnRight);
	}

	private ImageIcon getImageFile(String filePath) {
		return new ImageIcon(getClass().getClassLoader().getResource(filePath));
	}

	public void addPlayerActionListener(PlayerActionListener newListener) {
		listeners.add(newListener);
	}

	public void notifyListeners() {
		for (PlayerActionListener playerActionListener : listeners) {
			playerActionListener.onActionTaken();
		}
	}

	private ActionListener moveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			((CustomButton) e.getSource()).getCommand().execute();
			notifyListeners();
		}
	};

	private class CustomButton extends JButton {

		private Command command;

		private static final long serialVersionUID = -4070955898173116464L;

		public CustomButton(Command command, ImageIcon icon) {
			super(icon);
			this.command = command;
		}

		public Command getCommand() {
			return command;
		}
	}

}
