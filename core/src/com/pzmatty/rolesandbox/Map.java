package com.pzmatty.rolesandbox;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Array;
import com.pzmatty.rolesandbox.objects.ISwitch;
import com.pzmatty.rolesandbox.objects.entities.Character;
import com.pzmatty.rolesandbox.objects.entities.Entity;
import com.pzmatty.rolesandbox.objects.entities.Monster;
import com.pzmatty.rolesandbox.objects.entities.StaticEntity;
import com.pzmatty.rolesandbox.objects.entities.Trigger;

public class Map {

	private TiledMap map;
	private Array<Monster> monsters;
	private Array<Character> characters;
	private Array<ISwitch> switchs;
	private Array<Trigger> triggers;
	private Array<Entity> props;
	private Array<StaticEntity> items;
	private Array<StaticEntity> tiles;
	
	public Map(String mapName) {
		
	}
	
	public void dispose() {
		
	}
	
}
