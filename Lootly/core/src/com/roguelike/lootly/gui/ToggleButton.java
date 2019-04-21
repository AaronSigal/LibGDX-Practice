package com.roguelike.lootly.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

//Both an Actor and a Context for a MenuStrategy object via the Strategy Design Pattern
public class ToggleButton extends Actor{
	
	Texture currentTexture;
	Texture idleTexture; //Texture the object displays when left alone
	Texture hoverTexture; //The texture the object displays when the mouse is left above it
	
	//Do not use. Doesn't add any dummy textures and can cause NPEs!
	public ToggleButton() {
		
	}
	
	//Standard Constructor
	public ToggleButton(Texture idle, Texture hover) {
		idleTexture = idle;
		hoverTexture = hover;
		currentTexture = idleTexture; //Set the default texture to the idle texture
		
		addListener(new InputListener() {
			 @Override
			  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				   if (hoverTexture != null) {
					   currentTexture = hoverTexture;
				   } else {
					   System.out.println("Error! a Toggle Button doesn't have its textures loaded!");
				   }
			  }
			  
			@Override
			  public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				if (hoverTexture != null) {
					   currentTexture = idleTexture;
				   } else {
					   System.out.println("Error! a Toggle Button doesn't have its textures loaded!");
				   }
			  }
		
		});
	}
	@Override
	public void draw (Batch batch, float parentAlpha) {
		Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		batch.draw(new Sprite(currentTexture), getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	
	
	
}
