package com.roguelike.lootly.gui.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.roguelike.lootly.Utils;
import com.roguelike.lootly.character.CharacterClassManager;
import com.roguelike.lootly.character.Classes;

public class ActorClassSphere extends Actor {
	
		//default character class.
		Classes playerClass = Classes.values()[0];
		
		//Textures
		final Texture blueBall = new Texture("data/gui/ball_blue.png");
		final Texture lightBlueBall = new Texture("data/gui/ball_light_blue.png");
		final Texture greyBall = new Texture("data/gui/ball_grey.png");
		final float originalScale = 3f; // the original scale that the sphereSprite should be set to
		final float largeScale = 8f; //    the scale that the sphereSprite should inflate to when hovered over
		
		private boolean clicked = false;
		private boolean enabled = true;
		private boolean hovered = false;
		private boolean fixed = false; //if true, the states of the sphere cannot be changed.
		
		
		Sprite sphereSprite = new Sprite(lightBlueBall); //the container for the current sphere texture
		Sprite classSprite; //the container for the character sprite of the class the sphere is set to
		
		BitmapFont font = Utils.getFont(36);
		
		
		// constructor
		public ActorClassSphere(final Classes playerClass) {
			
			if (playerClass != null) {
				this.playerClass = playerClass;
			} else {
				System.out.println("Class sphere was created with NULL as its class. Defaulting to " + this.playerClass.toString());
			}
			
			//fetch the sprite for the character that the sphere represents
			classSprite = CharacterClassManager.getClassSprite(playerClass);
			
			
			//base scale. Setting the scale in the constructor is fine since this element will likely not be re-used. 
			 this.setScale(originalScale);
			
			//sets the bounds of the actor to enable hit detection.
			 setBounds(sphereSprite.getRegionX(), sphereSprite.getRegionY(), sphereSprite.getRegionWidth(), sphereSprite.getRegionHeight());
			 
			 //handle hover and click events
			 addListener(new InputListener() {
				 @Override
				 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					 
					 if (enabled && !fixed) {
						 Gdx.app.log("Class selection: ", playerClass.toString());
						 
						 //fetch all the other actors in this actor's stage
						 Array<Actor> actors = getStage().getActors();
						 
						 //for each actor in the current actor's stage
						 for (Actor actor : actors) {
							 
							 //if the current actor being iterated over is an ActorClassSphere
							 if (actor instanceof ActorClassSphere) {
								 if (((ActorClassSphere) actor).isEnabled() && !fixed)
								 //System.out.println("Clearing other selections");
								 ((ActorClassSphere) actor).setClicked(false);
							 }
						 }
						 setClicked(true); 
						 // TODO: search all other actors for the left-side gui, set the correct text for "class"
					 }
					 return true;
				 }
				  
				  @Override
				  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
					  hovered = true;
					  
					  if (enabled && !fixed) {
						  setScale((largeScale));
					  } else {
						  setScale(originalScale);
					  }					 
				  }
				  
				@Override
				  public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
					hovered = false;
					  if (enabled) {
						  if (clicked) {
							  setScale(largeScale);
						  } else {
							  setScale(originalScale);
						  } 
					  }
				  }
				});
			 
			 //make sure it is touchable
			 setTouchable(Touchable.enabled);
			
		}
		
		//method that draws the actor
		@Override
		public void draw (Batch batch, float parentAlpha) {
			Color color = getColor();
			batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			batch.draw(sphereSprite, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
			
			//handle displaying class sprite and class name text
			if (enabled && ( clicked || hovered)) {
				batch.draw(classSprite, getX(), getY(), getOriginX(), getOriginY(),
						getWidth(), getHeight(), largeScale * 0.8f, largeScale * 0.8f, getRotation());
			}
		}
		
		//Moves the sprite of the sphere to (x,y) then updates the bound of the sphere actor to reflect the moved-sprite. This is necessary to make sure that the sphere is correctly clickable
		public void sphereSpritePos(float x, float y){
			sphereSprite.setPosition(x, y);
			setBounds(sphereSprite.getX(), sphereSprite.getY(), sphereSprite.getWidth(), sphereSprite.getHeight());
		}

		//when the button is set to un-clicked, automatically change back to a green sphereSprite at original scale. When clicked, automatically change to orange at expanded scale.
		public void setClicked(boolean clicked) {
			if (enabled) {
				if (clicked) {
					setOrangesphereSprite();
					setScale(largeScale);
				} else {
					setGreensphereSprite();
					setScale(originalScale);
				}
				
				this.clicked = clicked;
				
			} else {
				setGreysphereSprite();
			}
		}

		public boolean isEnabled() {
			return enabled;
		}

		//CLEAR
		public void setEnabled(boolean enabled) {
			if (enabled) {
				setGreensphereSprite();
				setTouchable(Touchable.enabled);
				this.enabled = enabled;
			} else {
				setGreysphereSprite();
				setTouchable(Touchable.disabled);
				this.enabled = enabled;
			}
		}
		
		//change the actor to show an orange sphereSprite
		public void setOrangesphereSprite() {
			sphereSprite = new Sprite(blueBall);
		}
		
		//change the actor to show a green sphereSprite
		public void setGreensphereSprite() {
			sphereSprite  = new Sprite(lightBlueBall);
		}
		
		public void setGreysphereSprite() {
			sphereSprite = new Sprite(greyBall);
		}
		
		public Sprite getClassSprite() {
			return classSprite;
		}

		public void setClassSprite(Sprite classSprite) {
			this.classSprite = classSprite;
		}

		public float getOriginalScale() {
			return originalScale;
		}

		public float getLargeScale() {
			return largeScale;
		}

		public boolean isFixed() {
			return fixed;
		}

		public void setFixed(boolean fixed) {
			this.fixed = fixed;
		}
		
		public Classes getPlayerClass() {
			return playerClass;
		}

		public void setPlayerClass(Classes playerClass) {
			this.playerClass = playerClass;
		}

		public Sprite getsphereSprite() {
			return sphereSprite;
		}

		public void setsphereSprite(Sprite sphereSprite) {
			this.sphereSprite = sphereSprite;
		}

		public boolean isClicked() {
			return clicked;
		}
}
