package com.roguelike.lootly.io;

import com.roguelike.lootly.Lootly;

//Uses strategy design pattern. This object accepts and executes the code embedded within the SaveStrategy implementations. 
public class SaveContext {

	private SaveStrategy strategy;
	private Lootly game;
	
	public SaveContext(SaveStrategy strategy) {
		this.strategy = strategy;
	}
	
	//do the thing
	public void execute() {
		strategy.save(game);
	}

}
