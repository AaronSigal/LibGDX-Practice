package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.roguelike.lootly.character.Classes;
import com.roguelike.lootly.gui.mainmenu.ActorClassSphere;
import com.roguelike.lootly.gui.mainmenu.ActorMainMenuFrame;

public class MainMenuScreen implements Screen, InputProcessor {	
	final Lootly game;
	private Stage stage;
	private Texture background;
	private Texture titleTexture;
	private Sprite titleSprite;
	ActorMainMenuFrame menuFrame; //the image the spheres for class selection are superimposed upon
	ActorClassSphere[] classSpheres;//array to hold all the spheres for class selection on the main menu
	Button playButton;
	BitmapFont font = Utils.getFont(36);
	Classes playerClass;
	Skin skin = new Skin(Gdx.files.internal("gui/skin/LootlyV1/LootlyV1.json"));
	
	final float mainMenuScale = 3f;
	
	Music menuMusic;  //background music: http://freemusicarchive.org/music/Rolemusic/The_Black_Dot/The_Black_Kitty
	
	//takes place of "void create()"
	public MainMenuScreen(final Lootly game) {
		this.game = game;
		
		stage = new Stage(game.viewport);
		
		//*********** Play Button Instantiation *****************
		playButton = new Button(skin);
		playButton.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.startGameScreen();
	        }
	    });
	}

	@Override
	public void show() {
		
		//audio loading
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
		
		Gdx.input.setInputProcessor(stage);
		menuMusic.play();
		
		//image loading
		background = new Texture("gui/background.png");
		titleTexture = new Texture("gui/title.png");
		titleSprite = new Sprite(titleTexture);
		
		
		//Actor instantiation
		
		//******************************** menu frame ****************************
		menuFrame = new ActorMainMenuFrame();                                  //*
		menuFrame.setScale(mainMenuScale);	                                   //*
		menuFrame.setOrigin(menuFrame.getWidth()/2, menuFrame.getHeight()/2);  //*
		menuFrame.spritePos(Gdx.graphics.getWidth()/2 - menuFrame.getWidth()/2,/**/ 
				Gdx.graphics.getHeight()/2 - menuFrame.getHeight()/2);         //*
		//************************************************************************
		
		initSpheres();
		
		
		//Actor staging
		stage.addActor(menuFrame);
		playButton.setTransform(true);
		playButton.setOrigin(playButton.getWidth()/2, playButton.getHeight()/2);
		playButton.setScale(4f);
		stage.addActor(playButton);
		
		//add all the spheres to the stage
		for (int i = 0; i < classSpheres.length; i++) {
			stage.addActor(classSpheres[i]);
		}
		
		playButton.setX(Gdx.graphics.getWidth()/2 - playButton.getWidth()/2);
		playButton.setY(Gdx.graphics.getHeight() * 0.08f);
		setSphereLocations();
		
		titleSprite.setScale(10f);
		titleSprite.setX(Gdx.graphics.getWidth()/2 - titleSprite.getWidth()/2);
		titleSprite.setY(Gdx.graphics.getHeight() * 0.9f);
	}

	@Override
	public void render(float delta) {
		game.batch.setProjectionMatrix(game.camera.combined);
		
		game.batch.begin();
		game.batch.draw(background, 0, 0); //background texture, native 1080p
		
		titleSprite.draw(game.batch);
		game.batch.end();
		
		stage.act(delta);
		stage.draw();
		
		game.batch.setProjectionMatrix(game.camera.combined);
		
		
		//if music isn't playing, start it.
		if (!menuMusic.isPlaying()) {
			menuMusic.play();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		//update the size of the viewport upon resize
		stage.getViewport().update(width, height, true);
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		menuMusic.dispose();
		background.dispose();;
		titleTexture.dispose();
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
		menuMusic.stop();

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
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
	
	//initializes all the class spheres. TODO: add support for loading class unlocks from a save files
	public void initSpheres() {
		classSpheres = new ActorClassSphere[11];
		
		for (int i = 0; i < classSpheres.length; i++) {
			classSpheres[i] = new ActorClassSphere(Classes.values()[i]);
			classSpheres[i].setOrigin(classSpheres[i].getWidth()/2, classSpheres[i].getHeight()/2);
			
			//by default, disable all the hybrid classes and the "final" class. This should eventually be replaced with a proper save file. TODO: replace with save functionality
			if (i % 2 == 0) {
				classSpheres[i].setEnabled(false);
			}
		}
	}
	
	//Sphere coordinates: (127,18) (191, 39) (234, 102) (234, 154) (191, 217) (127, 238) (63, 217) (20, 154) (20, 102) (63, 39) (127, 128)
	public void setSphereLocations() {
		Vector2[] sphereCoords = new Vector2[classSpheres.length];
		
		sphereCoords[0] = menuFrame.localToScreenCoordinates(new Vector2(125, 18));
		sphereCoords[1] = menuFrame.localToScreenCoordinates(new Vector2(198, 43));
		sphereCoords[2] = menuFrame.localToScreenCoordinates(new Vector2(234, 102));
		sphereCoords[3] = menuFrame.localToScreenCoordinates(new Vector2(230, 166));
		sphereCoords[4] = menuFrame.localToScreenCoordinates(new Vector2(188, 214));
		sphereCoords[5] = menuFrame.localToScreenCoordinates(new Vector2(125, 238));
		sphereCoords[6] = menuFrame.localToScreenCoordinates(new Vector2(61, 215));
		sphereCoords[7] = menuFrame.localToScreenCoordinates(new Vector2(25, 167));
		sphereCoords[8] = menuFrame.localToScreenCoordinates(new Vector2(17, 102));
		sphereCoords[9] = menuFrame.localToScreenCoordinates(new Vector2(56, 44));
		sphereCoords[10] = menuFrame.localToScreenCoordinates(new Vector2(124, 127));
		
		for (int i = 0; i < sphereCoords.length; i++) {
			classSpheres[i].setOrigin(Align.center);
			classSpheres[i].sphereSpritePos(sphereCoords[i].x, sphereCoords[i].y);
		}
		
	}
}
