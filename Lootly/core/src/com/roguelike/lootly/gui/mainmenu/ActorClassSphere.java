package com.roguelike.lootly.gui.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.roguelike.lootly.CharacterClassManager;
import com.roguelike.lootly.Classes;
import com.roguelike.lootly.Utils;

public class ActorClassSphere extends Actor {
	
		//default character class.
		Classes playerClass = Classes.values()[0];
		
		//Textures
		final Texture blueBall = new Texture("gui/ball_blue.png");
		final Texture lightBlueBall = new Texture("gui/ball_light_blue.png");
		final Texture greyBall = new Texture("gui/ball_grey.png");
		final float originalScale = 3f; // the original scale that the sphereSprite should be set to
		final float largeScale = 8f; //    the scale that the sphereSprite should inflate to when hovered over
		
		private boolean clicked = false;
		private boolean enabled = true;
		private boolean hovered = false;
		
		
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
					 
					 if (enabled) {
						 Gdx.app.log("Class selection: ", playerClass.toString());
						 
						 //fetch all the other actors in this actor's stage
						 Array<Actor> actors = getStage().getActors();
						 
						 //for each actor in the current actor's stage
						 for (Actor actor : actors) {
							 
							 //if the current actor being iterated over is an ActorClassSphere
							 if (actor instanceof ActorClassSphere) {
								 if (((ActorClassSphere) actor).isEnabled())
								 //System.out.println("Clearing other selections");
								 ((ActorClassSphere) actor).setClicked(false);
							 }
						 }
						 
						 
						 setClicked(true); 
					 }
					 
					 
					 
					 return true;
				 }
				  
				  @Override
				  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
					  
					  hovered = true;
					  
					  if (enabled) {
						  setScale((largeScale));
						  setClassSpriteVisible(true);
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
							  setClassSpriteVisible(false);
						  } 
					  }
				  }
				});
			 
			 //make sure it is touchable
			 setTouchable(Touchable.enabled);
			
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
		
		private void setClassSpriteVisible(boolean b) {
			
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
			
			if (enabled && clicked) {
				font.draw(batch, playerClass.toString(), 
						getPositionOffset(font, playerClass.toString()), 
						Gdx.graphics.getHeight() - ((Gdx.graphics.getHeight()/10)));
			}
			
		}
		
		public void sphereSpritePos(float x, float y){
			sphereSprite.setPosition(x, y);
			setBounds(sphereSprite.getX(), sphereSprite.getY(), sphereSprite.getWidth(), sphereSprite.getHeight());
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

		//when the button is set to un-clicked, automatically change back to a green sphereSprite at original scale. When clicked, automatically change to orange at expanded scale.
		public void setClicked(boolean clicked) {
			if (enabled) {
				if (clicked) {
					this.clicked = clicked;
					setOrangesphereSprite();
					setScale(largeScale);
				} else {
					this.clicked = clicked;
					setGreensphereSprite();
					setScale(originalScale);
				}
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

		// display name of the class held in the sphere centered below the sphere when "isEnabled()" is true and either "isClicked()" is true of the mouse is hovering.
		public void displayClassName() {
			
		}
		
		private float getPositionOffset(BitmapFont bitmapFont, String value) {
		    GlyphLayout glyphLayout = new GlyphLayout();
		    glyphLayout.setText(bitmapFont, value);
		    return (Gdx.graphics.getWidth()/2) - glyphLayout.width/2;
		}
		
}
