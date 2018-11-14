package com.pzmatty.rolesandbox.objects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.pzmatty.rolesandbox.managers.TiledMapManager;

public class AnimatedEntity extends Entity {

	private Animation<TextureRegion> animation;
	private float stateTime = 0f;
	private boolean animate;
	private int index;

	public AnimatedEntity(TextureRegion[] textures, Rectangle rect, boolean block, boolean animate, String type) {
		super(rect, block, type);
		this.animate = animate;
		this.index = 0;
		animation = new Animation<>(0.498f, textures);
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (animate) {
			stateTime += Gdx.graphics.getDeltaTime();
			TextureRegion key = animation.getKeyFrame(stateTime, true);
			batch.draw(key, rect.x, rect.y, key.getRegionWidth() * TiledMapManager.WORLD_TO_SCREEN,
					key.getRegionHeight() * TiledMapManager.WORLD_TO_SCREEN);
		} else {
			batch.draw(animation.getKeyFrames()[index], rect.x, rect.y,
					animation.getKeyFrames()[index].getRegionWidth() * TiledMapManager.WORLD_TO_SCREEN,
					animation.getKeyFrames()[index].getRegionHeight() * TiledMapManager.WORLD_TO_SCREEN);
		}
	}

	public void toogleAnimationKey() {
		if (index == 1)
			index = 0;
		else
			index = 1;
	}

	public void setAnimationKey(int index) {
		this.index = index;
	}

}
