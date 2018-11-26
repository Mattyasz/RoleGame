package redesign.components;

import com.badlogic.ashley.core.ComponentMapper;

public abstract class Mapper {

	public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
	public static final ComponentMapper<TextureComponent> sprite = ComponentMapper.getFor(TextureComponent.class);
	public static final ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
	
}
