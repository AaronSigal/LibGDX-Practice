package com.roguelike.lootly.gui.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.roguelike.lootly.Classes;

public class ActorClassSphere extends Actor {
	
	/* TODO:
	 * Make the sprite stay inflated when clicked
	 */
	
		Classes playerClass = Classes.values()[0];
		
		//Textures
		final Texture orangeBall = new Texture("gui/ball_orange.png");
		final Texture greenBall = new Texture("gui/ball_green.png");
		final float originalScale = 3f; // the original scale that the sprite should be set to
		final float largeScale = 8f; //    the scale that the sprite should inflate to when hovered over
		
		boolean isClicked = false;
		
		//the container for the current texture
		Sprite sprite;
		
		
		// constructor
		public ActorClassSphere(final Classes playerClass) {
			
			if (playerClass != null) {
				this.playerClass = playerClass;
			} else {
				System.out.println("Class sphere was created with NULL as its class. Defaulting to " + this.playerClass.toString());
			}
			
			// texture/sprite for the actor
			sprite = new Sprite(greenBall);
			
			//base scale. Setting the scale in the constructor is fine since this element will likely not be re-used. 
			 this.setScale(originalScale);
			
			//sets the bounds of the actor to enable hit detection.
			 setBounds(sprite.getRegionX(), sprite.getRegionY(), sprite.getRegionWidth(), sprite.getRegionHeight());
			 
			 //handle hover and click events
			 addListener(new InputListener() {
				 @Override
				 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					 Gdx.app.log("Class selection: ", playerClass.toString());
					 
					 //fetch all the other actors in this actor's stage
					 Array<Actor> actors = getStage().getActors();
					 
					 //for each actor in the current actor's stage
					 for (Actor actor : actors) {
						 
						 //if the current actor being iterated over is an ActorClassSphere
						 if (actor instanceof ActorClassSphere) {
							 //System.out.println("Clearing other selections");
							 ((ActorClassSphere) actor).setClicked(false);
						 }
					 }
					 
					 
					 setClicked(true);
					 
					 return true;
				 }
				  
				  @Override
				  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
					  setScale((largeScale));
				  }
				  
				  @Override
				  public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
					  if (isClicked) {
						  setScale(largeScale);
					  } else {
						  setScale(originalScale);
					  }
					  
						 
					  
				  }
				});
			 
			 //make sure it is touchable
			 setTouchable(Touchable.enabled);
			
		}
		
		//change the actor to show an orange sprite
		public void setOrangeSprite() {
			sprite = new Sprite(orangeBall);
		}
		
		//change the actor to show a green sprite
		public void setGreenSprite() {
			sprite  = new Sprite(greenBall);
		}
		
		//method that draws the actor
		@Override
		public void draw (Batch batch, float parentAlpha) {
			Color color = getColor();
			if (isClicked()) {
				setScale(largeScale);
			}
			batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
		
		public void spritePos(float x, float y){
			sprite.setPosition(x, y);
			setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		}

		public Classes getPlayerClass() {
			return playerClass;
		}

		public void setPlayerClass(Classes playerClass) {
			this.playerClass = playerClass;
		}

		public Sprite getSprite() {
			return sprite;
		}

		public void setSprite(Sprite sprite) {
			this.sprite = sprite;
		}

		public boolean isClicked() {
			return isClicked;
		}

		//when the button is set to un-clicked, automatically change back to a green sprite at original scale. When clicked, automatically change to orange at expanded scale.
		public void setClicked(boolean isClicked) {
			if (isClicked) {
				this.isClicked = isClicked;
				setOrangeSprite();
				setScale(largeScale);
			} else {
				this.isClicked = isClicked;
				setGreenSprite();
				setScale(originalScale);
			}
		}
		
		
		
		
		
}
