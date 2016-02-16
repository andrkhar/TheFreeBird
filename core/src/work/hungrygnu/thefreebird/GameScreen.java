package work.hungrygnu.thefreebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 11.02.16.
 */
public class GameScreen implements Screen {



    private ShapeRenderer renderer;
    private FitViewport viewportFar;
    public FitViewport viewportClose;
    private Underground underground;
    private Land land;
    private Sky sky;
    private Tree tree;
    private Nest nest;
    public BirdButton bird;

    public boolean closeUpView;

    public GameInterface gameInterface;

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        viewportFar = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        viewportClose = new FitViewport(CLOSEUP_WIDTH, CLOSEUP_HEIGHT);

        underground = new Underground(renderer);
        land = new Land(renderer);
        sky = new Sky(renderer);
        tree = new Tree(renderer);
        nest = new Nest(renderer, tree.nestPosition);
        bird = new BirdButton(renderer, tree.nestPosition.add(0f,4f*BIRD_SCALE));

        closeUpView = true;

        gameInterface = new GameInterface(this);


    }
    public void update(float delta){
        // Logic
        viewportClose.getCamera().position.set(bird.position.x, bird.position.y, 0f);
        bird.update(delta);
    }

    @Override
    public void render(float delta) {

        update(delta);

        // Drawing
        if (closeUpView) viewportClose.apply();
        else viewportFar.apply();

        Gdx.gl20.glClearColor(BACKCOLOR.r, BACKCOLOR.g, BACKCOLOR.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glEnable(GL20.GL_BLEND);
        Gdx.gl20.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        if (closeUpView) renderer.setProjectionMatrix(viewportClose.getCamera().combined);
        else renderer.setProjectionMatrix(viewportFar.getCamera().combined);

        renderer.begin();

        underground.render();
        land.render();
        sky.render();
        tree.render();
        nest.render();
        //buttonFly.render();
        bird.render();

        renderer.end();
        Gdx.gl20.glDisable(GL20.GL_BLEND);
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
