package work.hungrygnu.thefreebird;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static work.hungrygnu.thefreebird.Constants.*;
/**
 * Created by hungry on 12.02.16.
 */
public class Nest extends StaticGameObject {
    // TODO: The bird should be able to land in the nest.

    private final float baseX;
    private final float baseY;
    private final float baseW;
    private final float baseH;

    private final float topX;
    private final float topY;
    private final float topW;
    private final float topH;

    public Nest(ShapeRenderer renderer, Vector2 position) {

        super(renderer, position);

        Vector2 base1 = new Vector2(position).add(-1.5f * NEST_SCALE, -0.5f * NEST_SCALE);
        Vector2 base2 = new Vector2(position).add(1.5f * NEST_SCALE, 0.5f * NEST_SCALE);

        baseX = base1.x;
        baseY = base1.y;
        baseW = base2.x - base1.x;
        baseH = base2.y - base1.y;

        topX = baseX + baseW / 10f;
        topY = baseY + 2f/3f * baseH;
        topW = 0.8f * baseW;
        topH = baseH / 3f;
    }

    public void render(){
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(NEST_COLOR_BOTTOM);
        renderer.ellipse(baseX, baseY, baseW, baseH, NEST_SEGMENTS);
        renderer.setColor(NEST_COLOR_TOP);
        renderer.ellipse(topX, topY, topW, topH, NEST_SEGMENTS);
    }
}
