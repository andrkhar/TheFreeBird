package work.hungrygnu.thefreebird;

import com.badlogic.gdx.Gdx;
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
public class MenuScreen extends InputAdapter implements Screen {

    private TheFreeBirdGame game;
    private ShapeRenderer renderer;
    private FitViewport viewport;
    private Button buttonFly;
    private Land land;
    private Sky sky;
    private Tree tree;
    private Nest nest;
    private Bird bird;

    public MenuScreen(TheFreeBirdGame game){
        this.game = game;
    }
    @Override
    public void show() {
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        Gdx.input.setInputProcessor(this);

        buttonFly = new Button(renderer,new Vector2(viewport.getWorldWidth()/2f, viewport.getWorldHeight()/2f));
        land = new Land(renderer);
        sky = new Sky(renderer);
        tree = new Tree(renderer);
        nest = new Nest(renderer, tree.nestPosition);
        bird = new Bird(renderer, tree.nestPosition.add(0f,4f*BIRD_SCALE));


    }

    @Override
    public void render(float delta) {
        // Logic

        // Drawing
        viewport.apply();
        Gdx.gl20.glClearColor(BACKCOLOR.r, BACKCOLOR.g, BACKCOLOR.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin();

        land.render();
        sky.render();
        tree.render();
        nest.render();
        buttonFly.render();
        bird.render();

        renderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
