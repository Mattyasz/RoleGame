package redesign;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import redesign.systems.RenderSystem;

public class ScreenGame extends ScreenAdapter {
	
	private SpriteBatch batch;
	
	private Engine engine;
	private RenderSystem renderSystem;
	
	public ScreenGame() {
		batch = new SpriteBatch();
		
		engine = new Engine();
		
		renderSystem = new RenderSystem(1, batch);
		
		engine.addSystem(renderSystem);
		
		engine.addEntity(EntityFactory.createMonster(0, 0));
		engine.addEntity(EntityFactory.createMonster(1, 0));
		engine.addEntity(EntityFactory.createMonster(0, 1));
		engine.addEntity(EntityFactory.createMonster(-1, 0));
		engine.addEntity(EntityFactory.createMonster(0, -1));
		engine.addEntity(EntityFactory.createMonster(0, -2));
		engine.addEntity(EntityFactory.createMonster(0, -3));
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(.0f, .0f, .0f, 1.0f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		engine.update(delta);
	}


	@Override
	public void resize(int width, int height) {
		renderSystem.getViewport().update(width, height);
		renderSystem.getViewport().getCamera().update();
	}

	@Override
	public void dispose() {
		AssetsManager.unload();
	}
	
	
}
