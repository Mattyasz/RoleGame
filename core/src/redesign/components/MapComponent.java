package redesign.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class MapComponent implements Component {

	public TiledMap tiledMap;
	public TiledMapTileLayer tileLayer;
	
}
