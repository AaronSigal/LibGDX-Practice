package com.roguelike.lootly.gui.mainmenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorMainMenuFrame extends Actor {
	//Textures
			final Texture menuFrame = new Texture("/assets/gui/main_menu_no_ball.png");
			
			//the container for the current texture
			TextureRegion currentRegion;
			
			// constructor
			ActorMainMenuFrame() {
				// texture/sprite for the actor
				currentRegion = new TextureRegion(menuFrame);
				
				//sets the bounds of the actor to enable hit detection.
				 setBounds(currentRegion.getRegionX(), currentRegion.getRegionY(), currentRegion.getRegionWidth(), currentRegion.getRegionHeight());	
			}
			
			//method that draws the actor
			@Override
			public void draw (Batch batch, float parentAlpha) {
				Color color = getColor();
				batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
				batch.draw(currentRegion, getX(), getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			}
}
