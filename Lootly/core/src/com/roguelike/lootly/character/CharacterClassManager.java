package com.roguelike.lootly.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

//For handling all the character-class specific functions that will likely need to be called in more than one class, and for more than one reason.
public class CharacterClassManager {
	
	static list<>;
	
	//returns the sprite for a specific class. Will be expanded upon later as the design specs for character sprites are finalized.
	public static Sprite getClassSprite(Classes characterClass) {
		Sprite sprite = null;
		
		switch (characterClass) {
		
			case CRUSADER:
				sprite = new Sprite(new Texture("character/crusader/crusader_idle_down.png"));
			break;
			case ROGUE:
				sprite = new Sprite(new Texture("character/rogue/rogue_idle_down.png"));
				break;
			case MAGE:
				sprite = new Sprite(new Texture("character/mage/mage_idle_down.png"));
				break;
			default:
				sprite = new Sprite(new Texture("character/crusader/crusader_idle_down.png"));
				break;
		}
		
		return sprite;
	}
	
	public static void 
	
}
