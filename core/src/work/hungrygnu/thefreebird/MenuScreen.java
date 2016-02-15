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
public class MenuScreen extends InputAdapter implements Screen {

    private TheFreeBirdGame game;
    private ShapeRenderer renderer;
    private FitViewport viewportFar;
    private FitViewport viewportClose;
    private Button buttonFly;
    private Land land;
    private Sky sky;
    private Tree tree;
    private Nest nest;
    private BirdButton bird;

    private boolean closeUpView;

    public MenuScreen(TheFreeBirdGame game){
        this.game = game;
    }
    @Override
    public void show() {
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        viewportFar = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        viewportClose = new FitViewport(CLOSEUP_WIDTH, CLOSEUP_HEIGHT);
        Gdx.input.setInputProcessor(this);

        buttonFly = new Button(renderer,new Vector2(WORLD_WIDTH/2f, WORLD_HEIGHT/2f));
        land = new Land(renderer);
        sky = new Sky(renderer);
        tree = new Tree(renderer);
        nest = new Nest(renderer, tree.nestPosition);
        bird = new BirdButton(renderer, tree.nestPosition.add(0f,4f*BIRD_SCALE));

        closeUpView = true;


    }

    @Override
    public void render(float delta) {
        // Logic
        bird.update(delta);

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

    public boolean keyUp(int keycode){

        if (keycode == Input.Keys.SPACE) closeUpView = !closeUpView;

        return true;
    }
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        if (bird.position.dst(viewportClose.unproject(new Vector2(screenX,screenY))) < bird.bodyRadius) {
            bird.visible = true;
            bird.isFlying = true;
            bird.velocity.add(0f, 2000f);
        }
        return true;
    }
}
