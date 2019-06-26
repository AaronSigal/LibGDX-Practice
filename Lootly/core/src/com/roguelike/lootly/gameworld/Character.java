package com.roguelike.lootly.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.roguelike.lootly.character.CharacterClassManager;
import com.roguelike.lootly.character.Classes;
import com.roguelike.lootly.GameScreen;
import com.roguelike.lootly.gameworld.WorldColisionType;

public class Character extends Entity{
    
	private Classes classes;
	
    public Character(GameScreen screen, Classes character) {
    	this.screen = screen;
    	this.classes = character;
    	//fetch character sprite and scale it
	    sprite = fetchSprite();
	    
	    //define body type and location
	    BodyDef bodyDef = defineBody(sprite, BodyDef.BodyType.DynamicBody);

	    //place body in world
	    body = screen.world.createBody(bodyDef);//make body in world
	    
	    //create shape
	    PolygonShape shape = new PolygonShape();//make poly
	    shape.setAsBox(sprite.getWidth()/2  / screen.PIXELS_TO_METERS * SCALE / 2, 
	    			   sprite.getHeight()/2 / screen.PIXELS_TO_METERS * SCALE);//set poly dimension to sprite adjusted size
	    
	    //assign shape as hit-box (FixtureDef)
	    //Note: more than one FixtureDef can be assigned to any body for more than one hitbox
	    FixtureDef fixtureDef = defineFixture(shape, WorldColisionType.CATEGORY_PLAYER_ENTITY, WorldColisionType.MASK_PLAYER_ENTITY);
	    
	    //Fixture is assigned to body
	    body.createFixture(fixtureDef);
	    
	    //prevent body from rotating
	    body.setFixedRotation(true);
	    
	    shape.dispose();
    }
    @Override
    protected Sprite fetchSprite(){
    	Sprite sprite = CharacterClassManager.getClassSprite(classes);
	    sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
	            		   Gdx.graphics.getHeight() / 2);
	    sprite.setScale(SCALE);
	    return sprite;
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
    public boolean hit(Body body1, Body body2) {
    	if(body1 == body) {
        	//TODO: reduce health by amount listed on projectile
        	//and delete projectile (body at fixtureB)
        	return true;
        }
        if(body2 == body) {
        	//TODO: reduce health by amount listed on projectile
        	//and delete projectile (body at fixtureA)
        	return true;
        }
        return false;
    }
    public void setToEnemy() {//testing class which creates a new fixture of type enemy for the player to colide with
    	Array<Fixture> fixtures = body.getFixtureList();
    	fixtures.removeIndex(0);
    	
    	PolygonShape shape = new PolygonShape();
	    shape.setAsBox(sprite.getWidth()/2  / screen.PIXELS_TO_METERS * SCALE / 2, 
	    			   sprite.getHeight()/2 / screen.PIXELS_TO_METERS * SCALE);
    	FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = shape;
	    fixtureDef.density = 10f;
	    fixtureDef.filter.categoryBits = WorldColisionType.CATEGORY_CREEP_ENTITY.getType();//set collision group
	    fixtureDef.filter.maskBits = WorldColisionType.MASK_CREEP_ENTITY.getType();//set group to collide with
	    
    	body.createFixture(fixtureDef);
    }
    public Classes getClasses() {
    	return classes;
    }
}
