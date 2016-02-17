package work.hungrygnu.thefreebird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 17.02.16.
 */
public class Menu {
    ShapeRenderer renderer;
    public FitViewport viewportClose;
    private Sky sky;
    private Land land;
    private Tree tree;
    private Nest nest;
    public BirdButton bird;


    public Menu(ShapeRenderer renderer, FitViewport viewportClose){
        this.renderer = renderer;
        this.viewportClose = viewportClose;
        sky = new Sky(renderer);
        land = new Land(renderer);
        tree = new Tree(renderer);
        nest = new Nest(renderer, tree.nestPosition);
        bird = new BirdButton(renderer, tree.nestPosition.add(0f,4f*BIRD_SCALE));


    }

    public void update(float delta){
        viewportClose.getCamera().position.set(bird.position, 0);
        bird.update(delta);


    }

    public void render(){
        sky.render();
        land.render();
        tree.render();
        nest.render();
        bird.render();


    }
}
