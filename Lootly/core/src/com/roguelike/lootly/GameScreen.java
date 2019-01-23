package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
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
    World world;
    Body body;
    final float PIXELS_TO_METERS = 100f;//scale of movement speed
    
	ItemDisplayBox itemBox = new ItemDisplayBox(Lootly.itemList.get(1)); //TODO: Remove debugging object
	ItemDisplayBox itemBoxClone = new ItemDisplayBox(Lootly.itemList.get(1).clone()); //TODO: Remove debugging object	
	
	public GameScreen(Lootly game) {
		this.game = game;
		stage = new Stage(game.viewport);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		//Rogue sprite introduction
		//game.batch
        sprite = CharacterClassManager.getClassSprite(Classes.ROGUE);
        sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                		   Gdx.graphics.getHeight() / 2);
        sprite.setScale(5);
        
        world = new World(new Vector2(), true);//create world w/o gravity
        BodyDef bodyDef = new BodyDef();//make body in world
        bodyDef.type = BodyDef.BodyType.DynamicBody;//define body
        bodyDef.position.set((sprite.getX() + sprite.getWidth() /2) / PIXELS_TO_METERS, 
        					 (sprite.getY() + sprite.getHeight()/2) / PIXELS_TO_METERS);//set body position to match sprite adjusted position
        body = world.createBody(bodyDef);//make body in world
        PolygonShape shape = new PolygonShape();//make poly
        shape.setAsBox(sprite.getWidth()/2 / PIXELS_TO_METERS, sprite.getHeight()/2 / PIXELS_TO_METERS);//set poly dimension to sprite adjusted size
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;//define shape of body
        fixtureDef.density = .1f;//define weight of body
        Fixture fixture = body.createFixture(fixtureDef);
        shape.dispose();
        
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
        
        
		
		game.batch.setProjectionMatrix(game.camera.combined);
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
		game.batch.begin();
		game.batch.draw(sprite,    sprite.getX(), sprite.getY(),
				sprite.getOriginX(),sprite.getOriginY(),
				sprite.getWidth(),   sprite.getHeight(),
				sprite.getScaleX(),  sprite.getScaleY(),sprite.getRotation());
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
	
	public void getMotionInput(){
		float inpDirX = 0.0f;
		float inpDirY = 0.0f;
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)||Gdx.input.isKeyPressed(Input.Keys.D)) 
            inpDirX++;
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)||Gdx.input.isKeyPressed(Input.Keys.A))
            inpDirX--;
		if(Gdx.input.isKeyPressed(Input.Keys.UP)||Gdx.input.isKeyPressed(Input.Keys.W))
            inpDirY++;
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)||Gdx.input.isKeyPressed(Input.Keys.S))
            inpDirY--;
		body.setLinearVelocity(inpDirX, inpDirY);
	}
	

}
