package com.roguelike.lootly.gameworld;

public enum WorldColisionType {
    PLAYER_ENTITY((short)1),
    PLAYER_PROJECTILE((short)2),
    CREEP_ENTITY((short)3),
    CREEP_PROJECTILE((short)4),
    WORLD_STATIC((short)5),
    WORLD_DESTRUCTABLE((short)6),
    WORLD_INTERACTABLE((short)7),
    INTANGIBLE((short)8);
	
	private short type;
	
	public short getType() {
		return this.type;
	}
	
	private WorldColisionType(short type) {
		this.type = type;
	}
}
