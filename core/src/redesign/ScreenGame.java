package redesign;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import redesign.components.PositionComponent;
import redesign.components.SpriteComponent;
import redesign.systems.RenderSystem;

public class ScreenGame extends ScreenAdapter {
	
	private SpriteBatch batch;
	
	private Engine engine;
	private RenderSystem renderSystem;
	
	private Entity player;
	private Texture cursor;
	
	public ScreenGame() {
		batch = new SpriteBatch();
		cursor = new Texture(Gdx.files.internal("images/Cursor.png"));
		
		engine = new Engine();
		
		renderSystem = new RenderSystem(1, batch);
		
		engine.addSystem(renderSystem);
		
		engine.addEntity(getMonster(0, 0));
		engine.addEntity(getMonster(1, 0));
		engine.addEntity(getMonster(0, 1));
		engine.addEntity(getMonster(-1, 0));
		engine.addEntity(getMonster(0, -1));
		engine.addEntity(getMonster(0, -2));
		engine.addEntity(getMonster(0, -3));
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(.0f, .0f, .0f, 1.0f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(delta);
	}

	@Override
	public void dispose() {
		cursor.dispose();
	}

	@Override
	public void resize(int width, int height) {
		renderSystem.getViewport().update(width, height);
		renderSystem.getViewport().getCamera().update();
	}

	private Entity getMonster(int x, int y) {
		Entity ent = new Entity();
		
		SpriteComponent sprite = new SpriteComponent();
		PositionComponent position = new PositionComponent();
		
		sprite.texture = new TextureRegion(cursor);
		position.x = x;
		position.y = y;
		
		ent.add(sprite);
		ent.add(position);
		
		return ent;
	}
	
	
	
	
	
}
