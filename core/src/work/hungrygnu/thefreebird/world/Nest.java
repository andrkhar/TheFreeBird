package work.hungrygnu.thefreebird.world;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Nest extends work.hungrygnu.thefreebird.beings.StaticGameObject {

    private final float baseX;
    private final float baseY;
    private final float baseW;
    private final float baseH;

    private final float topX;
    private final float topY;
    private final float topW;
    private final float topH;

    private Rectangle birdRectangleCollider;

    public Nest(ShapeRenderer renderer, Vector2 position) {

        super(renderer, position);



        baseX = position.x -1.5f * NEST_SCALE;
        baseY = position.y -0.5f * NEST_SCALE;
        baseW = 3f * NEST_SCALE;
        baseH = NEST_SCALE;

        topX = baseX + baseW / 10f;
        topY = baseY + 2f/3f * baseH;
        topW = 0.8f * baseW;
        topH = baseH / 3f;

        birdRectangleCollider = new Rectangle(topX, topY, topW, topH);
    }

    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(NEST_COLOR_BOTTOM);
        renderer.ellipse(baseX, baseY, baseW, baseH, NEST_SEGMENTS);
        renderer.setColor(NEST_COLOR_TOP);
        renderer.ellipse(topX, topY, topW, topH, NEST_SEGMENTS);
    }

    public boolean isPointInNest(Vector2 point){
        return birdRectangleCollider.contains(point.x, point.y);
    }
}
