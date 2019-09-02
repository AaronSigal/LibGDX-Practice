package com.roguelike.lootly.gameworld;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.roguelike.lootly.GameScreen;
import com.roguelike.lootly.character.Classes;

public class Walls extends Entity{
	
	
	
    public Walls(GameScreen screen, Classes character) {
    	//grab a sprite
    	sprite = fetchSprite();
    }
    
    @Override
    protected Sprite fetchSprite() {
    	return sprite;
    }
}
