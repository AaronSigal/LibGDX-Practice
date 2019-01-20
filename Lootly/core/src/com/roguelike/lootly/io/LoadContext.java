package com.roguelike.lootly.io;

public class LoadContext {

	private LoadStrategy strategy;
	
	public LoadContext(LoadStrategy strategy) {
		this.strategy = strategy;
	}
	
	//do the thing
	public void execute() {
		strategy.load();
	}

}
