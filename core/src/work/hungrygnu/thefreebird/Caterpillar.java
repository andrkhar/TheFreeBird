package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by hungry on 12.02.16.
 */
public class Caterpillar extends DestructibleDynamicObject {
    // Draw Caterpillar specific parameters -------
    private Circle circleHead, circleNeck, circleBelly, circleButt, circleTail;
    //  ---------

    public Caterpillar(ShapeRenderer renderer, Vector2 position) {
        super(renderer, position);
    }
}
