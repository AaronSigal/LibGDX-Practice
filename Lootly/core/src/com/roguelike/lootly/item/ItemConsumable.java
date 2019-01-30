package com.roguelike.lootly.item;

import java.util.ArrayList;

public class ItemConsumable extends Item {
	
	int uses; //uses of the item
	int maxUses; //the maximum uses of an item that can be held in one slot
	int cooldown; //the time the user must wait between consecutive item uses
	long lastUse; //the last time the item was used. Ideally taken from a remote clock to prevent exploitations.
	boolean full; //indicates that the item cannot hold any more (uses = maxUses)
	boolean empty = false; //a flag that will cause the game to delete the object from whatever inventory it is placed inside of if set to true
	ArrayList<ConfigurableEffect> effects;

	//Should rarely be used. Only for testing and quickly instantiating a basic ItemConsumable
	public ItemConsumable() {
		effects = new ArrayList<ConfigurableEffect>();
	}
	
	
	public ItemConsumable(String name, String flavorText, String spritePath, int id, int tier) {
		super(name, flavorText, spritePath, id, tier);
		effects = new ArrayList<ConfigurableEffect>();
		this.maxUses = 1;
		this.cooldown = 0;
	}
	
	//should be used most often, as it offers the largest degree of control on instantiation
	public ItemConsumable(String name, String flavorText, String spritePath, int id, int tier, int uses, int maxUses, int cooldown) {
		super(name, flavorText, spritePath, id, tier);
		effects = new ArrayList<ConfigurableEffect>();
		this.uses = uses;
		this.maxUses = maxUses;
		this.cooldown = cooldown;
	}
	
	public void addEffect(ConfigurableEffect effect) {
		effects.add(effect);
	}
	
	public int getUses() {
		return uses;
	}

	public void setUses(int uses) {
		
		//check for if the item's uses drops to, or below, zero
		if (uses <= 0) {
			setEmpty(true);
		}
		
		this.uses = uses;
	}

	public int getMaxUses() {
		return maxUses;
	}

	public void setMaxUses(int maxUses) {
		this.maxUses = maxUses;
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
			
			setUses(getUses() - 1); //decrement the item's uses
		}
		
	}
	
	public void execute() throws Exception {
		
	}
	
	//retrieves the current time. Should get time via a remote clock, but can default to using local time.
	public long getCurrentTime() {
		return 0; //TODO: Complete getCurrentTime function.
	}

}
