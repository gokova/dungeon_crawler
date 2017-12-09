package com.crawler.command;

import com.crawler.entity.Character;

public class MoveUpCommand extends CharacterCommand {

	public MoveUpCommand(Character character) {
		super(character);
	}

	@Override
	public void execute() {
		character.moveUp();
	}

}
