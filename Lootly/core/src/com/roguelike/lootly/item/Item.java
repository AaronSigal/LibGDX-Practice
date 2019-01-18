package com.roguelike.lootly.item;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Item {
	Sprite sprite; //sprite to be displayed
	String name; //name for item
	int id;// id of the item to be used for index and lookup purposes internally
	
	
	//Generic constructor for a quick instantiation
	public Item() {
		
	}
	
	//Main constructor, intended to be used most often.
	public Item(String name, int id, Sprite sprite) {
		this.name = name;
		this.id = id;
		this.sprite = sprite;
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

}
