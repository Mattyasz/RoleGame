package com.pzmatty.rolesandbox.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.pzmatty.rolesandbox.objects.entities.StaticEntity;

public class GraphicManager {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private ShapeRenderer shape;
	private boolean hasShapeRender = false;
	private Array<StaticEntity> entities;

	public GraphicManager(SpriteBatch batch, OrthographicCamera camera, boolean hasShapeRender) {
		this.entities = new Array<>();
		this.batch = batch;
		this.camera = camera;
		if (hasShapeRender) {
			hasShapeRender = true;
			this.shape = new ShapeRenderer();
		}
		configSprites();
	}

	public void configSprites() {
	}

	public void render(float delta) {
		// Aca el input

		camera.update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (StaticEntity ent : entities) {
			ent.draw(batch);
		}
		batch.end();

		if (hasShapeRender) {
			shape.setProjectionMatrix(camera.combined);
			shape.begin();
			shape.end();
		}
	}

	public void addEntity(StaticEntity entity) {
		entities.add(entity);
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

	public void dispose() {
		if (hasShapeRender)
			shape.dispose();
	}

}
