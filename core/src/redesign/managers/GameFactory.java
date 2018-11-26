package redesign.managers;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.utils.Array;

import redesign.components.AnimationComponent;
import redesign.components.MapComponent;
import redesign.components.PlayerComponent;
import redesign.components.TextureComponent;
import redesign.components.TransformComponent;

public final class GameFactory {
	
	private static AssetsManager assets;
	private static DatabaseManager database;
	
	public static Entity createMonster(String name, int x, int y) {
		Entity ent = new Entity();
		
		TransformComponent pc = new TransformComponent();
		AnimationComponent ac = new AnimationComponent();
		TextureComponent tc = new TextureComponent();
		
		ac.animation = new Animation<TextureRegion>(0.5f, getTextures(name));
		pc.position.set(x, y);
		pc.width = ac.animation.getKeyFrames()[0].getRegionWidth();
		pc.height = ac.animation.getKeyFrames()[0].getRegionHeight();
		
		ent.add(pc);
		ent.add(ac);
		ent.add(tc);
		
		return ent;
	}
	
	public static Entity setPlayer(Entity entity) {
		return entity.add(new PlayerComponent());
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
		int atlasWidth = (assets.getAtlas().findRegion(data.get(0)).getRegionWidth() / 2) / 16;
		
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
