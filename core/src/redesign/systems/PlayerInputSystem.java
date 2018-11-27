package redesign.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;

import redesign.components.InputComponent;
import redesign.components.PlayerComponent;
import redesign.components.TransformComponent;
import redesign.managers.InputController;
import redesign.managers.Mapper;

public class PlayerInputSystem extends EntitySystem {
	
	Entity entity;
	InputController controller;
	
	@Override
	public void addedToEngine(Engine engine) {
		entity = engine.getEntitiesFor(Family.all(TransformComponent.class, PlayerComponent.class, InputComponent.class).get()).first();
		controller = new InputController();
		Gdx.input.setInputProcessor(controller);
	}

	@Override
	public void update(float deltaTime) {
		InputComponent input = Mapper.input.get(entity);
		
		input.command = controller.command;
		
		if (input.command != null) {
			input.command.execute(entity);
			input.command = null;
			controller.command = null;
		}
	}
	
}
