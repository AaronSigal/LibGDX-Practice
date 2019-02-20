package com.roguelike.lootly.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class EscapeMenu extends Group {
	//Textures
	final Texture escapeMenu = new Texture("data/gui/escape_menu_no_options.png");
	final Texture option1 = new Texture("data/gui/word/back.png");
	final Texture option2 = new Texture("data/gui/word/main_menu.png");
	final Texture option3 = new Texture("data/gui/");
	final Texture option4 = new Texture("data/gui/");
	VerticalGroup textContainer;
	Image background;
	
		
	//the container for the current texture
	private Sprite sprite = new Sprite(escapeMenu);
	
	public EscapeMenu() {
		//sets the bounds of the actor to enable hit detection.
		setBounds(getSprite().getRegionX(), getSprite().getRegionY(), getSprite().getRegionWidth(), getSprite().getRegionHeight());
		setSize(escapeMenu.getWidth(), escapeMenu.getHeight());
		textContainer = new VerticalGroup();
	}
	
	public void spritePos(float x, float y){
		getSprite().setPosition(x, y);
		setBounds(getSprite().getX(), getSprite().getY(), getSprite().getWidth(), getSprite().getHeight());
	}
	
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
