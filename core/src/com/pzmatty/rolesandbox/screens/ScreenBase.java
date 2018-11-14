package com.pzmatty.rolesandbox.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pzmatty.rolesandbox.RoleSandbox;

public abstract class ScreenBase extends ScreenAdapter {

	protected RoleSandbox game;

	public ScreenBase(RoleSandbox game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		super.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public abstract AssetManager getAssets();

	public abstract SpriteBatch getBatch();

}
