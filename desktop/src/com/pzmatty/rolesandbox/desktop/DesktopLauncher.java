package com.pzmatty.rolesandbox.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

import redesign.LostLib;

public class DesktopLauncher {
	
	private static boolean rebuildAtlas = true;
	private static boolean drawDebugOutline = true;
	
	public static void main (String[] arg) {
		
		if (rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 2048;
			settings.maxHeight = 2048;
			//settings.pot = true;
			//settings.duplicatePadding = false;
			//settings.debug = drawDebugOutline;
			if (TexturePacker.isModified("maps/tilesets", "images", "textures.pack", settings)) {
				TexturePacker.process(settings, "maps/tilesets", "images", "textures.pack");				
			}
		}
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 600;
		config.depth = 32;
		config.useGL30 = false;
		new LwjglApplication(new LostLib(), config);
	}
}
