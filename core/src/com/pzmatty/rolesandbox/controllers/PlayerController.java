package com.pzmatty.rolesandbox.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.pzmatty.rolesandbox.managers.SoundManager;
import com.pzmatty.rolesandbox.managers.TiledMapManager;
import com.pzmatty.rolesandbox.managers.TiledMapManager.ActionState;
import com.pzmatty.rolesandbox.objects.entities.Entity;
import com.pzmatty.rolesandbox.screens.ScreenGame;
import com.pzmatty.rolesandbox.ui.InfoGroupUI;
import com.pzmatty.rolesandbox.ui.InfoMonsterUI;

public class PlayerController extends InputAdapter {

	public static enum PlayerState {
		MOVEMENT
	}

	private Entity entity;
	private TiledMapManager tilemap;
	private PlayerState state;
	private ScreenGame game;

	public PlayerController(ScreenGame game, Entity entity) {
		this.entity = entity;
		this.game = game;
		this.tilemap = game.getTiledMap();
		this.state = PlayerState.MOVEMENT;
	}

	public void evaluateMap(int keycode) {
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
		case Keys.K:
			SoundManager.playSound("CURSOR_OPEN");
			game.getUI().getActor("Info", InfoGroupUI.class).setVisible(true);
			game.getUI().getActor("Monster", InfoMonsterUI.class).setVisible(true);
			Gdx.input.setInputProcessor(game.getCursorController().set(tilemap.getPlayer().getPosition()));
			tilemap.setState(ActionState.CURSOR);
		}

		if (x != 0 || y != 0) {
			// Check if collides with the CollisionLayer
			if (!tilemap.collides(entity, entity.getMovedPosition(x, y))) {
				// Check if collides with entities and act
				if (!tilemap.checkEntities(entity, entity.getMovedPosition(x, y))) {
					// Evaluate trigger OnEnter and act
					tilemap.triggerOnEnter(entity.getMovedPosition(x, y));
					// Evaluate trigger OnExit and act
					tilemap.triggerOnExit(entity.getMovedPosition(x, y), entity.getPosition());
					// Moves the player
					entity.translate(new Vector2(x, y));
					// Update the camera
					tilemap.setCameraPosition(entity.getPosition());
					tilemap.getCamera().update();
					SoundManager.playSound("STEP");
				}
			}
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		if (state.equals(PlayerState.MOVEMENT)) {
			evaluateMap(keycode);
			return true;
		} else {
			return true;
		}
	}

	public PlayerController set() {
		tilemap.setCameraPosition(entity.getPosition());
		tilemap.getCamera().update();
		return this;
	}

}
