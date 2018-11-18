package com.pzmatty.rolesandbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.kotcrab.vis.ui.VisUI;
import com.pzmatty.rolesandbox.managers.AssetsManager;
import com.pzmatty.rolesandbox.managers.DatabaseManager;
import com.pzmatty.rolesandbox.screens.ScreenGame;

public class RoleSandbox extends Game {

	public enum ScreenState {
		GAME
	}

	private ScreenGame screenGame;
	private SpriteBatch batch;
	private ScreenState state;

	@Override
	public void create() {
		DatabaseManager.connect();

		AssetsManager.loadAssets();
		
		VisUI.load(AssetsManager.get(DatabaseManager.getConstant("SKIN_PATH"), Skin.class));

		batch = new SpriteBatch();

		screenGame = new ScreenGame(this);

		setScreen(screenGame);
	}

	@Override
	public void dispose() {
		AssetsManager.unloadAssets();
		VisUI.dispose();
		batch.dispose();
		screenGame.dispose();
		DatabaseManager.disconnect();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Screen getScreen(String name) {
		if (name.equals("ScreenGame")) {
			return this.screenGame;
		} else
			return null;
	}

	public ScreenState getState() {
		return state;
	}

	public void setScreen(ScreenState screen) {
		if (screen.equals(ScreenState.GAME)) {
			setState(ScreenState.GAME);
			setScreen(screenGame);
		}
	}

	public void setState(ScreenState state) {
		this.state = state;
	}

}
