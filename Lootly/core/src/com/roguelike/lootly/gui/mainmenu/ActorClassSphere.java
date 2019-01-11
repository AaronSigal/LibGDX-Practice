package com.roguelike.lootly.gui.mainmenu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorClassSphere extends Actor {
		
		//Textures
		final Texture orangeBall = new Texture("gui/ball_orange.png");
		final Texture greenBall = new Texture("gui/ball_green.png");
		
		//the container for the current texture
		TextureRegion currentRegion;
		
		// constructor
		ActorClassSphere() {
			// texture/sprite for the actor
			currentRegion = new TextureRegion(greenBall);
			
			//sets the bounds of the actor to enable hit detection.
			 setBounds(currentRegion.getRegionX(), currentRegion.getRegionY(), currentRegion.getRegionWidth(), currentRegion.getRegionHeight());
			
		}
		
		//change the actor to show an orange sprite
		public void setOrangeSprite() {
			currentRegion = new TextureRegion(orangeBall);
		}
		
		//change the actor to show a green sprite
		public void setGreenSprite() {
			currentRegion  = new TextureRegion(greenBall);
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
