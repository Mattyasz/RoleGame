package redesign.managers;

import com.badlogic.ashley.core.ComponentMapper;

import redesign.components.AnimationComponent;
import redesign.components.InputComponent;
import redesign.components.TextureComponent;
import redesign.components.TransformComponent;

public abstract class Mapper {

	public static final ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
	public static final ComponentMapper<TextureComponent> sprite = ComponentMapper.getFor(TextureComponent.class);
	public static final ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
	public static final ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
	public static final ComponentMapper<InputComponent> input = ComponentMapper.getFor(InputComponent.class);
	
}
