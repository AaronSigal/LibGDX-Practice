package com.roguelike.lootly;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import java.util.HashMap;

public class Lootly extends ApplicationAdapter {
    
	//Declarations for an animation
	private static final int FRAME_COLS = 15, FRAME_ROWS = 1;//number of sprites in sheet
    Animation<TextureRegion> soarAnimation;
    Texture soarSheet;
    SpriteBatch spriteBatch;
    float stateTime;
    
    //inessential Animation vars
    float posX = 75;
    float posY = 75;
    boolean move = false;
    int orient = 0;
    
    //Declarations for Hashmap
	final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    
    TextureAtlas textureAtlas;

    SpriteBatch batch;

    OrthographicCamera camera;

    ExtendViewport viewport;

    //Declarations of world basis
    World world;
    static final float STEP_TIME = 1f / 60f;
    static final int VELOCITY_ITERATIONS = 6;
    static final int POSITION_ITERATIONS = 2;

    float accumulator = 0;

    private void stepWorld() {
        float delta = Gdx.graphics.getDeltaTime();

        accumulator += Math.min(delta, 0.25f);

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;

            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }
    
    @Override
    public void create() {
    	
    	soarSheet = new Texture(Gdx.files.internal("Fireball.png"));
    	TextureRegion[][] tmp = TextureRegion.split(soarSheet, 
				soarSheet.getWidth() / FRAME_COLS,
				soarSheet.getHeight() / FRAME_ROWS);
    	TextureRegion[] soarFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				soarFrames[index++] = tmp[i][j];
			}
		}
		soarAnimation = new Animation<TextureRegion>(0.05f, soarFrames);
		spriteBatch = new SpriteBatch();
		stateTime = 0f;
		
		//initializing required vars to display a simple sprite
        Box2D.init();
        world = new World(new Vector2(0, 0), true);
        batch = new SpriteBatch();

        camera = new OrthographicCamera();

        viewport = new ExtendViewport(800, 600, camera);

        textureAtlas = new TextureAtlas("Fireball.txt");

        addSprites();
    }

    private void addSprites() {
        Array<AtlasRegion> regions = textureAtlas.getRegions();

        for (AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //detect wasd inputs
		boolean wIsPressed = Gdx.input.isKeyPressed(Keys.W);
		boolean aIsPressed = Gdx.input.isKeyPressed(Keys.A);
		boolean sIsPressed = Gdx.input.isKeyPressed(Keys.S);
		boolean dIsPressed = Gdx.input.isKeyPressed(Keys.D);
		//set fireball rotation and enable movement if wasd key pressed
		if(wIsPressed) {	posY+=1.5;	move = true;	orient = 90;}
		if(aIsPressed) {	posX-=1.5;	move = true;	orient = 180;}
		if(sIsPressed) {	posY-=1.5;	move = true;	orient = 270;}
		if(dIsPressed) {	posX+=1.5;	move = true;	orient = 0;}
		if(wIsPressed&&aIsPressed) {	posX+=.43;	posY-=.43;	move = true;	orient = 135;}
        //
		if(move) {
        	stateTime += Gdx.graphics.getDeltaTime();
        	move = false;
        }
		//update Animation frame to frame corresponding to amount of time passed
        TextureRegion currentFrame = soarAnimation.getKeyFrame(stateTime, true);
        
		batch.begin();
        stepWorld();
        drawSprite("Fireball1", 0, 0);
        drawSprite("Fireball3", 100, 100);
        batch.draw(currentFrame, 100,100);
        batch.end();
        
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, posX,posY, //TextureRegion & X&Y coordinates
        		0,0,	//Amount of texture drawn: X&Y start-points from bottom left
        		16,16,	//Amount of texture drawn: Width & Height of texture amount drawn
        		5,5,	//times scaled
        		orient);//amount rotated from base (degrees)
        spriteBatch.end();
    }

    private void drawSprite(String name, float x, float y) {
        Sprite sprite = sprites.get(name);

        sprite.setPosition(x, y);

        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
        world.dispose();
        sprites.clear();
        batch.dispose();
        spriteBatch.dispose();
        soarSheet.dispose();
    }
}