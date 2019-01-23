package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.roguelike.lootly.character.CharacterClassManager;
import com.roguelike.lootly.character.Classes;
import com.roguelike.lootly.gui.ItemDisplayBox;
import com.roguelike.lootly.item.Item;

public class GameScreen implements Screen, InputProcessor {
	final Lootly game;
	private Stage stage;
	
    Sprite sprite;//rogue
    Sprite sprite2;//mage
    World world;
    Body body;
    Body body2;
    Body bodyEdgeScreen;
    final float PIXELS_TO_METERS = 100f;//scale of movement speed
    final float SCALE = 3;
    final float SCALE2 = .3f;
    
	ItemDisplayBox itemBox = new ItemDisplayBox(Lootly.itemList.get(1)); //TODO: Remove debugging object
	ItemDisplayBox itemBoxClone = new ItemDisplayBox(Lootly.itemList.get(1).clone()); //TODO: Remove debugging object	
	
	public GameScreen(Lootly game) {
		this.game = game;
		stage = new Stage(game.viewport);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		newWorld();
        
		//Actor instantiation
		itemBox.setX(Gdx.graphics.getWidth()/2);
		itemBox.setY(Gdx.graphics.getHeight()/2);
		
		itemBoxClone.setX(Gdx.graphics.getWidth()/2 + 300);
		itemBoxClone.setY(Gdx.graphics.getHeight()/2);
		
		//Actor staging
		stage.addActor(itemBox);
		stage.addActor(itemBoxClone);
		itemBoxClone.getItem().setSprite(new Sprite(new Texture("item/candy_01g.png")));
		
	}

	@Override
	public void render(float delta) {
		
		getMotionInput();//moves rogue sprite according to wasd and arrowkeys
		world.step(1f/60f, 6, 2);//update world
		
		sprite.setPosition( (body.getPosition().x * PIXELS_TO_METERS) - sprite.getWidth()/2 ,
							(body.getPosition().y * PIXELS_TO_METERS) -sprite.getHeight()/2 );//set sprite position to box postion
		sprite.setRotation((float)Math.toDegrees(body.getAngle()));//set sprite rotation to box position
        
        sprite2.setPosition((body2.getPosition().x * PIXELS_TO_METERS) - sprite2.getWidth()/2 ,
							(body2.getPosition().y * PIXELS_TO_METERS) -sprite2.getHeight()/2 );//set sprite position to box postion
        sprite2.setRotation((float)Math.toDegrees(body2.getAngle()));//set sprite rotation to box position
        
		game.batch.setProjectionMatrix(game.camera.combined);
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		//stage.draw();
		
		game.batch.begin();
		game.batch.draw(sprite,    sprite.getX(), sprite.getY(),
				sprite.getOriginX(),sprite.getOriginY(),
				sprite.getWidth(),   sprite.getHeight(),
				sprite.getScaleX(),  sprite.getScaleY(),sprite.getRotation());
		game.batch.draw(sprite2,    sprite2.getX(), sprite2.getY(),
						sprite2.getOriginX(),sprite2.getOriginY(),
						sprite2.getWidth(),   sprite2.getHeight(),
						sprite2.getScaleX(),  sprite2.getScaleY(),sprite2.getRotation());
		game.batch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
		// 

	}

	@Override
	public void pause() {
		

	}

	@Override
	public void resume() {
		

	}

	@Override
	public void hide() {
		

	}

	@Override
	public void dispose() {
		

	}

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		return false;
	}
	
	public void newWorld() {     
        world = new World(new Vector2(), true);//create world w/o gravity
        
        sprite = CharacterClassManager.getClassSprite(Classes.ROGUE);
        sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                		   Gdx.graphics.getHeight() * .66f);
        sprite.setScale(SCALE);
        
        BodyDef bodyDef = new BodyDef();//make body in world
        bodyDef.type = BodyDef.BodyType.DynamicBody;//define body
        bodyDef.position.set((sprite.getX() + sprite.getWidth() /2) / PIXELS_TO_METERS, 
        					 (sprite.getY() + sprite.getHeight()/2) / PIXELS_TO_METERS);//set body position to match sprite adjusted position
        body = world.createBody(bodyDef);//make body in world
        PolygonShape shape = new PolygonShape();//make poly
        shape.setAsBox(sprite.getWidth()/2  / PIXELS_TO_METERS * SCALE / 2, 
        			   sprite.getHeight()/2 / PIXELS_TO_METERS * SCALE);//set poly dimension to sprite adjusted size
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;//define shape of body
        fixtureDef.density = 1f;//define weight of body
        body.createFixture(fixtureDef);
        body.setFixedRotation(true);

        
        sprite2 = CharacterClassManager.getClassSprite(Classes.MAGE);
        sprite2.setPosition(Gdx.graphics.getWidth() / 2 - sprite2.getWidth() / 2,
                		    Gdx.graphics.getHeight() * .33f);
        sprite2.setScale(SCALE2);
        
        BodyDef bodyDef2 = new BodyDef();//make body in world
        bodyDef2.type = BodyDef.BodyType.DynamicBody;//define body
        bodyDef2.position.set((sprite2.getX() + sprite2.getWidth() /2) / PIXELS_TO_METERS, 
        					  (sprite2.getY() + sprite2.getHeight()/2) / PIXELS_TO_METERS);//set body position to match sprite adjusted position
        body2 = world.createBody(bodyDef);//make body in world
        shape = new PolygonShape();//make poly
        shape.setAsBox(sprite2.getWidth()/2  / PIXELS_TO_METERS * SCALE2 / 2,
 			   		   sprite2.getHeight()/2 / PIXELS_TO_METERS * SCALE2);//set poly dimension to sprite adjusted size
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape;
        fixtureDef2.density = 10f;
        body2.createFixture(fixtureDef2);
        body2.setFixedRotation(true);
        
        shape.dispose();
        
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                // Check to see if the collision is between the second sprite and the bottom of the screen
                // If so apply a random amount of upward force to both objects... just because
                if((contact.getFixtureA().getBody() == body &&
                    contact.getFixtureB().getBody() == body2)||
                   (contact.getFixtureA().getBody() == body2 &&
                    contact.getFixtureB().getBody() == body)) {

                	
                }
            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }
        });
	}
	
	public void getMotionInput(){
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
	}
	

}
