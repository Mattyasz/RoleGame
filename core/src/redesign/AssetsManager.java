package redesign;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public final class AssetsManager {

	private static final String TAG = AssetsManager.class.getSimpleName();
	
	private static AssetManager assets = new AssetManager();
	
	public static Texture getTexture(String fileName) {
		if (assets.isLoaded(fileName, Texture.class)) {
			return assets.get(fileName, Texture.class);			
		} else {
			try {
				assets.load(fileName, Texture.class);
				assets.finishLoadingAsset(fileName);
				return assets.get(fileName, Texture.class);	
			} catch (Exception exc) {
				Gdx.app.log(TAG, exc.getMessage());
				return null;
			}
		}
	}
	
	public static void unload() {
		assets.dispose();
	}
	
}
