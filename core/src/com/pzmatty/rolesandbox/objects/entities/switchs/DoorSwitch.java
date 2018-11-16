package com.pzmatty.rolesandbox.objects.entities.switchs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.pzmatty.rolesandbox.managers.SoundManager;
import com.pzmatty.rolesandbox.objects.ISwitch;
import com.pzmatty.rolesandbox.objects.entities.AnimatedEntity;

public class DoorSwitch extends AnimatedEntity implements ISwitch {

	public DoorSwitch(TextureRegion[] textures, Rectangle rect, boolean block, String name) {
		super(textures, new Rectangle(rect.x, rect.y, rect.width, rect.height), true, false, name);
		act();
	}

	@Override
	public void act() {
		if (block) {
			setAnimationKey(0);
		} else {
			setAnimationKey(1);
			SoundManager.playSound("DOOR_OPEN");
		}
	}

	@Override
	public void toogle() {
		block = !block;
		act();
	}

}
