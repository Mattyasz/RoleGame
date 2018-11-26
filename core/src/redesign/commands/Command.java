package redesign.commands;

import com.badlogic.ashley.core.Entity;

public abstract class Command {

	public abstract void execute(Entity entity);
	
}
