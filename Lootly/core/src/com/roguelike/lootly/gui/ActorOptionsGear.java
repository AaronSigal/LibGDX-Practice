package com.roguelike.lootly.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class ActorOptionsGear extends Actor {
	
	ActorEscapeMenu pair; //The actor that gets called to the front of the GUI when this one is pressed
	
	//Textures
	private final Texture gear = new Texture("data/gui/gear.png");
	
	//Sprites
	private Sprite sprite = new Sprite(gear);
	
	//Constructor
	public ActorOptionsGear(final ActorEscapeMenu pair) {	
		this.pair = pair;
		
		//set the bounds of the sprite to enable hit detection
		setBounds(getSprite().getRegionX(), getSprite().getRegionY(), getSprite().getRegionWidth(), getSprite().getRegionHeight());
		
		//add a touch listener
		 addListener(new InputListener() {
			 @Override
			 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				 
				 pair.setVisible(true);
				 pair.setTouchable(Touchable.enabled);
				
				 System.out.println("Making menu visible");
				 return true;
			 }
		 });
	}
	
	public void spritePos(float x, float y){
		getSprite().setPosition(x, y);
		setBounds(getSprite().getX(), getSprite().getY(), getSprite().getWidth(), getSprite().getHeight());
	  }
	
	//method that draws the actor
	@Override
	public void draw (Batch batch, float parentAlpha) {
		batch.draw(getSprite(), getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
