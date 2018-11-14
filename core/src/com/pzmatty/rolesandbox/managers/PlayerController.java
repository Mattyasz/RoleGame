package com.pzmatty.rolesandbox.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.pzmatty.rolesandbox.objects.entities.Entity;

public class PlayerController extends InputAdapter {

	public static enum PlayerState {
		PLAYER, CURSOR
	}

	private Entity entity;
	private TiledMapManager tilemap;
	private PlayerState state;

	public PlayerController(TiledMapManager tilemap, Entity entity) {
		this.entity = entity;
		this.tilemap = tilemap;
		this.state = PlayerState.PLAYER;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (state.equals(PlayerState.PLAYER)) {
			evaluateMap(keycode);
			return true;
		} else if (state.equals(PlayerState.CURSOR)) {
			return true;
		} else {
			return true;
		}
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
		}

		// Check if collides with the CollisionLayer
		if (!tilemap.collides(entity, entity.getMovePosition(x, y))) {
			// Check if collides with entities and act
			if (!tilemap.checkEntities(entity, entity.getMovePosition(x, y))) {
				// Evaluate trigger OnEnter and act
				tilemap.triggerOnEnter(entity.getMovePosition(x, y));
				// Evaluate trigger OnExit and act
				tilemap.triggerOnExit(entity.getMovePosition(x, y), entity.getPosition());
				// Moves the player
				entity.translate(new Vector2(x, y));
				// Update the camera
				tilemap.setCameraPosition(entity.getPosition());
				tilemap.getCamera().update();
			}
		}
	}

}
