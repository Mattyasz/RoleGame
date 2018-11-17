package com.pzmatty.rolesandbox.objects.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.pzmatty.rolesandbox.objects.GameObject;

public abstract class Entity extends GameObject {

	protected String name;

	public Entity(Rectangle rect, boolean block, String name) {
		super(rect, block);
		this.name = name;
	}

	public abstract void draw(SpriteBatch batch);

	public String getName() {
		return name;
	}

}
