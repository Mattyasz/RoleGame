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

import redesign.components.AnimationComponent;
import redesign.components.Mapper;
import redesign.components.PositionComponent;
import redesign.components.SpriteComponent;

public class RenderSystem extends EntitySystem {
	
	private static final float ASPECT_RATIO = Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
	private static final float WORLD_WIDTH = 16;
	private static final float WORLD_HEIGHT = 9;
	private static final float WORLD_UNIT = 16;
	private static final float WORLD_TO_SCREEN = 1.0f / WORLD_UNIT;
	
	private ImmutableArray<Entity> entities;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private FitViewport viewport;

	public RenderSystem(int priority, SpriteBatch batch) {
		super(priority);
		this.batch = batch;
		camera = new OrthographicCamera();
		viewport = new FitViewport(WORLD_WIDTH * ASPECT_RATIO, WORLD_HEIGHT, camera);
	}

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family
				.all(PositionComponent.class)
				.one(SpriteComponent.class, AnimationComponent.class)
				.get());
	}

	@Override
	public void update(float deltaTime) {
		camera.update();
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Entity entity : entities) {
			PositionComponent position = Mapper.position.get(entity);
			SpriteComponent sprite = Mapper.sprite.get(entity);
			AnimationComponent animation = Mapper.animation.get(entity);
			
			if (animation == null) {
				batch.draw(sprite.texture,
						(position.x * WORLD_TO_SCREEN) * WORLD_UNIT, (position.y * WORLD_TO_SCREEN) * WORLD_UNIT,
						0, 0,
						16 * WORLD_TO_SCREEN, 16 * WORLD_TO_SCREEN,
						1.0f, 1.0f,
						0.0f);
			} else {
				batch.draw(animation.animation.getKeyFrame(deltaTime), position.x, position.y);
			}
		}
		batch.end();
	}
	
	public Viewport getViewport() {
		return viewport;
	}
	
}
