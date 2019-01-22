package com.roguelike.lootly.item;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class ItemConsumable extends Item {
	
	int quantity; //quantity of the item
	int maxQuantity; //the maximum quantity of an item that can be held in one slot
	int cooldown; //the time the user must wait between consecutive item uses
	long lastUse; //the last time the item was used. Ideally taken from a remote clock to prevent exploitations.
	boolean full; //indicates that the item cannot hold any more (quantity = maxQuantity)
	boolean empty = false; //a flag that will cause the game to delete the object from whatever inventory it is placed inside of if set to true

	//Should rarely be used. Only for testing and quickly instantiating a basic ItemConsumable
	public ItemConsumable() {
	}
	
	
	public ItemConsumable(String name, String flavorText, int id, String spritePath) {
		super(name, flavorText, id, spritePath);
		
		this.maxQuantity = 1;
		this.cooldown = 0;
	}
	
	//should be used most often, as it offers the largest degree of control on instantiation
	public ItemConsumable(String name, String flavorText, int id, String spritePath, int quantity, int maxQuantity, int cooldown) {
		super(name, flavorText, id, spritePath);
		this.quantity = quantity;
		this.maxQuantity = maxQuantity;
		this.cooldown = cooldown;
	}
	
	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		
		//check for if the item's quantity drops to, or below, zero
		if (quantity <= 0) {
			setEmpty(true);
		}
		
		this.quantity = quantity;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	
	//the function that is called when the user tries to use the item. DO NOT OVERRIDE UNLESS SPECIAL CIRCUMSTANCES NECESSITATE IT
	public void consume() {
		
		if (getCurrentTime() - lastUse >= cooldown && !isEmpty() ) {//checks if the cooldown has completed
			lastUse = getCurrentTime(); //a concession for optimization. Ideally, a new long containing the current time would be stored at the first line of Consume(), but constantly allocating storage on call for a long would undoubtably cause some leaks.
			
			try {
				execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			setQuantity(getQuantity() - 1); //decrement the item's quantity
		}
		
	}
	
	//MEANT TO BE OVERRIDDEN. Executes the effects of the item upon consumption
	public void execute() throws Exception {
		throw new Exception("Need to override @execute for" + getName() + "'s class!");
	}
	
	//retrieves the current time. Should get time via a remote clock, but can default to using local time.
	public long getCurrentTime() {
		return 0; //TODO: Compelte getCurrentTime function.
	}

}
