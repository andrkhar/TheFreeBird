package work.hungrygnu.thefreebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 11.02.16.
 */
public class MenuScreen extends InputAdapter implements Screen {

    TheFreeBirdGame game;
    ShapeRenderer renderer;
    FitViewport viewport;
    Button buttonFly;

    public MenuScreen(TheFreeBirdGame game){
        this.game = game;
    }
    @Override
    public void show() {
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        viewport = new FitViewport(WORLD_SIZE, WORLD_SIZE);
        Gdx.input.setInputProcessor(this);
        // TODO ButtonFly extends Button and has "FLY" label on it
        //buttonFly = new Button(renderer, new)
    }

    @Override
    public void render(float delta) {
        // Logic

        // Drawing
        viewport.apply();
        Gdx.gl20.glClearColor(BACKCOLOR.r, BACKCOLOR.g, BACKCOLOR.b, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);



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
