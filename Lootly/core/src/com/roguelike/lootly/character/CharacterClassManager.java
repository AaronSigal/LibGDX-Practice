package com.roguelike.lootly.character;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

//For handling all the character-class specific functions that will likely need to be called in more than one class, and for more than one reason.
public class CharacterClassManager {
	
	//stores whether or not a class has been unlocked by the user. Is a hash map so that very many classes can be added to the game without slowing down the loading and saving of class unlock progress.
	static HashMap<Classes, Boolean> characterClassUnlock;
	
	//returns the sprite for a specific class. Will be expanded upon later as the design specs for character sprites are finalized.
	public static Sprite getClassSprite(Classes characterClass) {
		Sprite sprite = null;
		
		switch (characterClass) {
		
			case CRUSADER:
				sprite = new Sprite(new Texture("data/character/crusader/crusader_idle_down.png"));
			break;
			case ROGUE:
				sprite = new Sprite(new Texture("data/character/rogue/rogue_idle_down.png"));
				break;
			case MAGE:
				sprite = new Sprite(new Texture("data/character/mage/mage_idle_down.png"));
				break;
			default:
				sprite = new Sprite(new Texture("data/character/crusader/crusader_idle_down.png"));
				break;
		}
		
		return sprite;
	}
	
	public static void savePlayerProgress() {
		//TODO: Implement saving of character progress. Level, Items, stored items, etc
	}
	
	public static void saveCharacterProgress() {
		//TODO: Implement saving of classes. Design XML formatting.
	}
	
	public static void loadCharacterProgress() {
		//TODO: Implement loading of classes.
	}
	
}
