package redesign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class DatabaseManager {

	private static final String MONSTER_QUERY = "";
	private static final String ANIMATION_QUERY = "select anim.tileset, anim.x, anim.y from monsters mon, animations anim where mon.animation = anim.id and mon.name = ?";
	
	private static final String TAG = DatabaseManager.class.getSimpleName();
	private Connection con;
	
	public void connect() {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + Gdx.files.internal("data/LostLib.db").path());
		} catch (SQLException e) {
			Gdx.app.log(TAG, "Error - " + e.getMessage());
		}
	}

	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				Gdx.app.log(TAG, "Error - " + e.getMessage());
			}
		}
	}
	
	public Array<String> getAnimation(String name) {
		try {
			PreparedStatement stm = con.prepareStatement(ANIMATION_QUERY);
			stm.setString(1, name);
			ResultSet rs = stm.executeQuery();
			Array<String> parts = new Array<String>(
					new String[] { rs.getString(1), String.valueOf(rs.getInt(2)), String.valueOf(rs.getInt(3)) });
			return parts;
		} catch (SQLException e) {
			Gdx.app.log(TAG, "Error - " + e.getMessage());
			return null;
		}
	}
	
}
