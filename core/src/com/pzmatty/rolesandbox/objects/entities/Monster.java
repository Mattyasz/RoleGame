package com.pzmatty.rolesandbox.objects.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Monster extends AnimatedEntity {

	// Monster stats
	private String name;
	private String race;
	private String family;
	private int strength;
	private int dextery;
	private int constitution;
	private int wisdom;
	private int charisma;
	private int baseHp;
	private int hp;

	public Monster(TextureRegion[] textures, Rectangle rect, boolean block, boolean animate, String name) {
		super(textures, rect, block, animate, name);
	}

	public int getBaseHp() {
		return baseHp;
	}

	public int getCharisma() {
		return charisma;
	}

	public int getConstitution() {
		return constitution;
	}

	public int getDextery() {
		return dextery;
	}

	public String getFamily() {
		return family;
	}

	public int getHp() {
		return hp;
	}

	@Override
	public String getName() {
		return name;
	}

	public Array<String> getProperties() {
		Array<String> list = new Array<>();
		list.add("Name: " + name);
		list.add("Race: " + race);
		list.add("Family: " + family);
		list.add("Strength: " + strength);
		list.add("Dextery: " + dextery);
		list.add("Constitution: " + constitution);
		list.add("Wisdom: " + wisdom);
		list.add("Charisma: " + charisma);
		list.add("HP: " + baseHp);
		return list;
	}

	public String getRace() {
		return race;
	}

	public int getStrength() {
		return strength;
	}

	public int getWisdom() {
		return wisdom;
	}

	public void setBaseHp(int baseHp) {
		this.baseHp = baseHp;
	}

	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public void setDextery(int dextery) {
		this.dextery = dextery;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProperties(String name, String race, String family, int strength, int dextery, int constitution,
			int wisdom, int charisma, int baseHp) {
		this.name = name;
		this.race = race;
		this.family = family;
		this.strength = strength;
		this.dextery = dextery;
		this.constitution = constitution;
		this.wisdom = wisdom;
		this.charisma = charisma;
		this.baseHp = baseHp;
		this.hp = baseHp;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

}
