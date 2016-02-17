package work.hungrygnu.thefreebird;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static work.hungrygnu.thefreebird.Constants.*;

public class TheFreeBirdGame extends Game {

	ShapeRenderer renderer;
	FitViewport viewportClose;

	@Override
	public void create () {
		renderer = new ShapeRenderer();
		renderer.setAutoShapeType(true);
		viewportClose = new FitViewport(CLOSEUP_WIDTH, CLOSEUP_HEIGHT);

		//startGame(new Vector2(100f,100f));
		startMenu();

	}
	public void startMenu(){
		setScreen(new MenuScreen(this));
	}

	public void startGame(Bird bird){
		setScreen(new GameScreen(this, bird));
	}


}
