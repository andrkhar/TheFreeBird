package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 17.02.16.
 */
public class Level {
    // TODO: Add static objects to diverse the world, like grass, flowers, stones and cetera.
    // TODO: Add class Batterfly. It should move at the both Axis like real and can be eaten as well as a caterpillar.

    public TheFreeBirdGame game;
    public ShapeRenderer renderer;
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

    private Bars bars;

    public Level(TheFreeBirdGame game){
        this.game = game;
        this.renderer = game.renderer;
        this.viewportClose = game.viewportClose;
        initStatic();
        init();
        spawn();


    }

    private void initStatic() {
        underground = new Underground(renderer);
        land = new Land(renderer);
        sky = new Sky(renderer);
        tree = new Tree(renderer);
        nest = new Nest(renderer, tree.nestPosition);
    }

    public void init(){

        bird = new Bird(tree.nestPosition.add(0f,4f*BIRD_SCALE), this);
        cats = new DelayedRemovalArray<Cat>();
        caterpillars = new DelayedRemovalArray<Caterpillar>();
        poops = new DelayedRemovalArray<Poop>();

        bars = new Bars(renderer,viewportClose.getCamera(), bird);

    }

    public void update(float delta){




        if (bird.position.x < CAM_BORDER_LEFT)
            viewportClose.getCamera().position.set(CAM_BORDER_LEFT, bird.position.y, 0f);
        else if (bird.position.x > CAM_BORDER_RIGHT)
            viewportClose.getCamera().position.set(CAM_BORDER_RIGHT, bird.position.y, 0f);
        else viewportClose.getCamera().position.set(bird.position.x, bird.position.y, 0f);

        bird.update(delta);

        checkActive(delta);

        spawn();

        bars.update();



    }



    public void render(){

        underground.render();
        land.render();
        sky.render();
        tree.render();
        nest.render();
        renderDynamicObjects();

        bird.render();

        bars.render();

    }
    private void checkActive(float delta){
        // TODO: I will think about to put them all in one updatable array
        for (Poop poop : poops) {
            poop.update(delta);
            if (!poop.active) poops.removeValue(poop, true);
        }
        for (Cat cat : cats) {
            cat.update(delta);
            if (!cat.active) cats.removeValue(cat, true);
        }
        for (Caterpillar caterpillar : caterpillars) {
            caterpillar.update(delta);
            if (!caterpillar.active) caterpillars.removeValue(caterpillar, true);
        }


    }
    private void renderDynamicObjects(){
        for (Poop poop : poops)
            poop.render();
        for (Caterpillar caterpillar : caterpillars)
            caterpillar.render();
        for (Cat cat : cats)
            cat.render();



    }
    private void spawn(){

        if (cats.size < CAT_MAX_NUMBER)
            if(MathUtils.random(-1,CAT_RESPAWN_COEFFICIENT) < 0)
                cats.add(new Cat(renderer, halfTrue()));

        if (caterpillars.size < CATERPILLAR_MAX_NUMBER)
            if(MathUtils.random(-1,CATERPILLAR_RESPAWN_COEFFICIENT) < 0)
                caterpillars.add(new Caterpillar(renderer, new Vector2(MathUtils.random(0, WORLD_WIDTH), CATERPILLAR_Y), halfTrue()));
    }

    private boolean halfTrue(){
       return MathUtils.random(-1,1) < 0;
    }

}
