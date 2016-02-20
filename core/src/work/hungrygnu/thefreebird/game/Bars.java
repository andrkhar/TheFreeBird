package work.hungrygnu.thefreebird.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 12.02.16.
 */
public class Bars {

    private final Array<Bar>bars;
    private final Camera camera;
    private Vector2 position;
    private work.hungrygnu.thefreebird.beings.Bird bird;



    public Bars(ShapeRenderer renderer, Camera camera, work.hungrygnu.thefreebird.beings.Bird bird){
        this.bird = bird;
        position = new Vector2(camera.position.x, camera.position.y);
        this.camera = camera;
        this.bars = new Array<Bar>();

        createBars(renderer);

    }

    private void createBars(ShapeRenderer renderer) {
        for(int i = 0; i<3; i++){
            switch (i){
                case 0:
                    bars.add(new Bar(renderer, position, BIRD_FOOD_MAX, CATERPILLAR_COLOR_BODY));
                    position.y -= BAR_HEIGHT + BAR_MARGIN;
                    break;
                case 1:
                    bars.add(new Bar(renderer, position, BIRD_ENERGY_MAX, BIRD_COLOR_BODY));
                    position.y -= BAR_HEIGHT + BAR_MARGIN;
                    break;
                case 2:
                    bars.add(new Bar(renderer, position, BIRD_POOP_MAX, POOP_COLOR_CIRCLE));
            }


        }
    }

    public void update(){
        position.set(camera.position.x + CAM_CLOSEUP_HALFWIDTH - BAR_WIDTH - BAR_MARGIN,
                camera.position.y + CAM_CLOSEUP_HALFHEIGHT - BAR_HEIGHT - BAR_MARGIN);

        for(int i = 0; i<3; i++){
            switch (i){
                case 0:
                    bars.get(i).update(position,bird.food);
                    position.y -= BAR_HEIGHT + BAR_MARGIN;
                    break;
                case 1:
                    bars.get(i).update(position,bird.energy);
                    position.y -= BAR_HEIGHT + BAR_MARGIN;
                    break;
                case 2:
                    bars.get(i).update(position,bird.poop);
            }


        }



    }


    public void render(){
        for (Bar bar : bars)
            bar.render();
    }

}
