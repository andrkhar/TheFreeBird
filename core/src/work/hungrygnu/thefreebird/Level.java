package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 17.02.16.
 */
public class Level {
    private ShapeRenderer renderer;
    public FitViewport viewportClose;
    private Underground underground;
    private Land land;
    private Sky sky;
    private Tree tree;
    private Nest nest;
    public Bird bird;

    DelayedRemovalArray<Cat> cats;
    DelayedRemovalArray<Caterpillar> caterpillars;
    DelayedRemovalArray<Poop> poops;

    public Level(ShapeRenderer renderer, FitViewport viewportClose){
        this.renderer = renderer;
        this.viewportClose = viewportClose;
        init();
        cats = new DelayedRemovalArray<Cat>();
        caterpillars = new DelayedRemovalArray<Caterpillar>();
        poops = new DelayedRemovalArray<Poop>();
    }

    public void init(){

        underground = new Underground(renderer);
        land = new Land(renderer);
        sky = new Sky(renderer);
        tree = new Tree(renderer);
        nest = new Nest(renderer, tree.nestPosition);
        bird = new Bird(renderer, tree.nestPosition.add(0f,4f*BIRD_SCALE));

    }

    public void update(float delta){


        if (bird.position.x < CAM_BORDER_LEFT)
            viewportClose.getCamera().position.set(CAM_BORDER_LEFT, bird.position.y, 0f);
        else if (bird.position.x > CAM_BORDER_RIGHT)
            viewportClose.getCamera().position.set(CAM_BORDER_RIGHT, bird.position.y, 0f);
        else viewportClose.getCamera().position.set(bird.position.x, bird.position.y, 0f);

        bird.update(delta);
        for (Poop poop : poops) {
            poop.update(delta);
            if (!poop.active) poops.removeValue(poop, true);
        }

    }

    public void render(){

        underground.render();
        land.render();
        sky.render();
        tree.render();
        nest.render();
        for (Poop poop : poops)
            poop.render();
        bird.render();

    }

}
