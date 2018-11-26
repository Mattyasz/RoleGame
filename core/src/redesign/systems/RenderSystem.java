package redesign.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import redesign.components.TextureComponent;
import redesign.components.TransformComponent;
import redesign.managers.Mapper;

public class RenderSystem extends EntitySystem {

	private static final float ASPECT_RATIO = Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
	private static final float WORLD_WIDTH = 32;
	private static final float WORLD_HEIGHT = 18;
	private static final float WORLD_UNIT = 16;
	private static final float WORLD_TO_SCREEN = 1.0f / WORLD_UNIT;

	private ImmutableArray<Entity> entities;
	private Entity map;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private FitViewport viewport;

	public RenderSystem(SpriteBatch batch) {
		super();
		this.batch = batch;
		camera = new OrthographicCamera();
		viewport = new FitViewport(WORLD_WIDTH * ASPECT_RATIO, WORLD_HEIGHT, camera);
	}

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(
				Family.all(TransformComponent.class).one(TextureComponent.class).get());
		// map = engine.getEntitiesFor(Family.all(MapComponent.class).get()).first();
	}

	@Override
	public void update(float deltaTime) {
		// Draw map

		// Draw Game Objects
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Entity entity : entities) {
			TransformComponent transform = Mapper.transform.get(entity);
			TextureComponent tc = Mapper.texture.get(entity);
			if (tc.region != null && transform.isVisible) {
				batch.draw(tc.region, // Texture
						(transform.position.x * WORLD_TO_SCREEN) * WORLD_UNIT, // Position X
						(transform.position.y * WORLD_TO_SCREEN) * WORLD_UNIT, // Position Y
						0, 0, // Origin X and Y
						transform.width * WORLD_TO_SCREEN, transform.height * WORLD_TO_SCREEN, // Width and Height
						transform.scale.x, transform.scale.y, // Scale
						transform.rotation); // Rotation				
			}
		}
		batch.end();
	}

	public Viewport getViewport() {
		return viewport;
	}

}
