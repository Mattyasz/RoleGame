package com.pzmatty.rolesandbox.managers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class UIManager {

	protected Stage stage;
	protected Table table;
	private Array<Actor> list;
	protected Skin skin;

	public UIManager(SpriteBatch batch, boolean debug) {
		this.skin = AssetsManager.get(DatabaseManager.getConstant("SKIN_PATH"), Skin.class);
		this.list = new Array<>();
		this.stage = new Stage(new ScreenViewport(), batch);
		this.table = new Table(skin);
		stage.setDebugAll(debug);
	}

	public UIManager(SpriteBatch batch, boolean debug, Viewport viewport) {
		this.skin = AssetsManager.get(DatabaseManager.getConstant("SKIN_PATH"), Skin.class);
		this.list = new Array<>();
		this.stage = new Stage(viewport, batch);
		this.table = new Table(skin);
		stage.setDebugAll(debug);
	}

	public void configActors() {
	}

	public Table getTable() {
		return table;
	}

	public Stage getStage() {
		return stage;
	}

	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		stage.getCamera().update();
	}

	public void dispose() {
		stage.dispose();
	}

	public void addActor(Actor actor, String name) {
		actor.setName(name);
		list.add(actor);
	}

	@SuppressWarnings("unchecked")
	public <T> T getActor(String name, Class<T> type) {
		for (Actor actor : list) {
			if (actor.getName() != null && actor.getName().equals(name)) {
				return (T) actor;
			}
		}
		return null;
		// for (Actor actor : stage.getActors()) {
		// if (actor.getName() != null && actor.getName().equals(name)) {
		// return actor;
		// } else if (actor instanceof Group) {
		// return ((Group) actor).findActor(name);
		// }
		// }
		// return null;
	}

}
