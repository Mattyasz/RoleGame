package com.pzmatty.rolesandbox.objects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.pzmatty.rolesandbox.objects.GameObject;

public class Trigger extends GameObject {

	private final String event;

	public Trigger(Rectangle rect, String event) {
		super(rect, false);
		this.event = event;
	}

	public void act() {
		if (event == "OnEnter")
			Gdx.app.log("Event: ", "OnEnter");
		else if (event == "OnExit")
			Gdx.app.log("Event: ", "OnExit");
	}

	public String getEvent() {
		return event;
	}

}
