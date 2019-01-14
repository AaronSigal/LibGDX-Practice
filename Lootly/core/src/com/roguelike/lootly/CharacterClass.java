package com.roguelike.lootly;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CharacterClass {
	
	
	public static Sprite getClassSprite(Classes characterClass) {
		Sprite sprite = null;
		
		switch (characterClass) {
		
			case CRUSADER:
				sprite = new Sprite(new Texture("character/crusader/crusader_idle_down.png"));
			break;
			case ROGUE:
				sprite = new Sprite(new Texture("character/rogue/rogue_idle_down.png"));
			default:
				sprite = new Sprite(new Texture("character/crusader/crusader_idle_down.png"));
				break;
		
		}
		
		return sprite;
	}
	
}
