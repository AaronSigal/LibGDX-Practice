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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen implements Screen, InputProcessor {
	final Lootly game;
	private Stage stage;

	//simple animation initial
	private static final int FRAME_COLS = 3;
	private static final float ATTACK_RATE = 0.15f;
	private static final float PROJECTILE_SPEED = 5f;
	private static final float PROJECTILE_DURATION = 1f;
	
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
	float mouseX = 0.0F;
	float mouseY = 0.0F;
	
	
    TextureRegion frame;
    Vector2[][] projectile;
    float[] lifespan;
    int projNum = -1;
    
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
        
        //supply starting animation frame
        currentFrame = frames[0];
        
        //generate animation from textureregions
		this.animation = new Animation<TextureRegion>(ATTACK_RATE, frames);
        this.batch = new SpriteBatch();
        this.stateTime = 0.0F;
	}
	
	public void createProjectile(){
		sheet = new Texture(Gdx.files.internal("character/rogue/rogue_projectile.png"));
        TextureRegion[][] tmp = TextureRegion.split(this.sheet, this.sheet.getWidth(), this.sheet.getHeight());
        frame = tmp[0][0];
	}
	
	public void throwProjectile() {
		if(currentFrame == frames[1]){
			projNum++;
			if(projNum==5)
				return;
			projectile[projNum][0] = new Vector2(posX, posY);
			projectile[projNum][1] = (new Vector2(mouseX - posX, mouseY - posY )).setLength(PROJECTILE_SPEED);
			lifespan[projNum] = PROJECTILE_DURATION;
        }
		if(lifespan[0] < 0.0F) {
			projectile[0][0] = projectile[1][0]; projectile[0][1] = projectile[1][1]; lifespan[0] = lifespan[1];
			projectile[1][0] = projectile[2][0]; projectile[1][1] = projectile[2][1]; lifespan[1] = lifespan[2];
			projectile[2][0] = projectile[3][0]; projectile[2][1] = projectile[3][1]; lifespan[2] = lifespan[3];
			projectile[3][0] = projectile[4][0]; projectile[3][1] = projectile[4][1]; lifespan[3] = lifespan[4];
			projectile[4][0] = null; 			 projectile[4][1] = null;			  lifespan[4] = -1.0F;
			projNum--;
		}
		System.out.println("here");
		for(int j = 0; j != projNum + 1; j++) {
			projectile[j][0].add(projectile[j][1]);
			lifespan[j] -= .25F;
		}
	}
	
	public void drawProjectiles() {
		for(int j = 0; j > projNum; j++)
			batch.draw(frame, projectile[j][0].x, projectile[j][0].y, 0.0F, 0.0F, 16.0F, 16.0F, 5.0F, 5.0F, projectile[j][1].angle());
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		createAnimation();
		
		//set max number of assignable vector projectiles
        projectile = new Vector2[2][5];
        lifespan = new float[5];
		
        createProjectile();
	}

	@Override
	public void render(float delta) {
		//clear screen and paint it blue
		Gdx.gl.glClearColor(0.57F, 0.77F, 0.85F, 1.0F);
        Gdx.gl.glClear(16384);
        
        //update animation frame if left mouse down or animation has not completed cycle
        mouseIsPressed = Gdx.input.isButtonPressed(Input.Buttons.LEFT);
        if(mouseIsPressed||currentFrame!=frames[0]) 
        	stateTime += Gdx.graphics.getDeltaTime();
        
        //throw projectile on first frame of animation
        throwProjectile();
        
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
        //drawProjectiles();
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
