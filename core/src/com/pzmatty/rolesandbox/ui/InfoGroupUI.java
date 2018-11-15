package com.pzmatty.rolesandbox.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class InfoGroupUI extends Window {

	private Label lblInfo;
	private VerticalGroup group;

	public InfoGroupUI(Skin skin) {
		super("Info", skin);
		this.setVisible(false);
		this.group = new VerticalGroup();
		this.lblInfo = new Label("", skin);
		this.group.addActor(lblInfo);
		//this.addActor(group);
		this.add(group);
	}
	
	public String getInfo() {
		return lblInfo.getText().toString();
	}
	
	public void setInfo(String name) {
		this.lblInfo.setText(name);
	}
	
}
