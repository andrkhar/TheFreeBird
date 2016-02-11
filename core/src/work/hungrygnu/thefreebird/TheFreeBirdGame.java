package work.hungrygnu.thefreebird;


import com.badlogic.gdx.Game;

public class TheFreeBirdGame extends Game {

	@Override
	public void create () {
		showMenuScreen();

	}

	public void showMenuScreen() {
		setScreen(new MenuScreen(this));
	}

	public void showGameScreen() {

	}
}
