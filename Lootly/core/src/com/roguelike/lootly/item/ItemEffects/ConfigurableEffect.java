package com.roguelike.lootly.item.ItemEffects;

import mob.MortalEntity;

public interface ConfigurableEffect {
	
	//Each consumable effect has four possible configuration options. Not every effect needs all four, but they are available. Unused arguments should always be set to zero. 
	public void configure(float arg0, float arg1, int arg2, int arg3);
	public void perform(MortalEntity targetEntity); //perform the effect

}
