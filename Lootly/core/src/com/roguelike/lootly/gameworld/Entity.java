package com.roguelike.lootly.gameworld;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.roguelike.lootly.GameScreen;

public abstract class Entity {
	
	protected Sprite sprite;
	protected GameScreen screen;
	protected Body body;
	public final float SCALE = 3f;	

    protected abstract Sprite fetchSprite();
    
    protected BodyDef defineBody(Sprite sprite, BodyDef.BodyType bodyType) {
    	BodyDef bodyDef = new BodyDef();//make body in world
	    bodyDef.type = bodyType;//define body
	    //set body position to match sprite adjusted position
	    bodyDef.position.set((sprite.getX() + sprite.getWidth() /2) / screen.PIXELS_TO_METERS, 
	    					 (sprite.getY() + sprite.getHeight()/2) / screen.PIXELS_TO_METERS);
	    return bodyDef;
    }
    protected BodyDef defineBody(float x, float y, BodyDef.BodyType bodyType) {
    	BodyDef bodyDef = new BodyDef();//make body in world
	    bodyDef.type = bodyType;//define body
	    //set body position to match sprite adjusted position
	    bodyDef.position.set(x / screen.PIXELS_TO_METERS, y / screen.PIXELS_TO_METERS);
	    return bodyDef;
    }
    protected FixtureDef defineFixture(Shape shape, WorldColisionType catType, WorldColisionType maskType) {
	    FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = shape;//define shape of body
	    fixtureDef.filter.categoryBits = catType.getType();//set collision group
	    fixtureDef.filter.maskBits = maskType.getType();//set group to collide with
	    return fixtureDef;
    }
    protected FixtureDef defineFixture(Shape shape, float density, float friction, float restitution, WorldColisionType catType, WorldColisionType maskType) {
	    FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = shape;//define shape of body
	    fixtureDef.density = density;//define weight of body, friction, restitution
	    fixtureDef.friction = friction;
	    fixtureDef.restitution = restitution;
	    fixtureDef.filter.categoryBits = catType.getType();//set collision group
	    fixtureDef.filter.maskBits = maskType.getType();//set group to collide with
	    return fixtureDef;
    }
    public void posSpriteToWorld() {
    	sprite.setPosition( (body.getPosition().x * screen.PIXELS_TO_METERS) - sprite.getWidth()/2 ,
    						(body.getPosition().y * screen.PIXELS_TO_METERS) -sprite.getHeight()/2 );//set sprite position to box postion
    	sprite.setRotation((float)Math.toDegrees(body.getAngle()));//set sprite rotation to box position
    }
    public Sprite getSprite() {
    	return sprite;
    }
    public Body getBody() {
    	return body;
    }

}
