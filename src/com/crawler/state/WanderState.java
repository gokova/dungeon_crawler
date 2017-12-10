package com.crawler.state;

import java.util.Random;

import com.crawler.command.MoveDownCommand;
import com.crawler.command.MoveLeftCommand;
import com.crawler.command.MoveRightCommand;
import com.crawler.command.MoveUpCommand;
import com.crawler.entity.CurrentLevel;
import com.crawler.entity.Enemy;
import com.crawler.entity.Player;

public class WanderState extends State {

	private Enemy enemy;
	private Random rnd;
	private int changeDirectionCahnce;
	private int direction;

	public WanderState(Enemy enemy) {
		this.enemy = enemy;
		rnd = new Random();
		changeDirectionCahnce = 5;
		direction = rnd.nextInt(4);
	}

	@Override
	public void update() {
		if (rnd.nextInt(100) < changeDirectionCahnce) {
			direction = rnd.nextInt(4);
			changeDirectionCahnce = 5;
		} else {
			changeDirectionCahnce += 5;
		}
		if (direction == 0) {
			new MoveUpCommand(enemy).execute();
		} else if (direction == 1) {
			new MoveRightCommand(enemy).execute();
		} else if (direction == 2) {
			new MoveDownCommand(enemy).execute();
		} else if (direction == 3) {
			new MoveLeftCommand(enemy).execute();
		}

		Player player = CurrentLevel.getInstance().getPlayer();
		int distance = (int) (Math.pow(enemy.getLocation().getX() - player.getLocation().getX(), 2)
				+ Math.pow(enemy.getLocation().getY() - player.getLocation().getY(), 2));
		if (distance < Math.pow(enemy.getSenseDistance(), 2)) {
			enemy.setCurrentState(new AttackState(enemy, player));
		}
	}

}
