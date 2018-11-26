package redesign.commands;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import redesign.components.TransformComponent;

public class MoveCommand extends Command {
	
	public Vector2 movement;
	
	public MoveCommand(Vector2 movement) {
		this.movement = movement;
	}
	
	@Override
	public void execute(Entity entity) {
		TransformComponent transform = entity.getComponent(TransformComponent.class);
		transform.position.add(movement);
	}

}
