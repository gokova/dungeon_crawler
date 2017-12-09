package com.crawler.command;

import com.crawler.entity.Character;

public class MoveLeftCommand extends CharacterCommand {

	public MoveLeftCommand(Character character) {
		super(character);
	}

	@Override
	public void execute() {
		character.moveLeft();
	}

}
