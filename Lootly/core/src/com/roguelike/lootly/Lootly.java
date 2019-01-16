package com.roguelike.lootly;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.Gdx;

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
		
		newCursor("Cursor (2).png");//calls new cursor looking for this 32x32 png
	}
	
	public void render() {
		super.render(); //important!
	}

	public void newCursor(String path) {//create and set new cursor from assets
		Pixmap pm = new Pixmap(Gdx.files.internal(path));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth() / 2, pm.getHeight() / 2));
		pm.dispose();
	}
	
	
}
