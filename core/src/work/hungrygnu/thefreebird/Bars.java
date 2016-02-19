package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import static work.hungrygnu.thefreebird.Constants.*;

/**
 * Created by hungry on 12.02.16.
 */
public class Bars {

    private final Array<Bar>bars;
    private final ShapeRenderer renderer;
    private final Camera camera;



    public Bars(ShapeRenderer renderer, Camera camera){
        this.renderer = renderer;
        this.camera = camera;
        this.bars = new Array<Bar>();
        float x = CAM_CLOSEUP_WIDTH - BAR_WIDTH;
        float y = CAM_CLOSEUP_HEIGHT - BAR_HEIGHT;

        for(int i = 0; i<3; i++){
            ;//bars.add(new Bar(renderer, position, maxValue));
        }
    }

}
