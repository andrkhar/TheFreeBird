package work.hungrygnu.thefreebird.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 20.02.16.
 */
public class Scores {
    // 100 grey cats is 10 red cats is 1 golden cat.
    private int gold;
    private int red;
    private int grey;

    private ShapeRenderer renderer;
    private Camera camera;
    private Vector2 position;

    public Scores(ShapeRenderer renderer, Camera camera){
        this.renderer = renderer;
        this.camera = camera;
        position = new Vector2();

        update(0);

    }

    public void update(int scores){
            gold = scores/100;
            red = scores%100/10;
            grey = scores%10;

            position.set(camera.position.x - CAM_CLOSEUP_HALFWIDTH + SCORES_MARGIN + SCORES_RADIUS,
                    camera.position.y + CAM_CLOSEUP_HALFHEIGHT - SCORES_MARGIN - SCORES_RADIUS);


    }



    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        int all = gold+red+grey;
        for (int i = 0; i < all; i++){
            if (gold > 0){
                renderer.setColor(Color.GOLD);
                gold--;
            } else if (red > 0){
                renderer.setColor(Color.RED);
                red--;
            } else {
                renderer.setColor(CAT_COLOR_BODY);
                grey--;
            }
            renderer.circle(position.x, position.y, SCORES_RADIUS);
            position.x += 2* SCORES_RADIUS + SCORES_PADDING;

        }

    }
}
