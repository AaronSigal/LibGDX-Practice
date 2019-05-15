package com.roguelike.lootly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Align;
import com.roguelike.lootly.gui.EscapeMenu;
import com.roguelike.lootly.gui.OptionsGear;
import com.roguelike.lootly.gui.ToggleButton;
import com.roguelike.lootly.gui.ItemDisplayBox;
import com.roguelike.lootly.gui.MenuStrategy;

public class GameScreen implements Screen, InputProcessor {
	final Lootly game;
	private Stage gameStage;//stage that contains all the game elements
	private Stage uiStage;//stage that contains all the UI elements
	ItemDisplayBox itemBox = new ItemDisplayBox(Lootly.cloner.deepClone(Lootly.itemList.get(0))); //TODO: Remove debugging object
	ItemDisplayBox itemBoxClone = new ItemDisplayBox(Lootly.cloner.deepClone(Lootly.itemList.get(1))); //TODO: Remove debugging object
	ToggleButton tb = new ToggleButton();
	OptionsGear escapeMenuGear;
	EscapeMenu escapeMenu;
	InputMultiplexer multiplexer;
	
	//Scales
	final float ESCAPE_MENU_SCALE = 10f;
	final float ESCAPE_MENU_GEAR_SCALE = 4f;
	

	
	public GameScreen(Lootly game) {
		this.game = game;
		gameStage = new Stage(game.viewport_game);
		uiStage = new Stage(game.viewport_ui);
		
		//Add support for taking input from multiple stages at once
		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(gameStage);
		multiplexer.addProcessor(uiStage);
		
		//Enable the escape menu
		escapeMenu = new EscapeMenu(); escapeMenu.setVisible(false); escapeMenu.setTouchable(Touchable.disabled); escapeMenu.setOrigin(Align.center); escapeMenu.setScale(ESCAPE_MENU_SCALE);
		escapeMenuGear = new OptionsGear(escapeMenu); escapeMenuGear.setScale(ESCAPE_MENU_GEAR_SCALE); 
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(multiplexer);
		
		//Actor instantiation
		itemBox.setX(Gdx.graphics.getWidth()/2);
		itemBox.setY(Gdx.graphics.getHeight()/2);
		itemBoxClone.setX(Gdx.graphics.getWidth()/2 + 300);
		itemBoxClone.setY(Gdx.graphics.getHeight()/2);
		
		escapeMenuGear.setPosition(10, Gdx.graphics.getHeight() - 70);
		escapeMenu.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		//Actor staging
		gameStage.addActor(itemBox);
		gameStage.addActor(itemBoxClone);
		
		gameStage.addActor(new ToggleButton(new Texture("data/gui/word/back.png"), new Texture("data/gui/word/back.png"), new MenuStrategy() {

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				
			}
		}));
		
		uiStage.addActor(escapeMenu);
		uiStage.addActor(escapeMenuGear);
	}

	@Override
	public void render(float delta) {
		game.batch.setProjectionMatrix(game.viewport_game.getCamera().combined);
		
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		uiStage.act(delta);                  
		gameStage.act(delta);
		gameStage.draw();
		uiStage.draw();
		
		

	}

	@Override
	public void resize(int width, int height) {
		// 

	}

	@Override
	public void pause() {
		

	}

	@Override
	public void resume() {
		

	}

	@Override
	public void hide() {
		

	}

	@Override
	public void dispose() {
		

	}

	@Override
	public boolean keyDown(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		return false;
	}
	
	

}
