package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.roguelike.lootly.character.Classes;
import com.roguelike.lootly.gameworld.Character;
import com.roguelike.lootly.gameworld.Projectile;
import com.roguelike.lootly.gui.ItemDisplayBox;

public class GameScreen implements Screen, InputProcessor {
	final Lootly game;
	private Stage stage;
    public World world;
    
    Character player;
    Character player2;
    Projectile proj[];
    
    private int firecount = -1;
    private boolean fire = false;
    private int fireDelay = 0;
    
    
    public final float PIXELS_TO_METERS = 100f;//scale of movement speed
    public final short PLAYER_ENTITY = 1;
    public final short PLAYER_PROJECTILE = 2;
    public final short CREEP_ENTITY = 3;
    public final short CREEP_PROJECTILE = 4;
    
	ItemDisplayBox itemBox = new ItemDisplayBox(Lootly.itemList.get(1)); //TODO: Remove debugging object
	ItemDisplayBox itemBoxClone = new ItemDisplayBox(Lootly.itemList.get(1).clone()); //TODO: Remove debugging object	
	
	public GameScreen(Lootly game) {
		this.game = game;
		stage = new Stage(game.viewport);
        world = new World(new Vector2(), true);//create world w/o gravity
		player = new Character(this, Classes.CRUSADER);
		player2 = new Character(this, Classes.ROGUE);
		proj = new Projectile[2];
		createComplexColisions();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
        
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
		
		fire = player.getMotionInput();//moves rogue sprite according to wasd and arrowkeys
		
		if(fire) {//Terrible attempt at recursive firing
			if(fireDelay != 0){
				fireDelay--;
			}
			else {
				if(firecount == 1)
					firecount = -1;
				firecount++;
				proj[firecount] = new Projectile(this,player);
				fireDelay = 30;
			}
		}
		
		world.step(1f/60f, 6, 2);//update world 60 times per second
		
		game.batch.setProjectionMatrix(game.camera.combined);
		
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		//stage.draw();
		
		player.posSpriteToWorld();//match sprite positioning with world positioning
		player2.posSpriteToWorld();//match sprite positioning with world positioning
		
		if(firecount!=-1)
			proj[firecount].posSpriteToWorld();//match sprite positioning with world positioning
		
		game.batch.begin();
		
		if(firecount!=-1)
			drawSprite(proj[firecount].getSprite());
		
		drawSprite(player.getSprite());
		drawSprite(player2.getSprite());
		game.batch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
		

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
	
	private void createComplexColisions() {//handles world collisions according to various contact events
		world.setContactListener(new ContactListener() {
	        @Override
	        public void beginContact(Contact contact) {
	            // Check to see if collision involves player
	        	Body body1 = contact.getFixtureA().getBody();
	        	Body body2 = contact.getFixtureB().getBody();
	        	if(player.hit(body1, body2))//if either body involved in collision are the player, performs function and returns true
	        		return;
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
	
	private void drawSprite(Sprite sprite) {
		game.batch.draw(sprite,    sprite.getX(), sprite.getY(),
						sprite.getOriginX(),sprite.getOriginY(),
						sprite.getWidth(),   sprite.getHeight(),
						sprite.getScaleX(),  sprite.getScaleY(),sprite.getRotation());
	}
	

}
