package com.roguelike.lootly.gameworld;

public enum WorldObjEnum {
	//used to identify IDs for tiles from tilemap
	//
	
	//WALL Tiles
	//
	WALL_TOP_VERT,// |
	WALL_TOP_HORZ,// --
	//
	WALL_BOT_HORZ,// --
	
	//
	WALL_TOP_CRN_UPR_RGT,// -|
	WALL_TOP_CRN_UPR_LFT,// |-
	WALL_TOP_CRN_LWR_RGT,// _|
	WALL_TOP_CRN_LWR_LFT,// |_
	//
	WALL_BOT_CRN_UPR_RGT,// -|
	WALL_BOT_CRN_UPR_LFT,// |-
	
	//PILLAR Tiles
	//ALL PILLARS are oriented -
	PLR_TOP,
	PLR_MID,
	PLR_BOT
	
	
}
