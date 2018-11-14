package com.pzmatty.rolesandbox.objects.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class StaticEntity extends Entity {

	private TextureRegion texture;

	public StaticEntity(TextureRegion texture, Rectangle rect, boolean block, String type) {
		super(rect, block, type);
		this.texture = texture;
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(texture, rect.x, rect.y);
	}

}
