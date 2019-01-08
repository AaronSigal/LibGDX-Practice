package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

public class MainMenuScreen implements Screen, InputProcessor {	
	final Lootly game;
	
	Music menuMusic;  //background music: http://freemusicarchive.org/music/Rolemusic/The_Black_Dot/The_Black_Kitty
	Texture menuImage;//image for the class selection
	float tw;
	float th;
	
	public MainMenuScreen(final Lootly game) {
		this.game = game;
		
		//image loading
		menuImage = new Texture(Gdx.files.internal("gui/main_menu_complete.png"));
		
		//audio loading
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
		
		tw = menuImage.getWidth();
        th = menuImage.getHeight();
	}

	@Override
	public void show() {
		
		menuMusic.play();
		menuImage.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	@Override
	public void render(float delta) {
		game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		game.batch.draw(menuImage, game.camera.position.x - (tw / 2), game.camera.position.y - (th / 2));
		game.batch.end();
		
		if (!menuMusic.isPlaying()) {
			menuMusic.play();
		}
		
	}

	@Override
	public void resize(int width, int height) {
		game.viewport.update(width, height);
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
