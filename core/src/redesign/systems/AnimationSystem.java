package redesign.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import redesign.components.AnimationComponent;
import redesign.components.TextureComponent;
import redesign.managers.Mapper;

public class AnimationSystem extends IteratingSystem {

	public AnimationSystem() {
		super(Family.all(TextureComponent.class, AnimationComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		AnimationComponent ac = Mapper.animation.get(entity);
		TextureComponent tc = Mapper.texture.get(entity);
		
		tc.region = ac.animation.getKeyFrame(ac.stateTime, true);
		ac.stateTime += deltaTime;
	}

	
	
}
