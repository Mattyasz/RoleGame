package com.pzmatty.rolesandbox.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.pzmatty.rolesandbox.objects.GameObject;
import com.pzmatty.rolesandbox.objects.GameObjectFactory;
import com.pzmatty.rolesandbox.objects.ISwitch;
import com.pzmatty.rolesandbox.objects.entities.Character;
import com.pzmatty.rolesandbox.objects.entities.Entity;
import com.pzmatty.rolesandbox.objects.entities.Trigger;

public class TiledMapManager {

	public static enum ActionState {
		PLAYER, CURSOR
	}

	private ActionState state;

	private static final String TAG = TiledMapManager.class.getSimpleName();
	public static final float WORLD_TO_SCREEN = 1 / 16f;
	public static final float WORLD_UNIT = 16;
	private static final float WORLD_WIDTH = 26;
	private static final float WORLD_HEIGHT = 16;
	public static final float ASPECT_RATIO = Gdx.graphics.getWidth() / Gdx.graphics.getHeight();

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private FitViewport viewport;

	private Array<Entity> entities;
	private Array<ISwitch> switchs;
	private Array<Trigger> triggers;

	private TiledMapTileLayer collisionLayer;
	private TiledMapTileLayer tileLayer;
	private TiledMapTileLayer decorationLayer;
	private MapObjects objectLayer;

	private Character player;

	public TiledMapManager(SpriteBatch batch, String mapName) {
		this.state = ActionState.PLAYER;
		this.entities = new Array<>();
		this.switchs = new Array<>();
		this.triggers = new Array<>();

		this.camera = new OrthographicCamera();
		this.viewport = new FitViewport(WORLD_WIDTH * ASPECT_RATIO, WORLD_HEIGHT, camera);

		this.map = AssetsManager.get(DatabaseManager.getConstant(mapName), TiledMap.class);
		this.renderer = new OrthogonalTiledMapRenderer(map, WORLD_TO_SCREEN, batch);

		this.batch = batch;

		collisionLayer = (TiledMapTileLayer) map.getLayers().get("CollisionLayer");
		tileLayer = (TiledMapTileLayer) map.getLayers().get("TileLayer");
		decorationLayer = (TiledMapTileLayer) map.getLayers().get("DecorationLayer");
		collisionLayer.setVisible(false);
		objectLayer = map.getLayers().get("ObjectLayer").getObjects();

		Vector2 playerPos = getSpawnPosition("Spawn.PlayerSpawn");
		Rectangle playerRect = new Rectangle(playerPos.x, playerPos.y, 16, 16);

		this.player = GameObjectFactory.createCharacter("Paesant", playerRect);
	}

	public void create() {
		setCameraPosition(player.getPosition());
		addEntity(player);
		loadEntities();
	}

	public Character getPlayer() {
		return player;
	}

	public void setPlayer(Character character) {
		player = character;
	}

