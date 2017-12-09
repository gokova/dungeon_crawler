package com.crawler.command;

import com.crawler.entity.Character;

public class MoveDownCommand extends CharacterCommand {

	public MoveDownCommand(Character character) {
		super(character);
	}

	@Override
	public void execute() {
		character.moveDown();
	}

}
