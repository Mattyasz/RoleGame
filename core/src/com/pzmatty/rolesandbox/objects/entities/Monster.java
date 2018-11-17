package com.pzmatty.rolesandbox.objects.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Monster extends AnimatedEntity {

	// Monster stats
	private final int strength;
	private final int dextery;
	private final int constitution;
	private final int intelligence;
	private final int wisdom;
	private final int charisma;
	private final int baseHp;
	private final String race;
	private final String family;

	// Monster properties
	private int hp;

	public Monster(TextureRegion[] textures, Rectangle rect, boolean block, String name, Array<String> stats) {
		super(textures, rect, block, true, name);
		this.strength = Integer.parseInt(stats.get(0));
		this.dextery = Integer.parseInt(stats.get(1));
		this.intelligence = Integer.parseInt(stats.get(2));
		this.constitution = Integer.parseInt(stats.get(3));
		this.wisdom = Integer.parseInt(stats.get(4));
		this.charisma = Integer.parseInt(stats.get(5));
		this.baseHp = Integer.parseInt(stats.get(6));
		this.family = stats.get(7);
		this.race = stats.get(8);

		this.hp = baseHp;
	}

	public Array<String> getProperties() {
		Array<String> list = new Array<>();
		list.add("Name: " + name);
		list.add("HP: " + hp + "/" + baseHp);
		list.add("Race: " + race);
		list.add("Family: " + family);
		list.add("Strength: " + strength);
		list.add("Dexterity: " + dextery);
		list.add("Intelligence: " + intelligence);
		list.add("Constitution: " + constitution);
		list.add("Wisdom: " + wisdom);
		list.add("Charisma: " + charisma);
		return list;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getStrength() {
		return strength;
	}

	public int getDextery() {
		return dextery;
	}

	public int getConstitution() {
		return constitution;
	}

	public int getWisdom() {
		return wisdom;
	}

	public int getCharisma() {
		return charisma;
	}

	public int getBaseHp() {
		return baseHp;
	}

	public String getRace() {
		return race;
	}

	public String getFamily() {
		return family;
	}

}
