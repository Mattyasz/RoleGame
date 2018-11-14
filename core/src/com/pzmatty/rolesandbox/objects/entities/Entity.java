package com.pzmatty.rolesandbox.objects.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pzmatty.rolesandbox.objects.GameObject;

public abstract class Entity extends GameObject {

	public Entity(Rectangle rect, boolean block, String type) {
		super(rect, block, type);
	}

	public abstract void draw(SpriteBatch batch);

}