	public Entity getEntity(int index) {
		if (index <= entities.size)
			return entities.get(index);
		else
			return entities.get(0);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public Viewport getViewport() {
		return viewport;
	}

	public void setState(ActionState state) {
		this.state = state;
	}

	public void render(float delta) {

		camera.update();
		// camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		renderer.setView(camera);
		renderer.render();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		if (state.equals(ActionState.CURSOR)) {
			GameObjectFactory.getCursor().draw(batch);
		}
		for (Entity ent : entities) {
			ent.draw(batch);
		}
		for (ISwitch sw : switchs) {
			((Entity) sw).draw(batch);
		}
		batch.end();
	}

	public void dispose() {
		map.dispose();
		renderer.dispose();
	}

	public void resize(int width, int height) {
		viewport.update((int) (width * ASPECT_RATIO), height);
		camera.update();
	}

	public void loadEntities() {
		for (MapObject object : objectLayer) {
			String[] parts = object.getName().split("[.]");
			RectangleMapObject rectangleObject = (RectangleMapObject) object;
			Rectangle rectangle = rectangleObject.getRectangle();
			if (parts[0].equals("Switch")) {
				if (parts[1].equals("Door")) {
					switchs.add(GameObjectFactory.createSwitch(parts[1], rectangle, true));
				}
			} else if (parts[0].equals("Char")) {
				entities.add(GameObjectFactory.createCharacter(parts[1], rectangle));
			} else if (parts[0].equals("Trigger")) {
				if (parts[1].equals("OnEnter")) {
					triggers.add(GameObjectFactory.createTrigger("OnEnter", rectangle));
				} else if (parts[1].equals("OnExit")) {
					triggers.add(GameObjectFactory.createTrigger("OnExit", rectangle));
				}
			}
		}
	}

	public void translateCamera(Vector2 vector) {
		camera.translate(vector);
	}

	public void setCameraPosition(Vector2 vector) {
		camera.position.set(vector.x + (TiledMapManager.WORLD_UNIT / 2) * TiledMapManager.WORLD_TO_SCREEN,
				vector.y + (TiledMapManager.WORLD_UNIT / 2) * TiledMapManager.WORLD_TO_SCREEN, 0.0f);
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public boolean checkObjects(Entity entity, Vector2 position) {
		Rectangle r = entity.getRect();
		r.setPosition(position.x, position.y);
		for (MapObject object : objectLayer) {
			if (object instanceof RectangleMapObject) {
				Rectangle rect = ((RectangleMapObject) object).getRectangle();
				if (r.overlaps(rect) && object.getProperties().get("collides", Boolean.class) == true) {
					Gdx.app.log(TAG, "Object collide");
					return true;
				}
			}
		}
		return false;
	}

	public boolean collides(Entity entity, Vector2 position) {
		Cell cell = collisionLayer.getCell((int) position.x, (int) position.y);
		if (cell != null) {
			TiledMapTile tile = cell.getTile();
			if (tile.getProperties().containsKey("collides")) {
				Gdx.app.log(TAG, "Block collide");
				return true;
			}
		}
		return false;
	}

	public boolean checkEntities(Entity entity, Vector2 position) {
		for (Entity other : entities) {
			if (other.getPosition().equals(position) && other.isBlock() && other != entity) {
				Gdx.app.log(TAG, "Character collide");
				return true;
			}
		}
		for (ISwitch other : switchs) {
			if (((GameObject) other).getPosition().equals(position) && ((GameObject) other).isBlock()
					&& other != entity) {
				other.toogle();
				Gdx.app.log(TAG, "Switch collide");
				return true;
			}
		}
		return false;
	}
	
	public String getTileInfo(Vector2 position) {
		for (Entity other : entities) {
			if (other.getPosition().equals(position)) {
				return other.getName();
			}
		}
		for (ISwitch other : switchs) {
			if (((GameObject) other).getPosition().equals(position)) {
				return ((Entity)other).getName();
			}
		}
		
		Cell cell = decorationLayer.getCell((int) position.x, (int) position.y);
		if (cell != null) {
			TiledMapTile tile = cell.getTile();
			return tile.getProperties().get("name").toString();
		} else {
			cell = tileLayer.getCell((int) position.x, (int) position.y);
			if (cell != null) {
				TiledMapTile tile = cell.getTile();
				return tile.getProperties().get("name").toString();				
			}
		} return null;
	}

	public void triggerOnEnter(Vector2 position) {
		for (Trigger trigger : triggers) {
			/*
			 * Gdx.app.log(TAG, "x: " + trigger.getPosition().x + ", y: " +
			 * trigger.getPosition().y + ", width: " + trigger.getRect().getWidth() +
			 * ", height: " + trigger.getRect().getHeight()); Gdx.app.log(TAG,
			 * "Player - x: " + position.x + ", y: " + position.y); Gdx.app.log(TAG,
			 * String.valueOf(trigger.getRect().contains(position)));
			 */
			if (trigger.getEvent().equals("OnEnter") && trigger.getRect().contains(position)) {
				trigger.act();
				break;
			}
		}
	}

	public void triggerOnExit(Vector2 position, Vector2 lastRect) {
		for (Trigger trigger : triggers) {
			if (trigger.getEvent().equals("OnExit") && !trigger.getRect().contains(position)
					&& trigger.getRect().contains(lastRect)) {
				trigger.act();
				break;
			}
		}
	}

	public Vector2 getSpawnPosition(String tag) {
		Vector2 position = new Vector2();
		if (objectLayer != null && objectLayer.get(tag) != null) {
			position.x = (float) objectLayer.get(tag).getProperties().get("x");
			position.y = (float) objectLayer.get(tag).getProperties().get("y");
		}
		return position;
	}

}
