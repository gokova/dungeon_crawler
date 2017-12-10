package com.crawler.state;

import com.crawler.command.MoveDownCommand;
import com.crawler.command.MoveLeftCommand;
import com.crawler.command.MoveRightCommand;
import com.crawler.command.MoveUpCommand;
import com.crawler.entity.Character;
import com.crawler.entity.Enemy;

public class AttackState extends State {

	private Enemy enemy;
	private Character target;

	public AttackState(Enemy enemy, Character target) {
		this.enemy = enemy;
		this.target = target;
	}

	@Override
	public void update() {
		int xDirection = (int) Math.signum(target.getLocation().getX() - enemy.getLocation().getX());
		int yDirection = (int) Math.signum(target.getLocation().getY() - enemy.getLocation().getY());
		int xDistance = Math.abs(target.getLocation().getX() - enemy.getLocation().getX());
		int yDistance = Math.abs(target.getLocation().getY() - enemy.getLocation().getY());

		if (xDistance > yDistance) {
			if (xDirection > 0) {
				new MoveRightCommand(enemy).execute();
			} else {
				new MoveLeftCommand(enemy).execute();
			}
		} else {
			if (yDirection > 0) {
				new MoveDownCommand(enemy).execute();
			} else {
				new MoveUpCommand(enemy).execute();
			}
		}

		int distance = (int) (Math.pow(enemy.getLocation().getX() - target.getLocation().getX(), 2)
				+ Math.pow(enemy.getLocation().getY() - target.getLocation().getY(), 2));
		if (distance > Math.pow(enemy.getSenseDistance(), 2) * 1.5) {
			enemy.setCurrentState(new WanderState(enemy));
		}
	}

}
