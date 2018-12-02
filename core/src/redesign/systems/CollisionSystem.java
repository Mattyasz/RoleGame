package redesign.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import redesign.components.CollisionComponent;
import redesign.components.TransformComponent;

public class CollisionSystem extends IteratingSystem {

	public CollisionSystem() {
		super(Family.all(TransformComponent.class, CollisionComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// Collision? Game? Help.
	}
	
}
