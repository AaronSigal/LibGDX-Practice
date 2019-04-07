package com.roguelike.lootly.gameworld;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class WorldMap {
	public static void loadWorld(TiledMap map) {
		int width;
		int height;
		int id;
		TiledMapTileLayer.Cell cell;
		for (MapLayer layer : map.getLayers()) {
			if (layer instanceof TiledMapTileLayer) {
				width = ((TiledMapTileLayer) layer).getWidth();
				height = ((TiledMapTileLayer) layer).getHeight();
				for(int y = 0; y != height; y++) {
					for(int x = 0; x != width; x++) {
						cell = ((TiledMapTileLayer) layer).getCell(x,y);
						if(cell != null)
							id = cell.getTile().getId();
						else
							id = 0;
						System.out.print( id + " ");
					}
					System.out.println();
				}
			}
			System.out.println("layer done");
		}
	}
}
