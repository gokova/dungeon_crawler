package com.crawler.command;

import com.crawler.entity.Character;

public class MoveRightCommand extends CharacterCommand {

	public MoveRightCommand(Character character) {
		super(character);
	}

	@Override
	public void execute() {
		character.moveRight();
	}

}
