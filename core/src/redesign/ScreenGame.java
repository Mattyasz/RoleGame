package redesign;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
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
		
		engine.addEntity(getMonster());
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
	
	private Entity getMonster() {
		Entity ent = new Entity();
		
		SpriteComponent sc = new SpriteComponent();
		PositionComponent pc = new PositionComponent();
		
		sc.texture = new TextureRegion(cursor);
		pc.x = 0;
		pc.y = 0;
		
		ent.add(sc);
		ent.add(pc);
		
		return ent;
	}
	
	
	
	
	
}
