package work.hungrygnu.thefreebird.menu;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

import work.hungrygnu.thefreebird.world.Nest;
import work.hungrygnu.thefreebird.TheFreeBirdGame;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 17.02.16.
 */
public class Menu {
    ShapeRenderer renderer;
    public FitViewport viewportClose;
    private work.hungrygnu.thefreebird.world.Sky sky;
    private work.hungrygnu.thefreebird.world.Land land;
    private work.hungrygnu.thefreebird.world.Tree tree;
    private Nest nest;
    public BirdButton bird;

    // Parameters for screen zones rendering

    Vector2 cameraPosition;

    boolean gameStarting;

    private Arrow arrow1Left;
    private Arrow arrow2Right;
    private Arrow arrow3Up;




    public Menu(TheFreeBirdGame game){
        this.renderer = game.renderer;
        this.viewportClose = game.viewportClose;
        sky = new work.hungrygnu.thefreebird.world.Sky(renderer);
        land = new work.hungrygnu.thefreebird.world.Land(renderer);
        tree = new work.hungrygnu.thefreebird.world.Tree(renderer);
        nest = new Nest(renderer, tree.nestPosition);
        Vector2 birdPosition = (new Vector2(tree.nestPosition)).add(0f,BIRD_NEST_START_OFFSET_Y);
        bird = new BirdButton(birdPosition,game.level);

        cameraPosition = new Vector2();
        gameStarting = false;

        arrow1Left = new Arrow(renderer,new Vector2(cameraPosition.x - CAM_CLOSEUP_WIDTH/3f, cameraPosition.y), 1);
        arrow2Right = new Arrow(renderer,new Vector2(cameraPosition.x + CAM_CLOSEUP_WIDTH/3f, cameraPosition.y),2);
        arrow3Up = new Arrow(renderer,new Vector2(cameraPosition.x, cameraPosition.y + CAM_CLOSEUP_HEIGHT/3f),3);


    }

    public void update(float delta){
        viewportClose.getCamera().position.set(bird.position, 0);
        bird.update(delta);
        cameraPosition.set(viewportClose.getCamera().position.x, viewportClose.getCamera().position.y);

        updateArrowsColor();

    }

    private void updateArrowsColor() {
        arrow1Left.color.a = 1 - bird.alpha;
        arrow2Right.color.a = 1 - bird.alpha;
        arrow3Up.color.a = 1 - bird.alpha;

    }

    public void render(){
        sky.render();
        land.render();
        tree.render();
        nest.render();
        renderMenuBoxes();
        bird.render();
        renderPoop();

        arrow1Left.render(cameraPosition);
        arrow2Right.render(cameraPosition);
        arrow3Up.render(cameraPosition);



     }

    private void renderMenuBoxes(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(MENU_SIDE_COLOR);
        // LEFT BOX
        renderer.rect(cameraPosition.x - CAM_CLOSEUP_HALFWIDTH - bird.alpha * MENU_BOX_WIDTH,
                cameraPosition.y - CAM_CLOSEUP_HALFHEIGHT, MENU_BOX_WIDTH, CAM_CLOSEUP_HEIGHT);
        // RIGHT BOX
        renderer.rect(cameraPosition.x + CAM_CLOSEUP_HALFWIDTH - MENU_BOX_WIDTH + bird.alpha * MENU_BOX_WIDTH,
                cameraPosition.y - CAM_CLOSEUP_HALFHEIGHT, MENU_BOX_WIDTH, CAM_CLOSEUP_HEIGHT);
        // TOP BOX
        renderer.setColor(MENU_TOP_COLOR);
        renderer.rect(cameraPosition.x - MENU_BOX_WIDTH / 2f, cameraPosition.y + bird.alpha * CAM_CLOSEUP_HALFHEIGHT,
                MENU_BOX_WIDTH, CAM_CLOSEUP_HALFHEIGHT);
        // BOTTOM BOX
        renderer.setColor(MENU_BOTTOM_COLOR);
        renderer.rect(cameraPosition.x - MENU_BOX_WIDTH / 2f, cameraPosition.y - CAM_CLOSEUP_HALFHEIGHT - bird.alpha * CAM_CLOSEUP_HALFHEIGHT,
                MENU_BOX_WIDTH, CAM_CLOSEUP_HALFHEIGHT);

    }

    private void renderPoop(){

        float poopX = cameraPosition.x;
        float poopY = cameraPosition.y - CAM_CLOSEUP_HEIGHT/4f - (CAM_CLOSEUP_HALFHEIGHT * bird.alpha);
        float poopScale = 5f;
        float poopR = POOP_RADIUS * poopScale;
        float poopH = POOP_HEIGHT * poopScale;

        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(POOP_COLOR_CIRCLE);
        renderer.circle(poopX, poopY, poopR, POOP_SEGMENTS);
        renderer.triangle(poopX - poopR, poopY, poopX + poopR, poopY, poopX, poopY + poopH,
                POOP_COLOR_CIRCLE,POOP_COLOR_CIRCLE,POOP_COLOR_TOP);

    }

}
