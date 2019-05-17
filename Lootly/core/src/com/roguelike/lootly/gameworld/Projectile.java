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

public class Projectile {
	private Sprite sprite;
	private GameScreen screen;
    private Body body;
    private Character player;
    
    final float SCALE = 3f;
    
    private int duration;
    
    public Projectile(GameScreen screen, Character player) {
    	this.screen = screen;
    	this.player = player;
    	makeProjectile();
    	duration = 50;
    }
    
    private void makeProjectile(){
		sprite = new Sprite(new Texture("character/rogue/rogue_projectile.png"));
		
		Sprite pSprite = player.getSprite();
		
		sprite.setPosition(pSprite.getX(),pSprite.getY());
		sprite.setScale(SCALE);
		
		//define body type and location
	    BodyDef bodyDef = new BodyDef();//make body in world
	    bodyDef.type = BodyDef.BodyType.KinematicBody;//define body
	    bodyDef.position.set((sprite.getX() + sprite.getWidth() /2) / screen.PIXELS_TO_METERS, 
	    					 (sprite.getY() + sprite.getHeight()/2) / screen.PIXELS_TO_METERS);//set body position to match sprite adjusted position

	    //place body in world
	    body = screen.world.createBody(bodyDef);//make body in world
	    
	    //create shape
	    PolygonShape shape = new PolygonShape();//make poly
	    shape.setAsBox(sprite.getWidth()/2  / screen.PIXELS_TO_METERS * SCALE * 2f / 3f, 
	    			   sprite.getHeight()/2 / screen.PIXELS_TO_METERS * SCALE / 4f);//set poly dimension to sprite adjusted size
	    
	    //assign shape as hit-box (FixtureDef)
	    //Note: more than one FixtureDef can be assigned to any body for more than one hitbox
	    FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = shape;//define shape of body
	    //fixtureDef.density = .1f;//define weight of body
	    fixtureDef.filter.categoryBits = WorldColisionType.PLAYER_PROJECTILE.getType();//set collision group
	    fixtureDef.filter.maskBits = WorldColisionType.CREEP_ENTITY.getType();//set group to collide with
	    
	    //Fixture is assigned to body
	    body.createFixture(fixtureDef);
	    
	    //projectile direction
	    //world starts at bottom of screen and travels up
	    //mouse in starts at middle of screen and travels down
	    //this complicates mouse to world conversions subsequently involving render height
	    Vector2 dir = new Vector2( (float)Gdx.input.getX() - (float)pSprite.getX(),
	    	Lootly.RENDER_HEIGHT - (float)Gdx.input.getY() - (float)pSprite.getY());
	    
	    dir.nor().scl(7.0f);//Projectile velocity: input vector is normalized then scaled
	    
	    //body.setTransform(sprite.getX()/screen.PIXELS_TO_METERS,sprite.getY()/screen.PIXELS_TO_METERS,dir.angleRad());//set rotation
	    body.setTransform(body.getPosition().x,body.getPosition().y,dir.angleRad());//set rotation
	    
	    //rotate body to direction of projection and add velocity in that direction
		body.setLinearVelocity(dir);
	    
	    //prevent body from rotating
	    body.setFixedRotation(true);
	    
	    shape.dispose();
    }

    public void posSpriteToWorld() {
    	sprite.setPosition( (body.getPosition().x * screen.PIXELS_TO_METERS) - sprite.getWidth()/2 ,
    						(body.getPosition().y * screen.PIXELS_TO_METERS) -sprite.getHeight()/2 );//set sprite position to box postion
    	sprite.setRotation((float)Math.toDegrees(body.getAngle()));//set sprite rotation to box position
    }
    
    public boolean getDuration() {
    	duration--;
    	if(duration>0)
    		return true;
    	return false;
    }
    
    public Sprite getSprite() {
    	return sprite;
    }
}
