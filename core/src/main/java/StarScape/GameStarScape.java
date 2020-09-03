package StarScape;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameStarScape extends BaseGame {
	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}
}