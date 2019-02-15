package com.roguelike.lootly.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorEscapeMenu extends Actor {
	//Textures
	final Texture escapeMenu = new Texture("data/gui/escape_menu_back.png");
		
	//the container for the current texture
	private Sprite sprite = new Sprite(escapeMenu);
	
	public ActorEscapeMenu() {
		//sets the bounds of the actor to enable hit detection.
		setBounds(getSprite().getRegionX(), getSprite().getRegionY(), getSprite().getRegionWidth(), getSprite().getRegionHeight());
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
