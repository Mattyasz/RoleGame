package com.pzmatty.rolesandbox.objects.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class StaticEntity extends Entity {

	private TextureRegion texture;

	public StaticEntity(Texture texture, Rectangle rect, boolean block, String name) {
		super(rect, block, name);
		this.texture = new TextureRegion(texture);
	}

	public StaticEntity(TextureRegion texture, Rectangle rect, boolean block, String name) {
		super(rect, block, name);
		this.texture = texture;
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(texture, rect.x, rect.y, rect.width, rect.height);
	}

}
