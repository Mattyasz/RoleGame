package com.pzmatty.rolesandbox.managers;

import com.badlogic.gdx.utils.Array;
import com.pzmatty.rolesandbox.Map;
import com.pzmatty.rolesandbox.objects.entities.Monster;

public class GameState {

	private Array<Map> mapList;
	private Map actualMap;
	private Monster player;
	
	public GameState() {
		mapList = new Array<Map>();
	}
	
}
