package redesign.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import redesign.components.PlayerComponent;
import redesign.components.TransformComponent;
import redesign.managers.Mapper;

public class PlayerInputSystem extends EntitySystem {
	
	Entity entity;
	
	@Override
	public void addedToEngine(Engine engine) {
		entity = engine.getEntitiesFor(Family.all(TransformComponent.class, PlayerComponent.class).get()).first();
	}

	@Override
	public void update(float deltaTime) {
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			TransformComponent transform = Mapper.transform.get(entity);
			transform.position.y += 1 * deltaTime;
		}
	}

	

}
