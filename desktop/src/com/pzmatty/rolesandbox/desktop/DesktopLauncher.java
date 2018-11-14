package com.pzmatty.rolesandbox.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pzmatty.rolesandbox.RoleSandbox;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 600;
		config.depth = 32;
		config.useGL30 = false;
		new LwjglApplication(new RoleSandbox(), config);
	}
}
