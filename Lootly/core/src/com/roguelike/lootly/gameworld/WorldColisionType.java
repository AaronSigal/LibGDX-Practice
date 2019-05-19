package com.roguelike.lootly.gameworld;

public enum WorldColisionType {
	//8 of 16 possible Categories used
	//binary representation not available in java 1.8 so Hex used
    CATEGORY_PLAYER_ENTITY		((short)(0x0001)),//0b 0000 0000 0000 0001
    CATEGORY_PLAYER_PROJECTILE	((short)(0x0002)),//0b 0000 0000 0000 0010
    CATEGORY_CREEP_ENTITY		((short)(0x0004)),//0b 0000 0000 0000 0100
    CATEGORY_CREEP_PROJECTILE	((short)(0x0008)),//0b 0000 0000 0000 1000
    CATEGORY_WORLD_STATIC		((short)(0x0010)),//0b 0000 0000 0001 0000
    CATEGORY_WORLD_DESTRUCTABLE	((short)(0x0020)),//0b 0000 0000 0010 0000
    CATEGORY_WORLD_INTERACTABLE	((short)(0x0040)),//0b 0000 0000 0100 0000
    CATEGORY_INTANGIBLE			((short)(0x0080)),//0b 0000 0000 1000 0000
	
    //For Mask: bits set to 1 are corresponding categories which something of that mask can collide with
    MASK_PLAYER_ENTITY			((short)(0x0058)),//0b 0000 0000 0101 1000
    MASK_PLAYER_PROJECTILE		((short)(0x0034)),//0b 0000 0000 0011 0100
    MASK_CREEP_ENTITY			((short)(0x0012)),//0b 0000 0000 0001 0010
    MASK_CREEP_PROJECTILE		((short)(0x0031)),//0b 0000 0000 0011 0001
    MASK_WORLD_STATIC			((short)(0x0000)),//0b 0000 0000 0000 0000 Collision not yet set 
    MASK_WORLD_DESTRUCTABLE		((short)(0x0000)),//0b 0000 0000 0000 0000 Collision not yet set 
    MASK_WORLD_INTERACTABLE		((short)(0x0000)),//0b 0000 0000 0000 0000 Collision not yet set 
    MASK_INTANGIBLE				((short)(0x0000));//0b 0000 0000 0000 0000 Collision not yet set 
	
	
	private short type;
	
	public short getType() {
		return this.type;
	}
	
	private WorldColisionType(short type) {
		this.type = type;
	}
}
