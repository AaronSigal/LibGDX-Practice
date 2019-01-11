package com.roguelike.lootly;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Lootly extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;
	public Viewport viewport;
	public Camera camera;

	@Override
	public void create() {
		
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera(1920,1080);
		viewport = new ExtendViewport(1920,1080, camera);
		this.setScreen(new MainMenuScreen(this));
	}
	
	public void render() {
		super.render(); //important!
	}

	
	
	
}
