package com.roguelike.lootly.io;

import com.roguelike.lootly.Lootly;

public interface SaveStrategy {
	
	/*Performs the saving. So ideally there would be different SaveStrategy implementations 
	 *for each type of thing that needs to be saved such as class progress, challenge progress, etc
	 */
	public void save(Lootly game);

}
