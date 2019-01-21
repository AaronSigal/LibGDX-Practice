package com.roguelike.lootly.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.roguelike.lootly.item.Item;

public class ItemDisplayBox extends VerticalGroup {
	Item item; //the item that the box displays
	Label nameLabel;
	Label flavorLabel;
	Image itemImage;
	Skin skin = new Skin(Gdx.files.internal("gui/skin/LootlyV1/LootlyV1.json"));
	
	public ItemDisplayBox(Item item) {
		this.item = item;
		itemImage = new Image(item.getSprite());
		nameLabel = new Label(item.getName(), skin);
		flavorLabel = new Label(item.getFlavorText(), skin);
		
		addActor(itemImage);
		addActor(nameLabel);
		addActor(flavorLabel);
		center();
		
	}
	
	//method that draws the actor
}
