package com.pzmatty.rolesandbox.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public abstract class AssetsManager {

	@SuppressWarnings("unused")
	private static final String TAG = AssetsManager.class.getSimpleName();
	private static AssetManager assets = new AssetManager();

	public static void loadAssets() {
		// LOAD LOADERS
		assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

		// LOAD ASSETS
		Array<String[]> assetsPath = DatabaseManager.getAssetsPath();
		for (String[] asset : assetsPath) {
			switch (asset[0]) {
			case "SKIN":
				assets.load(asset[1], Skin.class);
				break;
			case "MAP":
				assets.load(asset[1], TiledMap.class);
				break;
			case "TEXTURE":
				assets.load(asset[1], Texture.class);
				break;
			}
		}
		assets.finishLoading();
	}

	public static <T> T get(String fileName, Class<T> type) {
		return assets.get(fileName, type);
	}

	public static TextureRegion[] getAnimated(String name, String type) {
		Array<String> parts = DatabaseManager.getAnimationData(name, type);
		TextureRegion[] textures = new TextureRegion[2];
		Texture tileset = assets.get("maps/tilesets/" + parts.get(0) + ".png", Texture.class);
		for (int i = 0; i < textures.length; i++) {
			textures[i] = new TextureRegion(tileset,
					Integer.valueOf(parts.get(1)) * 16 + (i * (tileset.getWidth() / 2)),
					Integer.valueOf(parts.get(2)) * 16, 16, 16);
		}
		return textures;
	}

	public static void unloadAssets() {
		assets.dispose();
	}
	
	public static void unload(String fileName) {
		assets.unload(fileName);
	}

}
