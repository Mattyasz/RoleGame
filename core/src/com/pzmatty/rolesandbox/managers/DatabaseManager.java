package com.pzmatty.rolesandbox.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public abstract class DatabaseManager {

	private static final String TAG = DatabaseManager.class.getSimpleName();
	private static Connection con;
	private static final String URL = "jdbc:sqlite:" + Gdx.files.internal("data/Entities.db").path();;
	private static final String STR_CHARACTER = "SELECT * FROM characters c WHERE c.name = ?";
	private static final String STR_CONSTANT = "SELECT c.value FROM constants c WHERE c.name = ?";
	private static final String STR_CHAR_VAL = "SELECT c.? FROM characters c WHERE c.char_name = ?";
	private static final String STR_ANIM_CHAR = "select a.tileset, a.x, a.y from animations a, characters c where a.id = c.animation and c.name = ?";
	private static final String STR_ANIM_SWITCH = "select a.tileset, a.x, a.y from animations a, switchs s where a.id = s.animation and s.name = ?";
	private static final String STR_ASSETS_PATH = "select c.format, c.value from constants c where c.format != 'GAME'";

	public static void connect() {
		try {
			con = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			Gdx.app.log(TAG, "Error - " + e.getMessage());
		}
	}

	public static void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				Gdx.app.log(TAG, "Error - " + e.getMessage());
			}
		}
	}

	public static ResultSet getCharacter(String name) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement(STR_CHARACTER);
			stm.setString(1, name);
			return stm.executeQuery();
		} catch (SQLException e) {
			Gdx.app.log(TAG, "Error - " + e.getMessage());
			return null;
		}
	}

	public static String getConstant(String name) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement(STR_CONSTANT);
			stm.setString(1, name);
			return stm.executeQuery().getString(1);
		} catch (SQLException e) {
			Gdx.app.log(TAG, "Error - " + e.getMessage());
			return null;
		}
	}

	public static Array<String> getAnimationData(String entity, String type) {
		try {
			String str = null;
			if (type.equals("CHAR")) {
				str = STR_ANIM_CHAR;
			} else if (type.equals("SWITCH")) {
				str = STR_ANIM_SWITCH;
			}
			PreparedStatement stm = con.prepareStatement(str);
			stm.setString(1, entity);
			ResultSet rs = stm.executeQuery();
			Array<String> parts = new Array<String>(
					new String[] { rs.getString(1), String.valueOf(rs.getInt(2)), String.valueOf(rs.getInt(3)) });
			return parts;
		} catch (SQLException e) {
			Gdx.app.log(TAG, "Error - " + e.getMessage());
			return null;
		}
	}

	public static String getCharacterValue(String character, String id) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement(STR_CHAR_VAL);
			stm.setString(1, id);
			stm.setString(2, character);
			return stm.executeQuery().getString(1);
		} catch (SQLException e) {
			Gdx.app.log(TAG, "Error - " + e.getMessage());
			return null;
		}
	}

	public static Array<String[]> getAssetsPath() {
		Statement stm = null;
		Array<String[]> paths = new Array<>();
		try {
			stm = con.createStatement();
			ResultSet rs = stm.executeQuery(STR_ASSETS_PATH);
			while (rs.next()) {
				paths.add(new String[] { rs.getString(1), rs.getString(2) });
			}
			return paths;
		} catch (SQLException e) {
			Gdx.app.log(TAG, "Error - " + e.getMessage());
			return null;
		}
	}

}
