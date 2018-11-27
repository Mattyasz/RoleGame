package redesign.managers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import redesign.commands.Command;
import redesign.commands.MoveCommand;

public class InputController implements InputProcessor {

	private boolean up = false;
	private boolean down = false;
	private boolean left = false;
	private boolean right = false;
	
	public Command command;
	
	@Override
	public boolean keyDown(int keycode) {
		boolean keyProcessed = false;
		switch (keycode) // switch code base on the variable keycode
		{
		case Keys.LEFT: // if keycode is the same as Keys.LEFT a.k.a 21
			if (!left) {
				left = true; // do this
				command = new MoveCommand(new Vector2(-1, 0));
			}
			keyProcessed = true; // we have reacted to a keypress
			break;
		case Keys.RIGHT: // if keycode is the same as Keys.LEFT a.k.a 22
			if (!right) {
				right = true; // do this
				command = new MoveCommand(new Vector2(1, 0));
			}
			keyProcessed = true; // we have reacted to a keypress
			break;
		case Keys.UP: // if keycode is the same as Keys.LEFT a.k.a 19
			if (!up) {
				up = true; // do this
				command = new MoveCommand(new Vector2(0, 1));
			}
			keyProcessed = true; // we have reacted to a keypress
			break;
		case Keys.DOWN: // if keycode is the same as Keys.LEFT a.k.a 20
			if (!down) {
				down = true; // do this
				command = new MoveCommand(new Vector2(0, -1));
			}
			keyProcessed = true; // we have reacted to a keypress
		}
		return keyProcessed; // return our peyProcessed flag
	}

	@Override
	public boolean keyUp(int keycode) {
		boolean keyProcessed = false;
		switch (keycode) // switch code base on the variable keycode
		{
		case Keys.LEFT: // if keycode is the same as Keys.LEFT a.k.a 21
			left = false; // do this
			keyProcessed = true; // we have reacted to a keypress
			break;
		case Keys.RIGHT: // if keycode is the same as Keys.LEFT a.k.a 22
			right = false; // do this
			keyProcessed = true; // we have reacted to a keypress
			break;
		case Keys.UP: // if keycode is the same as Keys.LEFT a.k.a 19
			up = false; // do this
			keyProcessed = true; // we have reacted to a keypress
			break;
		case Keys.DOWN: // if keycode is the same as Keys.LEFT a.k.a 20
			down = false; // do this
			keyProcessed = true; // we have reacted to a keypress
		}
		return keyProcessed; // return our peyProcessed flag
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
