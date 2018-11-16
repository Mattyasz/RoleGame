package com.pzmatty.rolesandbox.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class InfoGroupUI extends Window {

	private VerticalGroup group;

	public InfoGroupUI(Skin skin) {
		super("Info", skin);
		this.setVisible(false);
		this.group = new VerticalGroup();
		this.group.pad(10.0f);
		//this.addActor(group);
		this.add(group);
	}
	
//	public String getInfo(int index) {
//		return infoList.get(index);
//	}
	
	public void setInfo(Array<String> list) {
		clearInfo();
		for (String name : list) {
			Label label = new Label(name, this.getSkin());
			label.setAlignment(Align.center);
			this.group.addActor(label);
		}
	}
	
	public void clearInfo() {
		this.group.clear();
	}
	
}
