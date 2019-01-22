package com.roguelike.lootly.item;

public interface ConfigurableEffect {
	
	//Each consumable effect has four possible configuration options. Not every effect needs all four, but they are available. Unused arguments should always be set to zero. 
	public void execute(float args0, float args1, int args2, int args3);

}
