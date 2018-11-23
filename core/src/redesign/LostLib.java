package redesign;

import com.badlogic.gdx.Game;

public class LostLib extends Game {

	ScreenGame screenGame;
	
	@Override
	public void create() {
		screenGame = new ScreenGame();
		setScreen(screenGame);
	}

}
