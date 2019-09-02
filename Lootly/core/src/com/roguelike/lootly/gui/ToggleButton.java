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
	
	Sprite currentSprite;
	Sprite idleSprite; //Texture the object displays when left alone
	Sprite hoverSprite; //The texture the object displays when the mouse is left above it
	MenuStrategy action; //The object that holds the functionality destined for the button to carry out when pressed
	
	//Do not use. Doesn't add any dummy textures and can cause NPEs!
	public ToggleButton() {
		
	}
	
	//Standard Constructor
	public ToggleButton(Texture idle, Texture hover, final MenuStrategy action) {
		idleSprite = new Sprite(idle);
		hoverSprite = new Sprite(hover);
		currentSprite = new Sprite(idleSprite); //Set the default texture to the idle texture
		this.action = action;
		
		addListener(new InputListener() {
			 @Override
			 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				 
				 if (action != null) {
					 action.execute();
				 } else {
					 System.out.println("Warning! a Toggle Button doesn't have an action to execute!");
				 }
				 
				 
				 return true;
			 }
			
			 @Override
			  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				 
				 setBounds(currentSprite.getRegionX(), currentSprite.getRegionY(), currentSprite.getRegionWidth(), currentSprite.getRegionHeight());
				 
				   if (hoverSprite != null) {
					   currentSprite = hoverSprite;
				   } else {
					   System.out.println("Error! a Toggle Button doesn't have its textures loaded!");
				   }
			  }
			  
			@Override
			  public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				if (idleSprite != null) {
					   currentSprite = idleSprite;
				   } else {
					   System.out.println("Error! a Toggle Button doesn't have its textures loaded!");
				   }
			  }
		
		});
		
	}
	
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
		
		batch.draw(currentSprite, getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	
	
	
}
