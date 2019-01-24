package com.roguelike.lootly.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
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
		
		//******* scale adjustments ********//
		itemImage.setScale(4f);
		nameLabel.setFontScale(1f);
		flavorLabel.setFontScale(0.5f);
		
		
		
		//****** origin adjustments *******//
		itemImage.setOrigin(Align.bottom);
		nameLabel.setOrigin(Align.bottom);
		flavorLabel.setOrigin(Align.bottom);
		
		//****** actor adding *************//
		addActor(itemImage);
		addActor(nameLabel);
		addActor(flavorLabel);
		center();
	}
	
	//updates the image
	public void update() {
		nameLabel.setText(item.getName());
		flavorLabel.setText(item.getFlavorText());
		itemImage.setDrawable(new TextureRegionDrawable(new TextureRegion(item.getSprite().getTexture())));
	}
	
	public void spritePos(float x, float y){
		setPosition(x, y);
		setBounds(getX(), getY(), getWidth(), getHeight());
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Label getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(Label nameLabel) {
		this.nameLabel = nameLabel;
	}

	public Label getFlavorLabel() {
		return flavorLabel;
	}

	public void setFlavorLabel(Label flavorLabel) {
		this.flavorLabel = flavorLabel;
	}

	public Image getItemImage() {
		return itemImage;
	}

	public void setItemImage(Image itemImage) {
		this.itemImage = itemImage;
	}

	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}
	
	
}
