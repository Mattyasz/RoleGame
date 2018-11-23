package redesign.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import redesign.components.AnimationComponent;
import redesign.components.Mapper;
import redesign.components.PositionComponent;
import redesign.components.SpriteComponent;

public class RenderSystem extends EntitySystem {

	private ImmutableArray<Entity> entities;
	private SpriteBatch batch;

	public RenderSystem(int priority, SpriteBatch batch) {
		super(priority);
		this.batch = batch;
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
		batch.begin();
		for (Entity entity : entities) {
			PositionComponent position = Mapper.position.get(entity);
			SpriteComponent sprite = Mapper.sprite.get(entity);
			AnimationComponent animation = Mapper.animation.get(entity);
			
			if (animation == null) {
				batch.draw(sprite.texture, position.x, position.y);
			} else {
				batch.draw(animation.animation.getKeyFrame(deltaTime), position.x, position.y);
			}
		}
		batch.end();
	}

	
	
}
