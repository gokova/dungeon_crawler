package com.crawler.command;

import com.crawler.entity.CurrentLevel;
import com.crawler.entity.Player;

public class MoveUpCommand extends Command {

	Player player;

	public MoveUpCommand(Player player) {
		super();
		this.player = player;
	}

	@Override
	public void execute() {
		int newX = player.getLocation().getX();
		int newY = player.getLocation().getY() - 1;

		if (CurrentLevel.getInstance().getMap().canMove(newX, newY)) {
			player.getLocation().setLocation(newX, newY);
		}
	}

}
