package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
import com.roguelike.lootly.gameworld.MyOrthoMap;
import com.roguelike.lootly.gameworld.Projectile;
import com.roguelike.lootly.gameworld.WorldMap;
import com.roguelike.lootly.gui.ItemDisplayBox;
import java.util.ArrayList;
import java.util.Iterator;

public class GameScreen implements Screen, InputProcessor {
	final Lootly game;
	private Stage stage;
	
    public World world;//physics world
    
    //TmxMapLoader 
    //OrthographicCamera camera;
    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;
    
    
    Character player;
    Character player2;
    Projectile proj[];
    
    private int firecount = -1;//set of vars control projectile firerate
    private boolean fire = false;
    private int fireDelay = 0;
    private final int MAXFIRE = 2;
    
    private ArrayList<Projectile> prjctl;
    
    public final float PIXELS_TO_METERS = 100f;//scale of movement speed
    
    public final short PLAYER_ENTITY = 1;
    public final short PLAYER_PROJECTILE = 2;
    public final short CREEP_ENTITY = 3;
    public final short CREEP_PROJECTILE = 4;
    public final short WORLD_STATIC = 5;
    public final short WORLD_DESTRUCTABLE = 6;
    public final short WORLD_INTERACTABLE = 7;
    
	ItemDisplayBox itemBox = new ItemDisplayBox(Lootly.itemList.get(1)); //TODO: Remove debugging object
	ItemDisplayBox itemBoxClone = new ItemDisplayBox(Lootly.itemList.get(1).clone()); //TODO: Remove debugging object	
	
	public GameScreen(Lootly game) {
		this.game = game;
		stage = new Stage(game.viewport);
        world = new World(new Vector2(), true);//create world w/o gravity
		player = new Character(this, Classes.CRUSADER);
		player2 = new Character(this, Classes.ROGUE);
		player2.setToEnemy();//testing class to see how enemies work
		prjctl = new ArrayList<Projectile>();
		createComplexColisions();//makes collisions do more than just displacement
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
        
		//Tile Map instantaition
		tiledMap = new TmxMapLoader().load("maps/Atempt2.tmx");//load in map and create rendered version
		WorldMap.loadWorld(tiledMap);
		tiledMapRenderer = new MyOrthoMap(tiledMap);
		
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
		//moves player sprite according to wasd and arrow keys
		fire = player.getMotionInput();//returns true if mouse is pressed
		
		//////////////////////////Fire projectile at most every 20 renders
		if(fireDelay != 0){//if shot recently fired skip firing
			fireDelay--;
		}
		else {
			if(fire)
				prjctl.add( new Projectile(this,player) );
			fireDelay = 20;
		}
		//////////////////////////
		
		//update world 60 times per second
		world.step(1f/60f, 6, 2);
		
		game.batch.setProjectionMatrix(game.camera.combined);
		
		//Reset Screen
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		//Acts and draws scene
		stage.act(delta);
		stage.draw();
		
		///////////////////////////////update sprite positioning with world positioning
		player.posSpriteToWorld();
		if(player2 != null)
			player2.posSpriteToWorld();
		
		for(int i = 0; i < prjctl.size(); i++) {
			prjctl.get(i).posSpriteToWorld();
			if(!prjctl.get(i).getDuration()) {
				prjctl.remove(prjctl.get(i));
				i--;
			}
		}
		///////////////////////////////
		
		//clear then populate Arraylist of sprites to load under layers
		
		//clear Arraylist
		((MyOrthoMap) tiledMapRenderer).clearSprites();
		
		//populate Arraylist
		for(Projectile p: prjctl) 
			((MyOrthoMap) tiledMapRenderer).addSprite(p.getSprite());
		((MyOrthoMap) tiledMapRenderer).addSprite(player.getSprite());
		if(player2 != null)
			((MyOrthoMap) tiledMapRenderer).addSprite(player2.getSprite());
		
		//Draws Map to Screen
		tiledMapRenderer.setView((OrthographicCamera) game.camera);//sets the location to draw the map to the location of the camera
		tiledMapRenderer.render();//draw map
				
		
		
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
	        	if(player2.hit(body1, body2)) {//if either body involved in collision are player2, performs function and returns true
	        		player2 = null;
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
}
