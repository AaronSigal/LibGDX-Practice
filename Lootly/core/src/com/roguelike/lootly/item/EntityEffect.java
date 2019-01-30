package com.roguelike.lootly.item;

//All item effects are, by default, configurable. Any static effects must be produced some other way.
public abstract class EntityEffect implements ConfigurableEffect{
	private int id;// the id for lookup

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
