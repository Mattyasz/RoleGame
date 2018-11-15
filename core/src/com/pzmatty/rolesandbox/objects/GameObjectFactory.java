package com.pzmatty.rolesandbox.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.pzmatty.rolesandbox.managers.AssetsManager;
import com.pzmatty.rolesandbox.managers.DatabaseManager;
import com.pzmatty.rolesandbox.managers.TiledMapManager;
import com.pzmatty.rolesandbox.objects.entities.Character;
import com.pzmatty.rolesandbox.objects.entities.StaticEntity;
import com.pzmatty.rolesandbox.objects.entities.Trigger;
import com.pzmatty.rolesandbox.objects.entities.switchs.DoorSwitch;

public abstract class GameObjectFactory {

	private static Rectangle baseRect = new Rectangle(0, 0, 16 * TiledMapManager.WORLD_TO_SCREEN,
			16 * TiledMapManager.WORLD_TO_SCREEN);
	private static CollisionObject collisionObject = new CollisionObject();
	private static StaticEntity cursor = new StaticEntity(
			new TextureRegion(AssetsManager.get(DatabaseManager.getConstant("CURSOR"), Texture.class)),
			new Rectangle(baseRect), false, "CURSOR");

	private GameObjectFactory() {
	}

	public static Character createCharacter(String name, Rectangle rect) {
		if (name != null) {
			return new Character(AssetsManager.getAnimated(name, "CHAR"),
					new Rectangle(rect.x * TiledMapManager.WORLD_TO_SCREEN, rect.y * TiledMapManager.WORLD_TO_SCREEN,
							rect.width * TiledMapManager.WORLD_TO_SCREEN,
							rect.height * TiledMapManager.WORLD_TO_SCREEN));
		} else {
			return null;
		}
	}

	public static ISwitch createSwitch(String name, Rectangle rect, boolean block) {
		if (name.equals("Door")) {
			return new DoorSwitch(AssetsManager.getAnimated("Metal Door", "SWITCH"),
					new Rectangle(rect.x * TiledMapManager.WORLD_TO_SCREEN, rect.y * TiledMapManager.WORLD_TO_SCREEN,
							rect.width * TiledMapManager.WORLD_TO_SCREEN,
							rect.height * TiledMapManager.WORLD_TO_SCREEN),
					block);
		} else {
			return null;
		}
	}

	public static Trigger createTrigger(String event, Rectangle rect) {
		return new Trigger(new Rectangle(rect.x * TiledMapManager.WORLD_TO_SCREEN,
				rect.y * TiledMapManager.WORLD_TO_SCREEN, rect.width * TiledMapManager.WORLD_TO_SCREEN - 1,
				rect.height * TiledMapManager.WORLD_TO_SCREEN - 1), false, "TRIGGER", event);
	}

	public static CollisionObject getCollisionObject() {
		return collisionObject;
	}

	public static StaticEntity getCursor() {
		return cursor;
	}

}
