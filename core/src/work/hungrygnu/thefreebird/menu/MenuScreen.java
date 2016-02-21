package work.hungrygnu.thefreebird.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import work.hungrygnu.thefreebird.Assets;
import work.hungrygnu.thefreebird.TheFreeBirdGame;
import work.hungrygnu.thefreebird.game.Scores;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 17.02.16.
 */
public class MenuScreen implements Screen {

    TheFreeBirdGame game;
    ShapeRenderer renderer;
    FitViewport viewportClose;
    Menu menu;
    MenuInput menuInterface;
    private final int poopedNumberPreviousGame;
    private Scores scores;
    public MenuScreen(TheFreeBirdGame game, int poopedNumberPreviousGame){
        this.game = game;
        this.poopedNumberPreviousGame = poopedNumberPreviousGame;

    }

    @Override
    public void show() {

        viewportClose = game.viewportClose;
        renderer = game.renderer;
        scores = new Scores(game.renderer, viewportClose.getCamera());
        menu = new Menu(game);
        menuInterface = new MenuInput(menu.bird, viewportClose);



    }

    @Override
    public void render(float delta) {
        menu.update(delta);
        scores.update(poopedNumberPreviousGame);

        // Drawing
        viewportClose.apply();

        Gdx.gl20.glClearColor(BACKCOLOR.r, BACKCOLOR.g, BACKCOLOR.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        renderer.setProjectionMatrix(viewportClose.getCamera().combined);


        renderer.begin();

        menu.render();
        scores.render();

        renderer.end();
        Gdx.gl20.glDisable(GL20.GL_BLEND);

        if (menu.bird.alpha == 1) {
            work.hungrygnu.thefreebird.beings.Bird levelBird = game.level.bird;
            levelBird.lastFramePosition.set(menu.bird.lastFramePosition);
            levelBird.position.set(menu.bird.position);
            levelBird.velocity.set(menu.bird.velocity);
            levelBird.flying = true;
            levelBird.inNest = false;
            levelBird.recalculatePoints();
            game.startGame();
        }

    }

    @Override
    public void resize(int width, int height) {
        viewportClose.update(width, height);
        viewportClose.getCamera().position.set(menu.bird.position, 0f);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {


    }
}
