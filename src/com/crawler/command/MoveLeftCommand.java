package com.crawler.command;

import com.crawler.entity.CurrentLevel;
import com.crawler.entity.Player;

public class MoveLeftCommand extends Command {

	Player player;

	public MoveLeftCommand(Player player) {
		super();
		this.player = player;
	}

	@Override
	public void execute() {
		int newX = player.getLocation().getX() - 1;
		int newY = player.getLocation().getY();

		if (CurrentLevel.getInstance().getMap().canMove(newX, newY)) {
			player.getLocation().setLocation(newX, newY);
		}
	}

}
