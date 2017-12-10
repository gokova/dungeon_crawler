package com.crawler.entity;

import com.crawler.state.State;
import com.crawler.state.WanderState;
import com.crawler.util.Location;

public abstract class Enemy extends Character {

	private State currentState;
	private int senseDistance;

	public Enemy(Location location, int spriteNo) {
		super(location, spriteNo);
		this.currentState = new WanderState(this);
		this.senseDistance = 5;
	}

	public Enemy(Location location, int spriteNo, int senseDistance) {
		super(location, spriteNo);
		this.currentState = new WanderState(this);
		this.senseDistance = senseDistance;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public int getSenseDistance() {
		return senseDistance;
	}

	public void update() {
		currentState.update();
	}

}
