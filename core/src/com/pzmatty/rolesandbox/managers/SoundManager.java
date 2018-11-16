package com.pzmatty.rolesandbox.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public abstract class SoundManager {

	private static float effectsVolume = 0.25f;
	private static float musicVolume = 0.25f;
	
	public static void playSound(String name) {
		AssetsManager.get(DatabaseManager.getConstant(name), Sound.class).play(effectsVolume);
	}
	
	public static void playMusic(String name) {
		AssetsManager.get(DatabaseManager.getConstant(name), Music.class).setVolume(musicVolume);;
		AssetsManager.get(DatabaseManager.getConstant(name), Music.class).play();
	}
	
	public static void setEffectsVolume(float volume) {
		SoundManager.effectsVolume = volume;
	}
	
}
