package com.roguelike.lootly;

import java.util.HashMap;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rits.cloning.Cloner;
import com.roguelike.lootly.io.LoadContext;
import com.roguelike.lootly.io.StrategyLoadItemList;
import com.roguelike.lootly.item.Item;
import com.roguelike.lootly.item.ItemEffects.ConfigurableEffect;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.Gdx;

public class Lootly extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;
	public Viewport viewport_game;
	public Viewport viewport_ui;
	public Camera camera;
	public static HashMap<Integer, Item> itemList = new HashMap<Integer, Item>();
	public static HashMap<Integer, ConfigurableEffect> effectList = new HashMap<Integer, ConfigurableEffect>();
	public static Cloner cloner = new Cloner();
	
	final static float RENDER_WIDTH = 1920;
	final static float RENDER_HEIGHT = 1080;

	@Override
	public void create() {
		Gdx.graphics.setResizable(false);
		batch = new SpriteBatch();
		font = new BitmapFont();
		viewport_game = new FitViewport(RENDER_WIDTH, RENDER_HEIGHT);
		viewport_ui = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); viewport_ui.apply(true); // true = center camera
		newCursor("data/gui/cursor.png");//calls new cursor looking for this 32x32 png
		
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
		LoadContext loadManager = new LoadContext(new StrategyLoadItemList());
		loadManager.execute();
		
	}
	
	
}
