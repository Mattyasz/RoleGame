package redesign;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import redesign.components.PositionComponent;
import redesign.components.SpriteComponent;

public final class EntityFactory {
	
	public static Entity createMonster(int x, int y) {
		Entity ent = new Entity();
		
		SpriteComponent sprite = new SpriteComponent();
		PositionComponent position = new PositionComponent();
		
		sprite.texture = new TextureRegion(AssetsManager.getTexture("images/Cursor.png"));
		position.x = x;
		position.y = y;
		
		ent.add(sprite);
		ent.add(position);
		
		return ent;
	}
	
}
