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
		
		//Textures
		final Texture orangeBall = new Texture("gui/ball_orange.png");
		final Texture greenBall = new Texture("gui/ball_green.png");
		
		//the container for the current texture
		Sprite sprite;
		
		static int clickX = 0, clickY = 0;
		
		// constructor
		public ActorClassSphere(final Classes playerClass) {
			// texture/sprite for the actor
			sprite = new Sprite(greenBall);
			
			//sets the bounds of the actor to enable hit detection.
			 setBounds(sprite.getRegionX(), sprite.getRegionY(), sprite.getRegionWidth(), sprite.getRegionHeight());
			 
			 //handle hover and click events
			 addListener(new InputListener() {
				 @Override
				 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					 Gdx.app.log("Class selection: ", playerClass.toString());
					 clickX = (int) getX();
					 clickY = (int) getY();
					 
					 Array<Actor> actors = getStage().getActors();
					 
					 //for each actor in the current actor's stage
					 for (Actor actor : actors) {
						 
						 //if the current actor being iterated over is an ActorClassSphere
						 if (actor instanceof ActorClassSphere) {
							 System.out.println("Clearing other selections");
							 ((ActorClassSphere) actor).setGreenSprite();
						 }
					 }
					 
					 return true;
				 }
				  
				  @Override
				  public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
					  setOrangeSprite();
				  }
				  
				  @Override
				  public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
					 
						  setGreenSprite();
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
			batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
			batch.draw(sprite, getX(), getY(), getOriginX(), getOriginY(),
				getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		}
		
		public void spritePos(float x, float y){
			sprite.setPosition(x, y);
			setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		  }
		
		
}
