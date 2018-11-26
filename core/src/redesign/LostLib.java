package redesign;

import com.badlogic.gdx.Game;

public class LostLib extends Game {

	private AssetsManager assets;
	private DatabaseManager database;
	
	ScreenGame screenGame;
	
	@Override
	public void create() {
		database = new DatabaseManager();
		database.connect();
		
		assets = new AssetsManager();
		assets.loadAtlas();
		assets.finishLoad();
		
		screenGame = new ScreenGame(this);
		setScreen(screenGame);
	}

	@Override
	public void dispose() {
		assets.unloadAtlas();
		database.disconnect();
	}

	public AssetsManager getAssets() {
		return assets;
	}

	public DatabaseManager getDatabase() {
		return database;
	}

}
