package work.hungrygnu.thefreebird;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static work.hungrygnu.thefreebird.Constants.*;

public class TheFreeBirdGame extends Game {

	ShapeRenderer renderer;
	FitViewport viewportClose;
	Level level;

	@Override
	public void create () {
		renderer = new ShapeRenderer();
		renderer.setAutoShapeType(true);
		viewportClose = new FitViewport(CAM_CLOSEUP_WIDTH, CAM_CLOSEUP_HEIGHT);
		level = new Level(renderer,viewportClose);

		startMenu();

	}
	public void startMenu(){
		setScreen(new MenuScreen(this));
	}

	public void startGame(){
		setScreen(new GameScreen(this));
	}


}
