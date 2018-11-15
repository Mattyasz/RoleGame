package com.pzmatty.rolesandbox.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.pzmatty.rolesandbox.managers.TiledMapManager;
import com.pzmatty.rolesandbox.managers.TiledMapManager.ActionState;
import com.pzmatty.rolesandbox.managers.UIManager;
import com.pzmatty.rolesandbox.objects.GameObjectFactory;
import com.pzmatty.rolesandbox.objects.entities.Entity;
import com.pzmatty.rolesandbox.screens.ScreenGame;
import com.pzmatty.rolesandbox.ui.InfoGroupUI;

public class CursorController extends InputAdapter {

	private TiledMapManager tilemap;
	private Entity entity;
	private ScreenGame game;
	private UIManager ui;

	public CursorController(ScreenGame game) {
		this.game = game;
		this.tilemap = game.getTiledMap();
		this.ui = game.getUI();
		this.entity = GameObjectFactory.getCursor();
	}

	@Override
	public boolean keyDown(int keycode) {
		int x = 0, y = 0;

		switch (keycode) {
		case Keys.UP:
			y = 1;
			break;
		case Keys.LEFT:
			x = -1;
			break;
		case Keys.DOWN:
			y = -1;
			break;
		case Keys.RIGHT:
			x = 1;
			break;
		case Keys.NUMPAD_1:
			x = -1;
			y = -1;
			break;
		case Keys.NUMPAD_2:
			y = -1;
			break;
		case Keys.NUMPAD_3:
			x = 1;
			y = -1;
			break;
		case Keys.NUMPAD_4:
			x = -1;
			break;
		case Keys.NUMPAD_6:
			x = 1;
			break;
		case Keys.NUMPAD_7:
			x = -1;
			y = 1;
			break;
		case Keys.NUMPAD_8:
			y = 1;
			break;
		case Keys.NUMPAD_9:
			x = 1;
			y = 1;
			break;
		case Keys.ESCAPE:
			game.getUI().getActor("Info", InfoGroupUI.class).setInfo("");
			game.getUI().getActor("Info", InfoGroupUI.class).setVisible(false);
			Gdx.input.setInputProcessor(game.getPlayerController().set());
			tilemap.setState(ActionState.PLAYER);
			break;
		}

		if (x != 0 || y != 0) {
			entity.translate(new Vector2(x, y));
			tilemap.setCameraPosition(entity.getPosition());
			tilemap.getCamera().update();
		}

		if (keycode != Keys.ESCAPE)
			showTileInfo();

		return true;
	}

	private void showTileInfo() {
		String info = tilemap.getTileInfo(entity.getPosition());
		if (info == null) info = "Void";
		ui.getActor("Info", InfoGroupUI.class)
		.setInfo(info);
	}

	public CursorController set(Vector2 position) {
		this.entity.setPosition(position);
		showTileInfo();
		return this;
	}

}