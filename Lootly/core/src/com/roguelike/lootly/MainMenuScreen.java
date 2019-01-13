package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.roguelike.lootly.gui.mainmenu.ActorClassSphere;
import com.roguelike.lootly.gui.mainmenu.ActorMainMenuFrame;

public class MainMenuScreen implements Screen, InputProcessor {	
	final Lootly game;
	private Stage stage;
	private Texture background;
	ActorMainMenuFrame menuFrame; //the image the spheres for class selection are superimposed upon
	ActorClassSphere[] classSpheres;//array to hold all the spheres for class selection on the main menu
	
	final float mainMenuScale = 3f;
	
	Music menuMusic;  //background music: http://freemusicarchive.org/music/Rolemusic/The_Black_Dot/The_Black_Kitty
	
	//takes place of "void create()"
	public MainMenuScreen(final Lootly game) {
		this.game = game;
		
		stage = new Stage(game.viewport);
	}
	
	//initializes all the class spheres.
	public void initSpheres() {
		
		classSpheres = new ActorClassSphere[11];
		
		for (int i = 0; i < classSpheres.length; i++) {
			classSpheres[i] = new ActorClassSphere(Classes.values()[i]);
		}
	}

	@Override
	public void show() {
		
		//audio loading
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
		
		Gdx.input.setInputProcessor(stage);
		menuMusic.play();
		
		//image loading
		background = new Texture("gui/background.png");
		
		//Actor instantiation
		
		//******************************** menu frame ****************************
		menuFrame = new ActorMainMenuFrame();                                  //*
		menuFrame.setScale(mainMenuScale);	                                               //*
		menuFrame.setOrigin(menuFrame.getWidth()/2, menuFrame.getHeight()/2);  //*
		menuFrame.spritePos(Gdx.graphics.getWidth()/2 - menuFrame.getWidth()/2,/**/ 
				Gdx.graphics.getHeight()/2 - menuFrame.getHeight()/2);         //*
		//************************************************************************
		
		initSpheres();
		
		
		//Actor staging
		
		//Sphere coordinates: (127,18) (191, 39) (234, 102) (234, 154) (191, 217) (127, 238) (63, 217) (20, 154) (20, 102) (63, 39) (127, 128)
		
		stage.addActor(menuFrame);
		stage.addActor(classSpheres[0]);
		//stage.addActor(classSpheres[1]);
		System.out.println("Stage height: " + stage.getHeight() + "Stage width: " + stage.getWidth());
		
	}

	@Override
	public void render(float delta) {
		game.batch.begin();
		game.batch.draw(background, 0, 0); //background texture, native 1080p
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
		stage.dispose();
		menuMusic.dispose();
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

}
