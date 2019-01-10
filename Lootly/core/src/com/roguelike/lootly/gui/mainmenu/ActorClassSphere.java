package com.roguelike.lootly.gui.mainmenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorClassSphere extends Actor {
		final TextureRegion orangeBall = new TextureRegion();
		final TextureRegion greenBall = new TextureRegion();
		
		TextureRegion currentRegion;
		
		// constructor
		ActorClassSphere() {
			// texture/sprite for the actor
			currentRegion = new TextureRegion(/* URI for default color sprite */);
			
			//sets the bounds of the actor to enable hit detection.
			 setBounds(currentRegion.getRegionX(), currentRegion.getRegionY(), currentRegion.getRegionWidth(), currentRegion.getRegionHeight());
			
		}
		
		public void setOrangeSprite() {
			currentRegion = orangeBall; //TODO: add URI for orange ball
		}
		
		public void setGreenSprite() {
			currentRegion  = greenBall;
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
