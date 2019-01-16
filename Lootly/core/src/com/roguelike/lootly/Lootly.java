package com.roguelike.lootly;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Lootly extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;
	public Viewport viewport;
	public Camera camera;
	
	final static float RENDER_WIDTH = 1920;
	final static float RENDER_HEIGHT = 1080;

	@Override
	public void create() {
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera(RENDER_WIDTH, RENDER_HEIGHT);
		viewport = new FitViewport(RENDER_WIDTH, RENDER_HEIGHT, camera);
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); //important!
	}
	
	public void startGameScreen() {
		this.setScreen(new GameScreen(this));
	}

	
	
	
}
