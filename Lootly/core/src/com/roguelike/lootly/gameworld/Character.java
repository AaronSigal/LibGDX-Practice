package com.roguelike.lootly.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.roguelike.lootly.character.CharacterClassManager;
import com.roguelike.lootly.character.Classes;
import com.roguelike.lootly.GameScreen;
import com.roguelike.lootly.gameworld.WorldColisionType;

public class Character {
	private Sprite sprite;
	private GameScreen screen;
    private Body body;
    private Classes classes;
    private final float SCALE = 3f;
    
    public Character(GameScreen screen, Classes character) {
    	this.screen = screen;
    	this.classes = character;
    	makeCharacter();
    }
    private void makeCharacter() {
    	//fetch character sprite and scale it
	    sprite = CharacterClassManager.getClassSprite(classes);
	    sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
	            		   Gdx.graphics.getHeight() / 2);
	    sprite.setScale(SCALE);
	    
	    //define body type and location
	    BodyDef bodyDef = new BodyDef();//make body in world
	    bodyDef.type = BodyDef.BodyType.DynamicBody;//define body
	    bodyDef.position.set((sprite.getX() + sprite.getWidth() /2) / screen.PIXELS_TO_METERS, 
	    					 (sprite.getY() + sprite.getHeight()/2) / screen.PIXELS_TO_METERS);//set body position to match sprite adjusted position

	    //place body in world
	    body = screen.world.createBody(bodyDef);//make body in world
	    
	    //create shape
	    PolygonShape shape = new PolygonShape();//make poly
	    shape.setAsBox(sprite.getWidth()/2  / screen.PIXELS_TO_METERS * SCALE / 2, 
	    			   sprite.getHeight()/2 / screen.PIXELS_TO_METERS * SCALE);//set poly dimension to sprite adjusted size
	    
	    //assign shape as hit-box (FixtureDef)
	    //Note: more than one FixtureDef can be assigned to any body for more than one hitbox
	    FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = shape;//define shape of body
	    fixtureDef.density = 1f;//define weight of body
	    fixtureDef.filter.categoryBits = WorldColisionType.PLAYER_ENTITY.getType();//set collision group
	    fixtureDef.filter.maskBits = WorldColisionType.CREEP_PROJECTILE.getType();//set group to collide with
	    
	    //Fixture is assigned to body
	    body.createFixture(fixtureDef);
	    
	    //prevent body from rotating
	    body.setFixedRotation(true);
	    
	    shape.dispose();
    }
    public boolean getMotionInput(){
		float inpDirX = 0.0f;
		float inpDirY = 0.0f;
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D))
            inpDirX+=5f;
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A))
            inpDirX-=5f;
		if(Gdx.input.isKeyPressed(Input.Keys.UP)||Gdx.input.isKeyPressed(Input.Keys.W))
            inpDirY+=5f;
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.S))
            inpDirY-=5f;
		body.setLinearVelocity(inpDirX, inpDirY);
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
			return true;
		return false;
	}
    public void posSpriteToWorld() {
    	sprite.setPosition( (body.getPosition().x * screen.PIXELS_TO_METERS) - sprite.getWidth()/2 ,
    						(body.getPosition().y * screen.PIXELS_TO_METERS) -sprite.getHeight()/2 );//set sprite position to box postion
    	sprite.setRotation((float)Math.toDegrees(body.getAngle()));//set sprite rotation to box position
    }
    public boolean hit(Body body1, Body body2) {
    	if(body1 == body) {
        	//TODO: reduce health by amount listed on projectile
        	//and delete projectile (body at fixtureB)
    		System.out.println("player hit");
        	return true;
        }
        if(body2 == body) {
        	//TODO: reduce health by amount listed on projectile
        	//and delete projectile (body at fixtureA)
    		System.out.println("player hit");
        	return true;
        }
        return false;
    }
    public void setToEnemy() {//testing class which creates a new fixture of type enemy for the player to colide with
    	PolygonShape shape = new PolygonShape();
	    shape.setAsBox(sprite.getWidth()/2  / screen.PIXELS_TO_METERS * SCALE / 2, 
	    			   sprite.getHeight()/2 / screen.PIXELS_TO_METERS * SCALE);
    	FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = shape;
	    fixtureDef.density = 10f;
	    fixtureDef.filter.categoryBits = WorldColisionType.CREEP_ENTITY.getType();//set collision group
	    fixtureDef.filter.maskBits = WorldColisionType.PLAYER_PROJECTILE.getType();//set group to collide with
	    
    	body.createFixture(fixtureDef);
    }
    public Sprite getSprite() {
    	return sprite;
    }
    public Classes getClasses() {
    	return classes;
    }
    public Body getBody() {
    	return body;
    }
}
