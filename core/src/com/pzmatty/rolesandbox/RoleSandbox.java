package com.pzmatty.rolesandbox;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

		batch = new SpriteBatch();

		screenGame = new ScreenGame(this);

		setScreen(screenGame);
	}

	@Override
	public void dispose() {
		AssetsManager.unloadAssets();
		batch.dispose();
		screenGame.dispose();
		DatabaseManager.disconnect();
	}

	public void setScreen(ScreenState screen) {
		if (screen.equals(ScreenState.GAME)) {
			setState(ScreenState.GAME);
			setScreen(screenGame);
		}
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

	public void setState(ScreenState state) {
		this.state = state;
	}

}
