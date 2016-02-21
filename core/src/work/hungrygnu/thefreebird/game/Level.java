package work.hungrygnu.thefreebird.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.FitViewport;

import work.hungrygnu.thefreebird.world.Nest;
import work.hungrygnu.thefreebird.beings.Poop;
import work.hungrygnu.thefreebird.TheFreeBirdGame;

import static work.hungrygnu.thefreebird.Constants.*;
import static work.hungrygnu.thefreebird.Assets.*;

/**
 * Created by hungry on 17.02.16.
 */
public class Level {
    // TODO: Add static objects to diverse the world, like grass, flowers, stones and cetera.
    // TODO: Add class Batterfly. It should move at the both Axis like real and can be eaten as well as a caterpillar.

    public TheFreeBirdGame game;
    public ShapeRenderer renderer;
    public FitViewport viewportClose;
    public Camera camera;
    private work.hungrygnu.thefreebird.world.Underground underground;
    private work.hungrygnu.thefreebird.world.Land land;
    private work.hungrygnu.thefreebird.world.Sky sky;
    private work.hungrygnu.thefreebird.world.Tree tree;
    public Nest nest;
    public work.hungrygnu.thefreebird.beings.Bird bird;

    public DelayedRemovalArray<work.hungrygnu.thefreebird.beings.Cat> cats;
    public DelayedRemovalArray<work.hungrygnu.thefreebird.beings.Caterpillar> caterpillars;
    public DelayedRemovalArray<Poop> poops;

    private work.hungrygnu.thefreebird.game.Bars bars;
    private Scores scores;

    public Level(TheFreeBirdGame game){
        this.game = game;
        this.renderer = game.renderer;
        this.viewportClose = game.viewportClose;
        this.camera = viewportClose.getCamera();
        initStatic();
        init();
        spawn();


    }

    private void initStatic() {
        underground = new work.hungrygnu.thefreebird.world.Underground(renderer);
        land = new work.hungrygnu.thefreebird.world.Land(renderer);
        sky = new work.hungrygnu.thefreebird.world.Sky(renderer);
        tree = new work.hungrygnu.thefreebird.world.Tree(renderer);
        nest = new Nest(renderer, tree.nestPosition);
    }

    public void init(){

        Vector2 birdPosition = (new Vector2(tree.nestPosition)).add(0f, BIRD_NEST_START_OFFSET_Y);

        bird = new work.hungrygnu.thefreebird.beings.Bird(birdPosition, this);
        cats = new DelayedRemovalArray<work.hungrygnu.thefreebird.beings.Cat>();
        caterpillars = new DelayedRemovalArray<work.hungrygnu.thefreebird.beings.Caterpillar>();
        poops = new DelayedRemovalArray<Poop>();

        bars = new work.hungrygnu.thefreebird.game.Bars(renderer,camera, bird);
        scores = new Scores(renderer, camera);

    }

    public void update(float delta){

        if (!music.isPlaying())
            music.play();




        if (bird.position.x < CAM_BORDER_LEFT)
            viewportClose.getCamera().position.set(CAM_BORDER_LEFT, bird.position.y, 0f);
        else if (bird.position.x > CAM_BORDER_RIGHT)
            viewportClose.getCamera().position.set(CAM_BORDER_RIGHT, bird.position.y, 0f);
        else viewportClose.getCamera().position.set(bird.position.x, bird.position.y, 0f);

        bird.update(delta);

        checkActive(delta);

        spawn();

        bars.update();
        scores.update(bird.poopedCatsCounter);



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
        scores.render();

    }
    private void checkActive(float delta){
        // TODO: LOW Put Arrays<type> all in one updatable array
        // TODO: LOW Put Arrays<type> all in one renderable array
        for (Poop poop : poops) {
            poop.update(delta);
            if (!poop.active) poops.removeValue(poop, true);
        }
        for (work.hungrygnu.thefreebird.beings.Cat cat : cats) {
            cat.update(delta);
            if (!cat.active) cats.removeValue(cat, true);
        }
        for (work.hungrygnu.thefreebird.beings.Caterpillar caterpillar : caterpillars) {
            caterpillar.update(delta);
            if (!caterpillar.active) caterpillars.removeValue(caterpillar, true);
        }


    }
    private void renderDynamicObjects(){
        for (Poop poop : poops)
            poop.render();
        for (work.hungrygnu.thefreebird.beings.Caterpillar caterpillar : caterpillars)
            caterpillar.render();
        for (work.hungrygnu.thefreebird.beings.Cat cat : cats)
            cat.render();



    }
    private void spawn(){

        if (cats.size < CAT_MAX_NUMBER)
            if(MathUtils.random(-1,CAT_RESPAWN_COEFFICIENT) < 0) {
                cats.add(new work.hungrygnu.thefreebird.beings.Cat(renderer, halfTrue()));
                soundMeow.play(1);
            }

        if (caterpillars.size < CATERPILLAR_MAX_NUMBER)
            if(MathUtils.random(-1,CATERPILLAR_RESPAWN_COEFFICIENT) < 0)
                caterpillars.add(new work.hungrygnu.thefreebird.beings.Caterpillar(renderer, new Vector2(MathUtils.random(0, WORLD_WIDTH), CATERPILLAR_Y), halfTrue()));
    }

    private boolean halfTrue(){
       return MathUtils.random(-1,1) < 0;
    }

}
