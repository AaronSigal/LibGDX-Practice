package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

// A collection of utility methods that will likely be used in multiple places throughout the code base. All inclusions into Utils should be generalized as much as possible to allow for maximum flexibility.
public class Utils {
	
	//Generates and returns an instance of the game font at the specified size.
	public static BitmapFont getFont(int size) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/FutilePro.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		BitmapFont font = generator.generateFont(parameter);
		
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
		
		return font;
	}
	
}