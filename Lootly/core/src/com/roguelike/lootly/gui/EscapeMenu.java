package com.roguelike.lootly.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class EscapeMenu extends Group {	
	VerticalGroup optionContainer;
	Image background = new Image(new Texture("data/gui/escape_menu_no_options.png"));
	
	//the container for the current texture
	private Sprite sprite = new Sprite(new Texture("data/gui/escape_menu_no_options.png"));
	
	public EscapeMenu() {
		//sets the bounds of the actor to enable hit detection.
		setBounds(getSprite().getRegionX(), getSprite().getRegionY(), getSprite().getRegionWidth(), getSprite().getRegionHeight());
		setSize(sprite.getWidth(), sprite.getHeight());
		optionContainer = new VerticalGroup();
		
		optionContainer.addActor(new ToggleButton(new Texture("data/gui/word/back.png"), new Texture("data/gui/word/back.png"), new MenuStrategy() {

			@Override
			public void execute() {
				setTouchable(Touchable.disabled);
				setVisible(false);
				System.out.println("making menu invisible");
			}
			
		}));
		
		addActor(background);
		addActor(optionContainer);
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
