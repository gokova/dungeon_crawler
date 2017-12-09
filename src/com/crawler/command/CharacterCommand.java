package com.crawler.command;

import com.crawler.entity.Character;

public abstract class CharacterCommand implements Command {

	protected Character character;

	public CharacterCommand(Character character) {
		super();
		this.character = character;
	}

}
