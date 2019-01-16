package com.roguelike.lootly.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.roguelike.lootly.Utils;


//A button that automatically loads a different sprite when pressed. Auto-centers text passed to it.
public class Button extends Actor {
	
	Sprite pressedSprite;
	Sprite idleSprite;
	Sprite currentSprite;
	String text;
	BitmapFont font = Utils.getFont(48);
	Float scale = 4f;
	
	public Button(String text, final Texture idleTexture, Texture pressedTexture) {
		idleSprite = new Sprite(idleTexture);
		pressedSprite = new Sprite(pressedTexture);
		currentSprite = idleSprite;
		this.text = text;		
		
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				 currentSprite = pressedSprite;
				return true;
			}
			
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				currentSprite = idleSprite; 
			}
		});
		 
		 //make sure it is touchable
		 setTouchable(Touchable.enabled);
	}
	
	//method that draws the actor
	@Override
	public void draw (Batch batch, float parentAlpha) {
		spritePos(getX(), getY());
		currentSprite.setScale(scale);
		currentSprite.draw(batch);
		font.draw(batch, text, getX() - getPositionOffset(font, text), getY() + getHeight()/2);
	}
	
	private float getPositionOffset(BitmapFont bitmapFont, String value) {
	    GlyphLayout glyphLayout = new GlyphLayout();
	    glyphLayout.setText(bitmapFont, value);
	    return (getWidth()/2) - glyphLayout.width/2;
	}
	
	public void spritePos(float x, float y){
		currentSprite.setPosition(x, y);
		setBounds(currentSprite.getX(), currentSprite.getY(), currentSprite.getWidth() * scale, currentSprite.getHeight() * scale);
	}

}
