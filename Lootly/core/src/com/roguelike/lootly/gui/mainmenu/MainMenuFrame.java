package com.roguelike.lootly.gui.mainmenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MainMenuFrame extends Actor {
	//Textures
	final Texture menuFrame = new Texture("data/gui/main_menu_no_ball_alt.png");
		
	//the container for the current texture
	private Sprite sprite;
	
	// constructor
	public MainMenuFrame() {
		// texture/sprite for the actor
		setSprite(new Sprite(menuFrame));
		
		//sets the bounds of the actor to enable hit detection.
		setBounds(getSprite().getRegionX(), getSprite().getRegionY(), getSprite().getRegionWidth(), getSprite().getRegionHeight());	
	}
			
	//method that draws the actor
	@Override
	public void draw (Batch batch, float parentAlpha) {
		batch.draw(getSprite(), getX(), getY(), getOriginX(), getOriginY(),
			getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}
	
	public void spritePos(float x, float y){
		getSprite().setPosition(x, y);
		setBounds(getSprite().getX(), getSprite().getY(), getSprite().getWidth(), getSprite().getHeight());
	  }

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
}
