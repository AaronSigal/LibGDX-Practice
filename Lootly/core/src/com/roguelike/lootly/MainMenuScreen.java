package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class MainMenuScreen implements Screen, InputProcessor {	
	final Lootly game;
	private Stage stage;
	
	Music menuMusic;  //background music: http://freemusicarchive.org/music/Rolemusic/The_Black_Dot/The_Black_Kitty
	
	float tw;
	float th;
	
	//takes place of "void create()"
	public MainMenuScreen(final Lootly game) {
		this.game = game;
		
		stage = new Stage(game.viewport);
		
		//image loading
		
		
		//audio loading
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
	}

	@Override
	public void show() {
		
		menuMusic.play();
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
		
		game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		game.batch.end();
		
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
