package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen, InputProcessor {
	final Lootly game;
	private Stage stage;

	//simple animation initial
	private static final int FRAME_COLS = 3;
	private static final float ATTACK_RATE = 0.15f;
	private static final float PROJECTILE_SPEED = 5f;
	
    Animation<TextureRegion> animation;
    Texture sheet;
    TextureRegion[] frames;    
    TextureRegion currentFrame;
    SpriteBatch batch;
    float stateTime;
    float posX = 75.0F;
    float posY = 75.0F;
    
    boolean wIsPressed = false;
    boolean aIsPressed = false;
    boolean sIsPressed = false; 
    boolean dIsPressed = false;
    boolean mouseIsPressed = false;
	float mouseX = 0.0f;
	float mouseY = 0.0f;
	float distToTravelX = 0.0f;
	float distToTravelY = 0.0f;
    TextureRegion frame;

    
	public GameScreen(final Lootly game) {
		this.game = game;

		stage = new Stage(game.viewport);
	}

	public void createAnimation() {
		//seperate linear texture into texureregions
		sheet = new Texture(Gdx.files.internal("character/rogue/rogue_strike.png"));
        TextureRegion[][] tmp = TextureRegion.split(this.sheet, this.sheet.getWidth() / FRAME_COLS, this.sheet.getHeight());
        frames = new TextureRegion[FRAME_COLS];
        for(int i = 0; i < FRAME_COLS; ++i) {
            frames[i] = tmp[0][i];
        }
        currentFrame = frames[0];
        //generate animation from textureregions
		this.animation = new Animation<TextureRegion>(ATTACK_RATE, frames);
        this.batch = new SpriteBatch();
        this.stateTime = 0.0F;
	}
	
	public void createSprite(){
		sheet = new Texture(Gdx.files.internal("character/rogue/rogue_projectile.png"));
        TextureRegion[][] tmp = TextureRegion.split(this.sheet, this.sheet.getWidth(), this.sheet.getHeight());
        frame = tmp[0][0];
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		createAnimation();
		createSprite();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
		//clear screen and paint it blue
		Gdx.gl.glClearColor(0.57F, 0.77F, 0.85F, 1.0F);
        Gdx.gl.glClear(16384);
        
        //update animation frame if left mouse down or animation has not completed cycle;
        mouseIsPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        if(mouseIsPressed)
        	if(currentFrame!=frames[0]) 
        		stateTime += Gdx.graphics.getDeltaTime();
        	else if(distToTravelX >= 0.0 || distToTravelY >= 0.0) {//calculate attack direction
        		distToTravelX -= PROJECTILE_SPEED;
        		distToTravelY -= PROJECTILE_SPEED;
        	}
        	else{
        		distToTravelX = Gdx.input.getX() - posX;
        		distToTravelY = Gdx.input.getY() - posY;        		
        	}
        
        //set animation frame
        currentFrame = animation.getKeyFrame(stateTime, true);
        
        
        //reposition animation
        wIsPressed = Gdx.input.isKeyPressed(51);
        aIsPressed = Gdx.input.isKeyPressed(29);
        sIsPressed = Gdx.input.isKeyPressed(47);
        dIsPressed = Gdx.input.isKeyPressed(32);
        
        if(wIsPressed) posY += 1.5f;
        if(aIsPressed) posX -= 1.5f;
        if(sIsPressed) posY -= 1.5f;
        if(dIsPressed) posX += 1.5f;
        
        
        
        
        //draw animation
        batch.begin();
        batch.draw(frame, posX + 500, posY + 500, 0.0F, 0.0F, 16.0F, 16.0F, 5.0F, 5.0F, 0);
        batch.draw(currentFrame, posX, posY, 0.0F, 0.0F, 16.0F, 16.0F, 5.0F, 5.0F, 0);
        batch.end();
        
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.sheet.dispose();
		this.batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		switch(keycode) {
			case Keys.W:
			case Keys.UP:
				wIsPressed = true;
				break;
			case Keys.A:
			case Keys.LEFT:
				aIsPressed = true;
				break;
			case Keys.S:
			case Keys.DOWN:
				sIsPressed = true;
				break;
			case Keys.D:
			case Keys.RIGHT:
				dIsPressed = true;
				break;
			}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		switch(keycode) {
			case Keys.W:
			case Keys.UP:
				wIsPressed = false;
				break;
			case Keys.A:
			case Keys.LEFT:
				aIsPressed = false;
				break;
			case Keys.S:
			case Keys.DOWN:
				sIsPressed = false;
				break;
			case Keys.D:
			case Keys.RIGHT:
				dIsPressed = false;
				break;
			}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		mouseX = Gdx.input.getX();
		mouseY = Gdx.input.getY();
		mouseIsPressed = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		mouseIsPressed = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		mouseX = Gdx.input.getX();
		mouseY = Gdx.input.getY();
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
