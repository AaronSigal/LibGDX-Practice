package com.roguelike.lootly.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.roguelike.lootly.GameScreen;
import com.roguelike.lootly.Lootly;
import com.roguelike.lootly.character.CharacterClassManager;
import com.roguelike.lootly.character.Classes;

public class Projectile extends Entity {
	
    private Character player;
    private float speed = 7.0f;
    private int duration;
    
    public Projectile(GameScreen screen, Character player) {
    	this.screen = screen;
    	this.player = player;
    	duration = 50;
    	
		sprite = fetchSprite();
				
		//define body type and location
	    BodyDef bodyDef = defineBody(sprite, BodyDef.BodyType.KinematicBody);

	    //place body in world
	    body = screen.world.createBody(bodyDef);//make body in world
	    
	    //create shape
	    PolygonShape shape = new PolygonShape();//make poly
	    shape.setAsBox(sprite.getWidth()/2  / screen.PIXELS_TO_METERS * SCALE * 2f / 3f, 
	    			   sprite.getHeight()/2 / screen.PIXELS_TO_METERS * SCALE / 4f);//set poly dimension to sprite adjusted size
	    
	    //assign shape as FixtureDef
	    FixtureDef fixtureDef = defineFixture(shape, WorldColisionType.CATEGORY_PLAYER_PROJECTILE, WorldColisionType.MASK_PLAYER_PROJECTILE);
	    
	    //Fixture is assigned to body
	    body.createFixture(fixtureDef);
	    
	    //Calculate Player to Mouse vector
	    //world starts at bottom of screen and travels up
	    //mouse starts at middle of screen and travels down
	    //this complicates mouse to world conversions subsequently involving render height
		Sprite pSprite = player.getSprite();
	    Vector2 dir = new Vector2( (float)Gdx.input.getX() - (float)pSprite.getX(),
	    	Lootly.RENDER_HEIGHT - (float)Gdx.input.getY() - (float)pSprite.getY());
	    
	    //Calculate velocity vector
	    dir.nor().scl(speed);//Normalize Player to Mouse vector then scale it
	    
	    //set rotation
	    body.setTransform(body.getPosition().x,body.getPosition().y,dir.angleRad());
	    
	    //rotate body to direction of projection and add velocity in that direction
		body.setLinearVelocity(dir);
	    
	    //prevent body from rotating
	    body.setFixedRotation(true);
	    
	    shape.dispose();
    }
    @Override
    protected Sprite fetchSprite(){
		//get player sprite location
		Sprite pSprite = player.getSprite();
		
		//get player projectile sprite
		Sprite sprite = CharacterClassManager.getClassProjectileSprite(player.getClasses());

		//set projectile sprite location to player sprite location and scale
		sprite.setPosition(pSprite.getX(),pSprite.getY());
		sprite.setScale(SCALE);
		
		return sprite;
    }
    
    public boolean getDuration() {
    	duration--;
    	if(duration>0)
    		return true;
    	return false;
    }
}
