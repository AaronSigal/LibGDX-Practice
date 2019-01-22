package com.roguelike.lootly.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Item {
	Sprite sprite;
	String spritePath; //spritePath to be displayed
	String name; //name for item
	String flavorText; //flavor text to be shown when applicable. Limit to 120 characters or fewer.
	int id;// id of the item to be used for index and lookup purposes internally
	
	
	//Generic constructor for a quick instantiation
	public Item() {
		
	}
	
	//Main constructor, intended to be used most often.
	public Item(String name, String flavorText, int id, String spritePath) {
		this.name = name;
		this.id = id;
		this.sprite = new Sprite(new Texture(spritePath));
		this.flavorText = flavorText;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlavorText() {
		return flavorText;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

	
}
