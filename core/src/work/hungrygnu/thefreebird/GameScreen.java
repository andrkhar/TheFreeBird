package work.hungrygnu.thefreebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 11.02.16.
 */
public class GameScreen implements Screen {

    TheFreeBirdGame game;
    private ShapeRenderer renderer;
    private FitViewport viewportFar;
    private FitViewport viewportClose;

    public boolean closeUpView;

    public GameInput gameInterface;
    public Bird bird;

    public GameScreen(TheFreeBirdGame game){
        this.game = game;
        this.bird = game.level.bird;
        viewportClose = game.level.viewportClose;
        bird.nanotimeAnimationStart = TimeUtils.nanoTime();

    }
    @Override
    public void show() {
        renderer = game.renderer;
        //viewportClose = game.viewportClose;

        viewportFar = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);


        closeUpView = true;

        gameInterface = new GameInput(this, game.level);


    }


    @Override
    public void render(float delta) {

        game.level.update(delta);

        // Drawing
        if (closeUpView) viewportClose.apply();
        else viewportFar.apply();

        Gdx.gl20.glClearColor(BACKCOLOR.r, BACKCOLOR.g, BACKCOLOR.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        Gdx.gl20.glEnable(GL20.GL_BLEND);
//        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        if (closeUpView) renderer.setProjectionMatrix(viewportClose.getCamera().combined);
        else renderer.setProjectionMatrix(viewportFar.getCamera().combined);

        renderer.begin();

        game.level.render();

        renderer.end();
//        Gdx.gl20.glDisable(GL20.GL_BLEND);
    }

    @Override
    public void resize(int width, int height) {
        viewportFar.update(width, height, true);
        viewportClose.update(width,height);
        viewportClose.getCamera().position.set(bird.position.x, bird.position.y, 0f);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        renderer.dispose();

    }

    @Override
    public void dispose() {

    }


}
