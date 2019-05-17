package com.roguelike.lootly.gameworld;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MyOrthoMap extends OrthogonalTiledMapRenderer {
	private Sprite sprite;
    private List<Sprite> sprites;
    private int drawSpritesAfterLayer = 1;

    public MyOrthoMap(TiledMap map) {
        super(map);
        sprites = new ArrayList<Sprite>();
    }

    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }
    
    public void removeSprite(Sprite sprite) {
    	sprites.remove(sprite);
    }
    
    public void clearSprites() {
    	sprites.clear();
    }

    @Override
    public void render() {
        beginRender();
        int currentLayer = 0;
        for (MapLayer layer : map.getLayers()) {
            if (layer.isVisible()) {
                if (layer instanceof TiledMapTileLayer) {
                    renderTileLayer((TiledMapTileLayer)layer);
                    currentLayer++;
                    if(currentLayer == drawSpritesAfterLayer){
                    	for(Sprite sprite : sprites)
                    		drawSprite(sprite);
                    }
                } else {
                    for (MapObject object : layer.getObjects()) {
                        renderObject(object);
                    }
                }
            }
        }
        endRender();
    }

	private void drawSprite(Sprite sprite) {
		batch.draw(sprite,    sprite.getX(), sprite.getY(),
						sprite.getOriginX(), sprite.getOriginY(),
						sprite.getWidth(),   sprite.getHeight(),
						sprite.getScaleX(),  sprite.getScaleY(),sprite.getRotation());
	}
	
}
