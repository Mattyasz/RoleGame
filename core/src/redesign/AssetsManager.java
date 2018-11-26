package redesign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public final class AssetsManager {

	private static final String TAG = AssetsManager.class.getSimpleName();
	private static final String ATLAS_PATH = "images/textures.pack.atlas";
	private static final int TILE_SIZE = 16;

	private AssetManager assets;

	public AssetsManager() {
		assets = new AssetManager();
		assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
	}
	
	public void finishLoad() {
		assets.finishLoading();
	}
	
	public void loadMap(String fileName) {
		assets.load(fileName, TiledMap.class);
	}
	
	public void unloadMap(String fileName) {
		if (assets.isLoaded(fileName)) {
			assets.unload(fileName);
		} else {
			Gdx.app.log(TAG, fileName + " map is not loaded.");
		}
	}
	
	public TiledMap getMap(String fileName) {
		if (assets.isLoaded(fileName)) {
			return assets.get(fileName, TiledMap.class);
		} else {
			Gdx.app.log(TAG, fileName + " map is not loaded.");
			return null;
		}
	}

	public void loadAtlas() {
		assets.load(ATLAS_PATH, TextureAtlas.class);
	}

	public void unloadAtlas() {
		if (assets.isLoaded(ATLAS_PATH)) {
			assets.unload(ATLAS_PATH);
		} else {
			Gdx.app.log(TAG, ATLAS_PATH + " asset is not loaded.");
		}
	}
	
	public TextureAtlas getAtlas() {
		if (assets.isLoaded(ATLAS_PATH)) {
			return assets.get(ATLAS_PATH, TextureAtlas.class);
		} else {
			Gdx.app.log(TAG, ATLAS_PATH + " map is not loaded.");
			return null;
		}
	}

	public TextureRegion getTile(String tileset, int x, int y) {
		try {
			return new TextureRegion(assets.get(ATLAS_PATH, TextureAtlas.class).findRegion(tileset), x * TILE_SIZE,
					y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
		} catch (Exception exc) {
			Gdx.app.log(TAG, exc.getMessage());
			return null;
		}
	}

}
