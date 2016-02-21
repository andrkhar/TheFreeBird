package work.hungrygnu.thefreebird;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static work.hungrygnu.thefreebird.Assets.*;

import static work.hungrygnu.thefreebird.Constants.*;

public class TheFreeBirdGame extends Game {
	// TODO: HIGH Work around the music loop issue
	// TODO: LOW Improve the code and comments quality overall.
	// TODO: LOW Impove protection of public classes.
	// TODO: Make Settings for Sound and Music
	// TODO: HIGH Add End game screen with the Scores and the time
	// TODO: Add shadows

	public ShapeRenderer renderer;
	public FitViewport viewportClose;
	public work.hungrygnu.thefreebird.game.Level level;


	@Override
	public void create () {
		renderer = new ShapeRenderer();
		renderer.setAutoShapeType(true);
		viewportClose = new FitViewport(CAM_CLOSEUP_WIDTH, CAM_CLOSEUP_HEIGHT);
		level = new work.hungrygnu.thefreebird.game.Level(this);

		music.setLooping(true);
		music.setVolume(0.1f);
		startMenu(0);

		}
	public void startMenu(int poopedCatsCounter){
		setScreen(new work.hungrygnu.thefreebird.menu.MenuScreen(this, poopedCatsCounter));
	}

	public void startGame(){


		setScreen(new work.hungrygnu.thefreebird.game.GameScreen(this));
	}

	public void endGame(){
		music.stop();
		music.setPosition(0);
		music.dispose();
		int poopedCatsCounter = level.bird.poopedCatsCounter;
		level.init();
		startMenu(poopedCatsCounter);
	}


}
