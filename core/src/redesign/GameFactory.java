package redesign;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;

import redesign.components.AnimationComponent;
import redesign.components.MapComponent;
import redesign.components.PositionComponent;

public final class GameFactory {
	
	private static AssetsManager assets;
	private static DatabaseManager database;
	
	public static Entity createMonster(String name, int x, int y) {
		Entity ent = new Entity();
		
		PositionComponent pc = new PositionComponent();
		AnimationComponent ac = new AnimationComponent();
		
		ac.animation = new Animation<TextureRegion>(0.025f, getTextures(name));
		pc.x = x;
		pc.y = y;
		
		ent.add(pc);
		ent.add(ac);
		
		return ent;
	}
	
	public static Entity createMap(String mapName) {
		Entity ent = new Entity();
		
		MapComponent map = new MapComponent();
		
		map.tiledMap = assets.getMap(mapName);
		map.tileLayer = (TiledMapTileLayer) map.tiledMap.getLayers().get("TileLayer");
		
		ent.add(map);
		
		return ent;
	}
	
	private static TextureRegion[] getTextures(String name) {
		TextureRegion[] regions = new TextureRegion[2];
		Array<String> data = database.getAnimation(name);
		int atlasWidth = assets.getAtlas().findRegion(data.get(0)).getRegionWidth();
		
		regions[0] = assets.getTile(data.get(0), Integer.valueOf(data.get(1)), Integer.valueOf(data.get(2)));
		regions[1] = assets.getTile(data.get(0), Integer.valueOf(data.get(1)) + atlasWidth, Integer.valueOf(data.get(2)));
		
		return regions;
	}
	
	public static void setAssetManager(AssetsManager assets) {
		GameFactory.assets = assets;
	}
	
	public static void setDatabaseManager(DatabaseManager database) {
		GameFactory.database = database;
	}
	
}
