package com.pzmatty.rolesandbox.objects.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Character extends AnimatedEntity {

	public Character(TextureRegion[] textures, Rectangle rect, boolean block, String name) {
		super(textures, rect, block, true, name);
	}

}
