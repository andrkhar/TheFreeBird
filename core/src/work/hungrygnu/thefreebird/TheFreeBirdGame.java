package work.hungrygnu.thefreebird;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static work.hungrygnu.thefreebird.Constants.*;

public class TheFreeBirdGame extends Game {
	// TODO: Improve the code and comments quality overall.
	// TODO: Add Sounds and Music
	// TODO: Make Settings for Sound and Music
	// TODO: Add Scores - pooped Cats and the Time of Survival
	// TODO: Add End game screen with the Scores and the time
	// TODO: Add shadows

	ShapeRenderer renderer;
	FitViewport viewportClose;
	Level level;

	@Override
	public void create () {
		renderer = new ShapeRenderer();
		renderer.setAutoShapeType(true);
		viewportClose = new FitViewport(CAM_CLOSEUP_WIDTH, CAM_CLOSEUP_HEIGHT);
		level = new Level(this);

		startMenu();

	}
	public void startMenu(){
		setScreen(new MenuScreen(this));
	}

	public void startGame(){
		setScreen(new GameScreen(this));
	}

	public void endGame(){
		level.init();
		startMenu();
	}


}
