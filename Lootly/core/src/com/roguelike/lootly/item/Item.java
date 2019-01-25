package com.roguelike.lootly.item;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Item implements Cloneable{
	Sprite sprite;
	String spritePath; //spritePath to be displayed
	String name; //name for item
	String flavorText; //flavor text to be shown when applicable. Limit to 120 characters or fewer.
	int id;// id of the item to be used for index and lookup purposes internally
	int tier; //tier of the item. The higher the better. This value can be used for scaling effects according to item rarity
	
	
	//Generic constructor for a quick instantiation
	public Item() {
		
	}
	
	//Main constructor, intended to be used most often.
	public Item(String name, String flavorText, String spritePath, int id, int tier) {
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

	public Item clone() {
		Item clone = null;
		try {
			clone = (Item)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
	
}
