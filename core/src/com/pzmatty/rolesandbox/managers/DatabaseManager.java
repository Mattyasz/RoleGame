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
	private static final String STR_MONSTER = "SELECT * FROM monsters m WHERE m.name = ?";
	private static final String STR_CONSTANT = "SELECT c.value FROM constants c WHERE c.name = ?";
	private static final String STR_MON_VAL = "SELECT m.? FROM monsters m WHERE m.char_name = ?";
	private static final String STR_ANIM_MON = "select a.tileset, a.x, a.y from animations a, monsters m where a.id = m.animation and m.name = ?";
	private static final String STR_ANIM_SWITCH = "select a.tileset, a.x, a.y from animations a, switchs s where a.id = s.animation*V1* and s.name = ?";
	private static final String STR_ASSETS_PATH = "select c.format, c.value from constants c where c.format != 'GAME'";
	private static final String STR_MON_STATS = "select m.name, m.family, m.strength, m.dextery, m.constitution, m.intelligence, m.wisdom, m.charisma, m.hp, r.name, m.damage from monsters m, races r where r.id = m.race and m.name = ?";

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

	public static Array<String> getAnimationData(String entity, String type) {
		try {
			String str = null;
			if (type.equals("CHAR")) {
				str = STR_ANIM_MON;
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

	public static Array<String> getAnimationData(String entity, String type, String align) {
		try {
			String str = null;
			if (type.equals("CHAR")) {
				str = STR_ANIM_MON;
			} else if (type.equals("SWITCH")) {
				str = STR_ANIM_SWITCH.replace("*V1*", align);
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

	public static ResultSet getCharacter(String name) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement(STR_MONSTER);
			stm.setString(1, name);
			return stm.executeQuery();
		} catch (SQLException e) {
			Gdx.app.log(TAG, "Error - " + e.getMessage());
			return null;
		}
	}

	public static String getCharacterValue(String character, String id) {
		PreparedStatement stm = null;
		try {
			stm = con.prepareStatement(STR_MON_VAL);
			stm.setString(1, id);
			stm.setString(2, character);
			return stm.executeQuery().getString(1);
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

	public static Array<String> getMonsterStats(String name) {
		Array<String> list = new Array<>();
		try {
			PreparedStatement stm = con.prepareStatement(STR_MON_STATS);
			stm.setString(1, name);
			ResultSet rs = stm.executeQuery();
			for (int i = 1; i <= 10; i++) {
				list.add(rs.getString(i));
			}
			return list;
		} catch (SQLException e) {
			Gdx.app.log(TAG, "Error - " + e.getMessage());
			return null;
		}
	}

}
