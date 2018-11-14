package com.pzmatty.rolesandbox.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.pzmatty.rolesandbox.RoleSandbox;
import com.pzmatty.rolesandbox.managers.PlayerController;
import com.pzmatty.rolesandbox.managers.TiledMapManager;
import com.pzmatty.rolesandbox.managers.UIManager;
import com.pzmatty.rolesandbox.objects.GameObjectFactory;
import com.pzmatty.rolesandbox.objects.entities.Character;

public class ScreenGame extends ScreenAdapter {

	// private static final String TAG = ScreenBase.class.getSimpleName();
	private Character player;
	private TiledMapManager map;
	private UIManager ui;
	private PlayerController controller;
	private RoleSandbox game;

	public ScreenGame(RoleSandbox game) {
		this.game = game;
		map = new TiledMapManager(game.getBatch(), "MAP_01");
		// TODO Organize this.
		Vector2 playerPos = map.getSpawnPosition("Spawn.PlayerSpawn");
		Rectangle playerRect = new Rectangle(playerPos.x, playerPos.y, 16, 16);
		player = GameObjectFactory.createCharacter("Paesant", playerRect);
		map.setCameraPosition(player.getPosition());
		ui = configUI();
		ui.configActors();
		controller = new PlayerController(map, player);
		Gdx.input.setInputProcessor(controller);
		map.addEntity(player);
	}

	@Override
	public void show() {
		map.loadEntities();
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(.0f, .0f, .0f, 1.0f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		map.render(delta);
		ui.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		map.resize(width, height);
		// ui.resize(width, height);
	}

	@Override
	public void dispose() {
		map.dispose();
		ui.dispose();
	}

	private UIManager configUI() {
		return new UIManager(game.getBatch(), false) {

			@Override
			public void configActors() {

				VerticalGroup options = new VerticalGroup();
				Label lblLine = new Label("Label", skin, "default");
				List<String> varList = new List<>(skin);

				ScrollPane scrollOptions = new ScrollPane(options, skin);
				ScrollPane scrollVariables = new ScrollPane(varList, skin);

				table.setFillParent(true);

				table.setWidth(Gdx.graphics.getWidth());
				table.top();

				lblLine.setWrap(true);
				options.align(Align.topLeft).pad(5);
				options.columnLeft();

				table.add(lblLine).expandX().left().fillX().top().pad(10);
				table.add(scrollVariables).right().width(120).fillY().top().pad(10).expandY().height(250);
				table.row().height(100);
				table.add(scrollOptions).bottom().fillX().fillY().pad(10).colspan(2);

				stage.addActor(table);
			}

		};
	}

}
