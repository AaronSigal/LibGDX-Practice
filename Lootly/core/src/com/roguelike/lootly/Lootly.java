package com.roguelike.lootly;

import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rits.cloning.Cloner;
import com.roguelike.lootly.io.LoadContext;
import com.roguelike.lootly.io.StrategyLoadItems;
import com.roguelike.lootly.item.Item;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.Gdx;

public class Lootly extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;
	public Viewport viewport;
	public Camera camera;
	public static HashMap<Integer, Item> itemList = new HashMap();
	public static Cloner cloner = new Cloner();
	
	final static float RENDER_WIDTH = 1920;
	final static float RENDER_HEIGHT = 1080;

	@Override
	public void create() {
		Gdx.graphics.setResizable(false);
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera(RENDER_WIDTH, RENDER_HEIGHT);
		viewport = new FitViewport(RENDER_WIDTH, RENDER_HEIGHT, camera);
		newCursor("gui/cursor.png");//calls new cursor looking for this 32x32 png
		
		init();
		
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render(); //important!
	}
	
	public void startGameScreen() {
		setScreen(new GameScreen(this));
		System.out.println("Switched to gamescreen");
	}

	public void newCursor(String path) {//create and set new cursor from assets
		Pixmap pm = new Pixmap(Gdx.files.internal(path));
		Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth() / 2, pm.getHeight() / 2));
		pm.dispose();
	}
	
	public void init() {
		LoadContext loadManager = new LoadContext(new StrategyLoadItems());
		loadManager.execute();
		
	}
	
	
}
