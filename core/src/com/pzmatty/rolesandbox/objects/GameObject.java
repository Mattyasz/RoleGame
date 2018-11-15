package com.pzmatty.rolesandbox.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {

	protected boolean block;
	protected Rectangle rect;
	private final String type;

	public GameObject(Rectangle rect, boolean block, String type) {
		this.block = block;
		this.rect = rect;
		this.type = type;
	}

	public void setPosition(Vector2 position) {
		rect.setPosition(position);
	}

	public void translate(Vector2 position) {
		rect.x += position.x;
		rect.y += position.y;
	}

	public Vector2 getPosition() {
		return new Vector2(rect.x, rect.y);
	}

	public Vector2 getMovedPosition(int x, int y) {
		return getPosition().cpy().add(x, y);
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public String getType() {
		return type;
	}

	public Rectangle getRect() {
		return rect;
	}

}
